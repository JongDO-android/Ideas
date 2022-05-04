package android.ideas.localweather.data

import android.ideas.localweather.remote.dto.LocalWeather

sealed class WeatherItem {
    data class Header(
        val local: String = "Local",
        val today: String = "Today",
        val tomorrow: String = "Tomorrow"
    ) : WeatherItem()

    data class Weather(
        val title: String,
        val todayWeather: LocalWeather,
        val tomorrowWeather: LocalWeather
    ) : WeatherItem()
}
