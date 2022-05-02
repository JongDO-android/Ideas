package android.ideas.localweather.remote.dto

import com.google.gson.annotations.SerializedName

data class ConsolidatedWeather(
    @SerializedName("consolidated_weather") val consolidatedWeather: List<LocalWeather>
)
