package com.sample.ui.home.fragments.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sample.databinding.FragmentChracterDetailBinding
import com.sample.extn.extractName
import com.sample.extn.fromHtml
import com.sample.extn.loadPicassoImage
import com.sample.models.RelatedTopic

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentChracterDetailBinding

    private val viewModel: DetailViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val obj = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable("OBJ", RelatedTopic::class.java)
            } else {
                it.getParcelable("OBJ")
            }
            if (obj != null) {
                viewModel.putValue(obj)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentChracterDetailBinding.inflate(inflater)
        observeData()
        return binding.root
    }


    private fun observeData() {
        viewModel.singleItem.observe(viewLifecycleOwner) {
            setData(it)
        }
    }

    private fun setData(item: RelatedTopic) {
        item.let { relatedTopic ->
            item.icon.let {
                binding.imageViewPro.loadPicassoImage(it.uRL)
            }
            binding.tvName.text = extractName(relatedTopic.text)
            binding.tvDescription.text = fromHtml(relatedTopic.result)
        }
    }


}