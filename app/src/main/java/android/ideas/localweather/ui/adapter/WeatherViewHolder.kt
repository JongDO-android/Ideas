package android.ideas.localweather.ui.adapter

import android.ideas.localweather.data.WeatherItem
import android.ideas.localweather.databinding.LayoutLocalBinding
import android.ideas.localweather.databinding.LayoutWeatherDefaultBinding
import android.ideas.localweather.databinding.LayoutWeatherItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

sealed class WeatherViewHolder<D : WeatherItem>(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: D)
    data class DefaultViewHolder(private val binding: LayoutWeatherDefaultBinding) :
        WeatherViewHolder<WeatherItem.Title>(binding) {
        override fun bind(item: WeatherItem.Title) {
            binding.weatherItem = item
        }

    }

    data class TitleViewHolder(private val binding: LayoutLocalBinding) :
        WeatherViewHolder<WeatherItem.WeatherLocal>(binding) {
        override fun bind(item: WeatherItem.WeatherLocal) {
            binding.weatherItem = item
        }
    }

    data class WeatherItemViewHolder(private val binding: LayoutWeatherItemBinding) :
        WeatherViewHolder<WeatherItem.Weather>(binding) {
        override fun bind(item: WeatherItem.Weather) {
            binding.weather = item
        }
    }

    companion object {
        fun <T: ViewBinding> from(parent: ViewGroup, resId: Int): T {
            return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                resId,
                parent,
                false
            )
        }
    }
}