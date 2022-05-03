package android.ideas.localweather.ui.adapter

import android.ideas.localweather.R
import android.ideas.localweather.data.ViewType
import android.ideas.localweather.data.WeatherItem
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import java.lang.Exception

@Suppress("UNCHECKED_CAST")
class WeatherAdapter: ListAdapter<WeatherItem, WeatherViewHolder<WeatherItem>>(WeatherDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder<WeatherItem> {
        return when(ViewType.values()[viewType]) {
            ViewType.DEFAULT_TITLE -> {
                WeatherViewHolder.DefaultViewHolder(
                    WeatherViewHolder.from(parent, R.layout.layout_weather_default)
                )
            }
            ViewType.LOCAL_TITLE -> {
                WeatherViewHolder.TitleViewHolder(
                    WeatherViewHolder.from(parent, R.layout.layout_local)
                )
            }
            ViewType.WEATHER -> {
                WeatherViewHolder.WeatherItemViewHolder(
                    WeatherViewHolder.from(parent, R.layout.layout_weather_item)
                )
            }
        } as WeatherViewHolder<WeatherItem>
    }

    override fun onBindViewHolder(holder: WeatherViewHolder<WeatherItem>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is WeatherItem.Title -> DEFAULT_TYPE
            is WeatherItem.WeatherLocal -> TITLE_TYPE
            is WeatherItem.Weather -> WEATHER_TYPE
            else -> throw Exception("Unknown Type Item")
        }
    }

    companion object {
        const val DEFAULT_TYPE = 0
        const val TITLE_TYPE = 1
        const val WEATHER_TYPE = 2
    }
}