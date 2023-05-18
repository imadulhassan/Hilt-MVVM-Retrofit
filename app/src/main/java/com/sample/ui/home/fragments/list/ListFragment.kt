package com.sample.ui.home.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.R
import com.sample.adapters.AdapterItemClick
import com.sample.adapters.ChracterAdapter
import com.sample.databinding.FragmentChracterListBinding
import com.sample.extn.hide
import com.sample.extn.show
import com.sample.extn.showToast
import com.sample.models.ApisResponse
import com.sample.models.RelatedTopic
import com.sample.ui.home.WorkingStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), AdapterItemClick {

    private lateinit var binding: FragmentChracterListBinding

    private val viewModel: ListViewModel by viewModels()

    private val characterAdapter by lazy { ChracterAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentChracterListBinding.inflate(inflater)
        init()
        observeData()
        return binding.root
    }

    private fun init() {
        binding.chracterList.layoutManager = LinearLayoutManager(requireContext())
        binding.chracterList.adapter = characterAdapter
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                characterAdapter.filterList(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                characterAdapter.filterList(newText)
                return true
            }
        })
        characterAdapter.setCallback(this@ListFragment)
    }

    private fun observeData() {
        viewModel.getResult<ApisResponse>()?.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.addItems(it.data as ApisResponse)
            }
        }
        viewModel.getMessage().observe(viewLifecycleOwner){
            if(isVisible){
                requireActivity().showToast(it)
            }
        }
        viewModel.items.observe(viewLifecycleOwner) {
            characterAdapter.updateList(it)
        }
        viewModel.homeScreenState.observe(viewLifecycleOwner) { status ->
            if (isVisible) {
                when (status.state) {
                    WorkingStatus.Loaded -> {
                        binding.showLoading.root.hide()
                    }
                    WorkingStatus.Loading -> {
                        binding.showLoading.root.show()
                    }
                }
            }
        }
    }

    private fun goToDetailsScreen(item: RelatedTopic) {
        item.let {
            val itemDetailFragmentContainer: View? =
                binding.root.findViewById(R.id.chracter_detail_nav_container)
            val bundle = Bundle()
            bundle.putParcelable("OBJ", item)
            if (itemDetailFragmentContainer != null) {
                itemDetailFragmentContainer.findNavController()
                    .navigate(R.id.fragment_chracter_detail, bundle)
            } else findNavController().navigate(R.id.show_chracter_detail, bundle)
        }
    }

    override fun onItemClick(position: Int) {
        viewModel.onClickItem(position)?.let { goToDetailsScreen(it) }
    }

}
