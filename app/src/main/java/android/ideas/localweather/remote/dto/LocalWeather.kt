package android.ideas.localweather.remote.dto

import com.google.gson.annotations.SerializedName

data class LocalWeather(
    @SerializedName("weather_state_name") val weatherStateName: String,
    @SerializedName("weather_state_abbr") val weatherStateAbbr: String,
    @SerializedName("the_temp") val theTemp: Double,
    @SerializedName("humidity") val humidity: Int
)
