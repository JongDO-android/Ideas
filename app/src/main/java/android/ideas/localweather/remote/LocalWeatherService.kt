package android.ideas.localweather.remote

import android.ideas.localweather.remote.dto.ConsolidatedWeather
import android.ideas.localweather.remote.dto.LocalInformation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocalWeatherService {
    @GET("/api/location/search/")
    fun fetchLocalInfo(@Query("query") query: String): LocalInformation

    @GET("/api/location/{woeid}")
    fun fetchLocalWeather(@Path("woeid") woeId: Long): ConsolidatedWeather
}