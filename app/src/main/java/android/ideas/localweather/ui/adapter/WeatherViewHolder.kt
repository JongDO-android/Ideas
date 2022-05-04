package android.ideas.localweather.ui.adapter

import android.ideas.localweather.data.WeatherItem
import android.ideas.localweather.databinding.LayoutWeatherHeaderBinding
import android.ideas.localweather.databinding.LayoutWeatherTotalBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

sealed class WeatherViewHolder<D : WeatherItem>(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: D)

    data class WeatherHeaderViewHolder(private val binding: LayoutWeatherHeaderBinding) :
        WeatherViewHolder<WeatherItem.Header>(binding) {
        override fun bind(item: WeatherItem.Header) {
            binding.weatherHeader = item
        }

    }

    data class WeatherItemViewHolder(private val binding: LayoutWeatherTotalBinding) :
        WeatherViewHolder<WeatherItem.Weather>(binding) {
        override fun bind(item: WeatherItem.Weather) {
            binding.weatherItem = item
        }
    }

    companion object {
        fun <T : ViewBinding> from(parent: ViewGroup, resId: Int): T {
            return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                resId,
                parent,
                false
            )
        }
    }
}