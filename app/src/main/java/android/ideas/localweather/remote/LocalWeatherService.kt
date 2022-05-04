package android.ideas.localweather.remote

import android.ideas.localweather.remote.dto.Weather
import android.ideas.localweather.remote.dto.LocalInformation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocalWeatherService {
    @GET("/api/location/search/")
    suspend fun fetchLocalInfo(@Query("query") query: String): List<LocalInformation>

    @GET("/api/location/{woeid}")
    suspend fun fetchLocalWeather(@Path("woeid") woeId: Long): Weather
}