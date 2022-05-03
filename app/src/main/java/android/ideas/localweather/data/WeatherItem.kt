package android.ideas.localweather.data

import android.ideas.localweather.remote.dto.LocalWeather

sealed class WeatherItem {
    data class Title(val title: String): WeatherItem()
    data class WeatherLocal(val title: String): WeatherItem()
    data class Weather(val localWeather: LocalWeather): WeatherItem()
}
