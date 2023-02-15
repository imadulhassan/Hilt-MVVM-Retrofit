package com.savour.app.fr.base

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AnimatorRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.hiltMvvmRetrofit.R
import com.hiltMvvmRetrofit.base.BaseViewModel


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> :
    AppCompatActivity() {
    lateinit var viewDataBinding: T
    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int
    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int
    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val thisViewModel: V
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            val window = window
            if (isMakeStatusBarTransparent) {
                window.statusBarColor = ContextCompat.getColor(this, R.color.purple_200 )
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            } else {
                window.statusBarColor = ContextCompat.getColor(
                    this, R.color.purple_200
                )
            }
        }
        init()
    }


    protected open fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        viewDataBinding.setVariable(bindingVariable, thisViewModel)
        viewDataBinding.executePendingBindings()
    }




    abstract fun init()

    abstract val isMakeStatusBarTransparent: Boolean

    fun addFragmentWithBundle(container: Int,
                              fragment: Fragment,
                              isBackStack: Boolean,
                              bundle: Bundle
    ) {
        val fts = supportFragmentManager.beginTransaction()
        fragment.arguments = bundle
        fts.replace(container, fragment, fragment.javaClass.simpleName)
        if (isBackStack) fts.addToBackStack(fragment.javaClass.simpleName)
        fts.commitAllowingStateLoss() //replaced transaction.commit(); to handle Can not perform this action after onSaveInstanceState Error
    }

    fun navigateToWithBundle(container: Int,
                             fragment: Fragment,
                             isBackStack: Boolean,
                             bundle: Bundle) {
        fragment.arguments = bundle
        val fts = supportFragmentManager.beginTransaction()
        fts.replace(container, fragment, fragment.javaClass.simpleName)
        if (isBackStack) fts.addToBackStack(fragment.javaClass.simpleName)
        fts.commitAllowingStateLoss() //replaced transaction.commit(); to handle Can not perform this action after onSaveInstanceState Error
    }


}

fun AppCompatActivity.doFragmentTransaction(fragManager: FragmentManager = supportFragmentManager,
                                            @IdRes containerViewId: Int,
                                            fragment: Fragment,
                                            @AnimatorRes enterAnimation: Int = android.R.animator.fade_in,
                                            @AnimatorRes exitAnimation: Int = 0,
                                            @AnimatorRes popEnterAnimation: Int = 0,
                                            @AnimatorRes popExitAnimation: Int = android.R.animator.fade_in,
                                            isAddFragment: Boolean = true,
                                            isAddToBackStack: Boolean = true,
                                            allowStateLoss: Boolean = false,
                                            bundle: Bundle? = null) {
    if (bundle != null) {
        fragment.arguments = bundle
    }

    val fragmentTransaction = fragManager.beginTransaction()
        .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)

    if (isAddFragment) {
        fragmentTransaction.add(containerViewId, fragment, fragment.javaClass.simpleName)
    } else {
        fragmentTransaction.replace(containerViewId, fragment, fragment.javaClass.simpleName)
    }

    if (isAddToBackStack) {
        fragmentTransaction.addToBackStack(null)
    }

    if (allowStateLoss) {
        fragmentTransaction.commitAllowingStateLoss()
    } else {
        fragmentTransaction.commit()
    }
}

fun  changeToolBarHeading(str:String){

}


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}