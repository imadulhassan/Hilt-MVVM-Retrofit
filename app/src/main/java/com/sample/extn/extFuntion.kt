package com.sample.extn

import android.app.Activity
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.sample.R
import com.squareup.picasso.Picasso


fun ImageView.loadPicassoImage(url: String) {
    Picasso.get().load("https://duckduckgo.com$url").placeholder(
        R.drawable.placeholder_image_24
    ).into(this)
}


fun extractName(name: String): String {
    return name.substringBefore("-").trim()
}

fun fromHtml(name: String): Spanned? {
    return Html.fromHtml(name, Html.FROM_HTML_MODE_LEGACY)
}

fun Activity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}


fun View.hide() {
    this.visibility = View.GONE
}


fun View.show() {
    this.visibility = View.VISIBLE
}




