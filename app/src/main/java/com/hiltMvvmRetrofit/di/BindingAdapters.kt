package com.hiltMvvmRetrofit.di

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageResource")
fun setImageResource(image: ImageView,url:String){
     if(url!=null){
     Picasso.get().load(url).into(image)
     }
}

@BindingAdapter("onClickWithParams")
fun View.setOnClickWithParams(handler: ((Int, String) -> Unit)?) {
    setOnClickListener {
        handler?.invoke(1, "example")
    }
}


fun setVisblity(view: View, visbility : Int){
      view.visibility=visbility
}