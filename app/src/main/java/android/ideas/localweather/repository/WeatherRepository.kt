package android.ideas.localweather.repository

import android.ideas.localweather.remote.Result
import android.ideas.localweather.remote.RetrofitBuilder
import android.ideas.localweather.remote.dto.LocalInformation
import android.ideas.localweather.remote.dto.Weather
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

    override suspend fun fetchWeatherInfo(woeId: Long): Result<Weather> {
        return try {
            Result.Success(retrofit.fetchLocalWeather(woeId))
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }

}