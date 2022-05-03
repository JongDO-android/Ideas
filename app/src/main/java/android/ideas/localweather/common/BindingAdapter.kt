package android.ideas.localweather.common

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:icon")
fun setWeatherIcon(view: ImageView, abbr: String) {
    val imagerUrl = "https://www.metaweather.com/static/img/weather/png/64/${abbr}.png"
    Glide.with(view)
        .load(imagerUrl)
        .into(view)
}

@BindingAdapter("app:temperature")
fun setWeatherTemperature(view: TextView, temperature: Double) {
    val parsedTemp = temperature.parseToTemperature()
    view.text = parsedTemp

    val spannable = SpannableString(view.text)
    spannable.setSpan(
        StyleSpan(Typeface.BOLD),
        0,
        4,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}

@BindingAdapter("app:humidity")
fun setWeatherHumidity(view: TextView, humidity: Int) {
    view.text = humidity.parseToHumidity()
    val spannable = SpannableString(view.text)
    spannable.setSpan(
        StyleSpan(Typeface.BOLD),
        0,
        2,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}


fun Double.parseToTemperature(): String {
    return "${String.format("%.1f", this)}℃"
}

fun Int.parseToHumidity(): String {
    return "${this}%"
}