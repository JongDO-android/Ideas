package android.ideas.localweather.remote.dto

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("title") val title: String,
    @SerializedName("consolidated_weather") val consolidatedWeather: List<LocalWeather>
)
