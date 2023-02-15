package com.hiltMvvmRetrofit

import android.util.Log
import androidx.activity.viewModels
import com.hiltMvvmRetrofit.databinding.ActivityMainBinding
import com.hiltMvvmRetrofit.ui.activities.main.MainViewModel
import com.savour.app.fr.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {


    //region Variables
    private val mViewModel :MainViewModel  by viewModels()


    lateinit var binding: ActivityMainBinding

    override val bindingVariable: Int
        get() = BR.viewModel

    override val thisViewModel: MainViewModel
        get() = mViewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val isMakeStatusBarTransparent: Boolean
        get() = false



    override fun init() {
        binding= viewDataBinding

        clicklisteners()
        observeData()
    }



    fun clicklisteners(){


    }


     fun observeData() {
         Log.d("Main", "observeData: ")
         Log.d("TAG", "observeData: ")

    }




}


