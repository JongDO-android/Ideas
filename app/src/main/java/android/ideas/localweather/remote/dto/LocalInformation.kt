package android.ideas.localweather.remote.dto

import com.google.gson.annotations.SerializedName

data class LocalInformation(
    @SerializedName("title") val title: String,
    @SerializedName("woeid") val woeId: Long
)
