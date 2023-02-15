package com.hiltMvvmRetrofit.ui.fragments.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiltMvvmRetrofit.BR
import com.hiltMvvmRetrofit.R
import com.hiltMvvmRetrofit.adapters.ChracterAdapter
import com.hiltMvvmRetrofit.databinding.FragmentHomeBinding
import com.hiltMvvmRetrofit.models.ResultsItem
import com.hiltMvvmRetrofit.models.apiResponse
import com.hiltMvvmRetrofit.ui.fragments.details.DetailsFragment
import com.hiltMvvmRetrofit.utils.setGrid
import com.hiltMvvmRetrofit.utils.setList
import com.savour.app.fr.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    lateinit var binding: FragmentHomeBinding

    private val mViewModel: HomeViewModel by viewModels()

    override val viewModel: HomeViewModel
        get() = mViewModel

    override val bindingVariable: Int = BR.viewModel

    private val characterAdapter by lazy { ChracterAdapter(mViewModel) }


    override fun init() {
        binding = viewDataBinding
        binding.rvHome.adapter = characterAdapter
        binding.ivListType.setOnClickListener {
            viewModel.switchDisplayMode()
        }


    }

    private fun goToDetailsScreen(item: ResultsItem) {
        item.let {
            val bundle= Bundle()
            bundle.putParcelable("OBJ",item)
            baseActivity!!.addFragmentWithBundle(R.id.fragmentContainerView,DetailsFragment(),true,bundle)

        }
    }

    override fun observeData() {

        viewModel.getResult<apiResponse>().observe(this, {
            it.let {
                viewModel.addItems(it.data as apiResponse)
            }
        })
        viewModel.itemList.observe(this, {
            characterAdapter.updateList(it)
        })
        viewModel.getDisplayMode()!!.observe(this, {
            if (it === DisplayModes.LIST) {
                binding.ivListType.setList()
                binding.rvHome.setLayoutManager(LinearLayoutManager(context))
            } else {
                binding.ivListType.setGrid()
                binding.rvHome.setLayoutManager(GridLayoutManager(context, 2))
            }
        })
        viewModel.homeScreenState.observe(this,{status->
            when(status.state){
                WorkingStatus.onItemClick->
                         goToDetailsScreen(status.first as ResultsItem)
                    WorkingStatus.Loading->
                        Log.d("Home", "observeData: ")
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()

         }


}