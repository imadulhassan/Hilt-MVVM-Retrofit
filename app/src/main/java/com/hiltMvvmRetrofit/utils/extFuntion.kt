package com.hiltMvvmRetrofit.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.graphics.*

import android.text.InputType
import android.text.SpannableString
import android.text.style.*
import android.util.Patterns
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment


import com.google.android.material.textfield.TextInputLayout
import com.hiltMvvmRetrofit.R

import com.squareup.picasso.Picasso
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * @AUTHOR IMAD UL HASSAN
 * @DATE 11/JAN/2023
 */

//region Spannable String Extension Functions
fun SpannableString.color(color: String, start: Int, end: Int): SpannableString {
    this.setSpan(ForegroundColorSpan(Color.parseColor(color)), start, end, 0)
    return this
}

fun SpannableString.bold(start: Int, end: Int): SpannableString {
    this.setSpan(StyleSpan(Typeface.BOLD), start, end, 0)
    return this
}

fun SpannableString.underline(start: Int, end: Int): SpannableString {
    this.setSpan(UnderlineSpan(), start, end, 0)
    return this
}


fun SpannableString.italic(start: Int, end: Int): SpannableString {
    this.setSpan(StyleSpan(Typeface.ITALIC), start, end, 0)
    return this
}


fun SpannableString.strike(start: Int, end: Int): SpannableString {
    this.setSpan(StrikethroughSpan(), start, end, 0)
    return this
}
//endregion

fun String.getTextWithStarAtTop() =
    HtmlCompat.fromHtml("<p>$this<sup>*</sup></p>", HtmlCompat.FROM_HTML_MODE_LEGACY)

fun String.getTextWithRedStarAtTop() = HtmlCompat.fromHtml(
    "<p>$this<font color=#C11C35><sup>*</sup></font></p>", HtmlCompat.FROM_HTML_MODE_LEGACY
)

fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun getRandomString(length: Int) : String {
    val charset = ('A'..'Z') + ('0'..'9')
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}

fun EditText.isEmptyText(): Boolean {
    return this.text.toString().trim().isEmpty()
}

fun TextView.isEmptyText(): Boolean {
    return this.text.toString().trim().isEmpty()
}




fun ViewGroup.toggleVisibility(tv: TextView, drawableEnd : Int, plusDrawable : Int, minusDrawable : Int) {
    if (this.isVisible) {
        tv.setCompoundDrawablesWithIntrinsicBounds(plusDrawable, 0, drawableEnd, 0)
        this.hide()

    } else {
        tv.setCompoundDrawablesWithIntrinsicBounds(minusDrawable, 0, drawableEnd, 0)
        this.show()
    }
}

fun ViewGroup.toggleVisibility(iv: ImageView) {
    if (this.isVisible) {
        iv.setImageResource(R.drawable.home_icon)
        this.hide()

    } else {
        iv.setImageResource(R.drawable.home_icon)
        this.show()
    }
}

/**
 * Tfun to set the status bar color
 *
 * @param color
 */
fun Activity.changeStatusBarColor(color: String) {
    val window = window
    //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.parseColor(color)
}

@SuppressLint("SimpleDateFormat") fun Long.milliToDate(dateFormat: String): String {

    val formatter = SimpleDateFormat(dateFormat)

    // Create a calendar object that will convert the date and time value in milliseconds to date.
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return formatter.format(calendar.time)
}


fun Fragment.showAlertDialog(message: String,
                             okListener: DialogInterface.OnClickListener,
                             cancelListener: DialogInterface.OnClickListener?
) {
    AlertDialog.Builder(this.requireActivity()).setMessage(message)
        .setPositiveButton(this.requireActivity().getString(
            R.string.ok), okListener)
        .setNegativeButton(this.requireActivity().getString(R.string.cancel), cancelListener)
        .create().show()
}

fun EditText.setReadOnly(value: Boolean, inputType: Int = InputType.TYPE_NULL) {
    isFocusable = !value
    isFocusableInTouchMode = !value
    this.inputType = inputType
}

fun Double.formatPrice() : String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING

    return df.format(this)
}

/**
 *extension  fun to make any view visible
 *
 */
fun View.show() {
    visibility = View.VISIBLE
}



fun View.hide() {
    visibility = View.GONE
}

/**
 *extension  fun to make any view's visibility to gone
 *
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

fun EditText.errorInField(errorText: String = "", resId: Int? = null) {
    this.apply {
        error = if (errorText.isNotEmpty()) errorText else this.context.getString(resId!!)
        requestFocus()
    }
}
fun EditText.clearError() {
    this.apply {
        error = null
    }
}


fun TextInputLayout.errorInField(errorText: String = "", resId: Int? = null) {
    this.apply {
        isErrorEnabled = true
        error = if (errorText.isNotEmpty()) errorText else this.context.getString(resId!!)
        requestFocus()
    }
}

fun ImageView.setList(){
      setImageResource(R.drawable.iclist_alt_24)
}

fun ImageView.setGrid(){
    setImageResource(R.drawable.ic_grid_view_24)
}



fun ImageView.loadPicassoImage(url: String) {
    val image = if (url.isEmpty()) "google.com" else url
    Picasso.get().load(image).placeholder(
        R.drawable.placeholder_image_24
    ).into(this)
}


fun ImageView.setLocked() {
    val matrix = ColorMatrix()
    matrix.setSaturation(0f)  //0 means grayscale
    val cf = ColorMatrixColorFilter(matrix)
    this.colorFilter = cf
    this.imageAlpha = 128   // 128 = 0.5
}


fun ImageView.checkFav(isFav: Boolean) {
    setImageResource(if (isFav) R.drawable.icfavorite_24 else R.drawable.ic_unfavourite)
}

fun ImageView.setUnlocked() {
    //this.clearColorFilter()
    this.colorFilter = null
    this.imageAlpha = 255
}


/**
 * check if this milli's is greater or less than other milli's
 *
 * @param milli2
 * @return
 */
fun Long.isBiggerThan(milli2: Long): Boolean {
    return this - milli2 >= 1000 * 60 * 60
}

/**
 * fun to get the desired output date format from milliSeconds
 *
 * @param displayFormat
 * @return
 */
fun Long.timeStampToDate(displayFormat: String): String? {
    return try {
        val currentDate = Date(this)
        val dateFormat = SimpleDateFormat(displayFormat, Locale.getDefault())
        dateFormat.format(currentDate)
    } catch (e: Exception) {
        e.toString()
    }
}

fun Long.isTodayBetweenThesesTwoDays(futureDay: Long): Boolean {
    val today = System.currentTimeMillis()
    return this < today && today < futureDay
}

fun Long.isThisBefore(otherDay: Long): Boolean {
    return this < otherDay
}


fun Fragment.showToast(resId: Int? = null, message: String? = null) {
    Toast.makeText(
        activity, if (resId != null) {
            activity!!.getString(resId)
        } else {
            message!!
        }, Toast.LENGTH_SHORT
    ).show()


    fun View.show() {
        visibility = View.VISIBLE
    }


    /**
     *extension  fun to make any view's visibility to gone
     *
     */
    fun View.hide() {
        visibility = View.GONE
    }

    /**
     *extension  fun to make any view's visibility to gone
     *
     */
    fun View.invisible() {
        visibility = View.INVISIBLE
    }
}




