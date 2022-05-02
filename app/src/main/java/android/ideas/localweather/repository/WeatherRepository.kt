package android.ideas.localweather.repository

import android.ideas.localweather.remote.Result
import android.ideas.localweather.remote.RetrofitBuilder
import android.ideas.localweather.remote.dto.LocalInformation
import android.ideas.localweather.remote.dto.LocalWeather
import retrofit2.HttpException

class WeatherRepository: RemoteRepository {
    private val retrofit = RetrofitBuilder.createService()
    override suspend fun fetchLocalInfo(query: String): Result<List<LocalInformation>> {
        return try {
            Result.Success(retrofit.fetchLocalInfo(query))
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }

    override suspend fun fetchWeatherInfo(woeId: Long): Result<List<LocalWeather>> {
        return try {
            Result.Success(retrofit.fetchLocalWeather(woeId).consolidatedWeather)
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }

}