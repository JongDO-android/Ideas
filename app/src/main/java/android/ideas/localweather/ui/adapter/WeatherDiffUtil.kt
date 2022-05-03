package android.ideas.localweather.ui.adapter

import android.ideas.localweather.data.WeatherItem
import androidx.recyclerview.widget.DiffUtil

object WeatherDiffUtil: DiffUtil.ItemCallback<WeatherItem>() {
    override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
        return oldItem == newItem
    }
}