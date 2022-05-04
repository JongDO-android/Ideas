package android.ideas.localweather.repository

import android.ideas.localweather.remote.Result
import android.ideas.localweather.remote.dto.LocalInformation
import android.ideas.localweather.remote.dto.Weather

interface RemoteRepository {
    suspend fun fetchLocalInfo(query: String): Result<List<LocalInformation>>
    suspend fun fetchWeatherInfo(woeId: Long): Result<Weather>
}