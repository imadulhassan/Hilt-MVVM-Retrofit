package com.savour.app.fr.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.hiltMvvmRetrofit.base.BaseViewModel
import com.hiltMvvmRetrofit.utils.showToast


abstract class BaseFragment<T :ViewDataBinding,V : BaseViewModel>(@LayoutRes private val layoutResId: Int) :  Fragment() {

    var baseActivity: BaseActivity<T,V>? = null

    abstract val viewModel: V

    abstract val bindingVariable: Int

    lateinit var viewDataBinding: T

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // get dataBinding
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        setDataBindingVariable()
        //
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        viewDataBinding.executePendingBindings()
        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observeCommonData()
    }

    open fun setDataBindingVariable() {
        // set dataBinding variables
        viewDataBinding.setVariable(bindingVariable, viewModel)

    }

    private fun observeCommonData() {
        // observe common messages

        viewModel.getMessage().observe(viewLifecycleOwner, Observer {
            showToast(message = it)
        })


        // Observe retrofit error messages
        viewModel.retrofitErrorMessage().observe(viewLifecycleOwner, Observer {
            showToast(resId = it?.errorResId, message = it?.errorMessage)
        })

        observeData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        baseActivity = activity as BaseActivity< T,V>
    }

    abstract fun init()

    abstract fun observeData()


}