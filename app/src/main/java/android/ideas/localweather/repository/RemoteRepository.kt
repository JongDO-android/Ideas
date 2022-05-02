package android.ideas.localweather.repository

import android.ideas.localweather.remote.Result
import android.ideas.localweather.remote.dto.ConsolidatedWeather
import android.ideas.localweather.remote.dto.LocalInformation

interface RemoteRepository {
    suspend fun fetchLocalInfo(query: String): Result<List<LocalInformation>>
    suspend fun fetchWeatherInfo(woeId: Long): Result<ConsolidatedWeather>
}