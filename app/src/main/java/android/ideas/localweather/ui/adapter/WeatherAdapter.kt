package android.ideas.localweather.ui.adapter

import android.ideas.localweather.R
import android.ideas.localweather.data.ViewType
import android.ideas.localweather.data.WeatherItem
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import java.lang.Exception


class WeatherAdapter: ListAdapter<WeatherItem, WeatherViewHolder<WeatherItem>>(WeatherDiffUtil) {
    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder<WeatherItem> {
        return when(ViewType.values()[viewType]) {
            ViewType.WEATHER_HEADER -> {
                WeatherViewHolder.WeatherHeaderViewHolder(
                    WeatherViewHolder.from(parent, R.layout.layout_weather_header)
                )
            }
            ViewType.WEATHER_ITEM -> {
                WeatherViewHolder.WeatherItemViewHolder(
                    WeatherViewHolder.from(parent, R.layout.layout_weather_total)
                )
            }
        } as WeatherViewHolder<WeatherItem>
    }

    override fun onBindViewHolder(holder: WeatherViewHolder<WeatherItem>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is WeatherItem.Header -> WEATHER_HEADER_TYPE
            is WeatherItem.Weather -> WEATHER_ITEM_TYPE
            else -> throw Exception("Unknown Type Item")
        }
    }

    companion object {
        const val WEATHER_HEADER_TYPE = 0
        const val WEATHER_ITEM_TYPE = 1
    }
}