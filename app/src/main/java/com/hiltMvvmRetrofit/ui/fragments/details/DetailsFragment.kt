package com.hiltMvvmRetrofit.ui.fragments.details
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import com.hiltMvvmRetrofit.BR
import com.hiltMvvmRetrofit.R
import com.hiltMvvmRetrofit.databinding.DetailsScreenBinding
import com.hiltMvvmRetrofit.models.EpisodeDetails
import com.hiltMvvmRetrofit.models.ResultsItem
import com.hiltMvvmRetrofit.utils.*

import com.savour.app.fr.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<DetailsScreenBinding, DetailsViewModel>(R.layout.details_screen) {

    lateinit var binding: DetailsScreenBinding

    private val mViewModel: DetailsViewModel by viewModels()

    override val viewModel: DetailsViewModel
        get() = mViewModel

    override val bindingVariable: Int = BR.viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = getArguments()
        bundle?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                viewModel.putValue(it.getParcelable("OBJ",ResultsItem.javaClass) as ResultsItem)
            } else {
                it.getParcelable<ResultsItem>("OBJ")?.let { it1 -> viewModel.putValue(it1) }
            }
        }
    }
    override fun init() {
        binding = viewDataBinding
        Log.d("TAG", "init: *****************")
        binding.ivFav.setOnClickListener {
            mViewModel.addToFav(viewModel.items.value!!,binding.ivFav)
        }


    }

    override fun observeData() {

        viewModel.getResult<EpisodeDetails>().observe(this, {
            it.let {
                viewModel.addEpisodeDetails(it.data as EpisodeDetails)
            }
        })
        viewModel.episodeDetails.observe(this,{
            setEpisodeData(it)
        })

        viewModel.items.observe(this,{
            it.let {
                setData(it)
                binding.ivFav.checkFav(viewModel.checkIsFav(it))
            }
        })


    }

    fun  setData(item:ResultsItem){
        binding.imageViewPro.loadPicassoImage(item.image!!)
        binding.tvGender.setText(item.gender!!)
        binding.tvStaus.setText(item.status)
        binding.tvName.setText(item.name)
        binding.tvSpecies.setText(item.species)
        binding.tvOrigion.setText(item.origin!!.name!!)
        binding.tvEpisode.text= item.episode.let {
             if(it.isNullOrEmpty()){
                 ""
             }else{
                "# "+it.size
             }
        }
        binding.tvLocation.setText(item.location!!.name!!)
        item.episode.let {
              val size= it!!.size
              val id= it.get(size-1)!!.substringAfterLast("/")
              Log.d("TAG", "setData:EpisodeId $id")
              mViewModel.getLastEpisodeDetails(id.toInt())

        }
    }

    fun setEpisodeData(episodeDetails: EpisodeDetails){
        binding.tvEpisodeon.text= episodeDetails.name
        binding.tvEpisodeonair.text=episodeDetails.airDate
    }

}