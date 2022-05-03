package android.ideas.localweather.viewmodel

import android.ideas.localweather.data.WeatherItem
import android.ideas.localweather.remote.Result
import android.ideas.localweather.remote.dto.LocalWeather
import android.ideas.localweather.repository.RemoteRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: RemoteRepository
): ViewModel() {
    private val weatherItems = mutableListOf<WeatherItem>()
    private val _localWeathers = MutableLiveData<List<WeatherItem>>()
    val localWeather: LiveData<List<WeatherItem>> get() = _localWeathers

    private val _isRequestDone = MutableLiveData(false)
    val isRequestDone: LiveData<Boolean> get() = _isRequestDone

    fun fetchLocalInfo() = viewModelScope.launch {
        weatherItemsInit()
        _isRequestDone.value = false
        val result = withContext(Dispatchers.IO) {
            repository.fetchLocalInfo(DEFAULT_QUERY)
        }
        when(result) {
            is Result.Success -> {
                result.data.map {
                    fetchWeatherInfo(it.title, it.woeId)
                }
            }
            is Result.Error -> {
            }
        }

        _isRequestDone.value = true
    }

    private fun fetchWeatherInfo(title: String, woeId: Long) = viewModelScope.launch {
        val result = withContext(Dispatchers.IO) {
            repository.fetchWeatherInfo(woeId)
        }
        when(result) {
            is Result.Success -> {
                weatherItems.add(WeatherItem.WeatherLocal(title))
                weatherItems.add(WeatherItem.Weather(result.data[0]))
                weatherItems.add(WeatherItem.Weather(result.data[1]))
            }
            is Result.Error -> {

            }
        }
        _localWeathers.value = weatherItems.toList()
    }

    private fun weatherItemsInit() {
        weatherItems.clear()
        _localWeathers.value = weatherItems.toList()
        weatherItems.add(WeatherItem.Title(LOCAL))
        weatherItems.add(WeatherItem.Title(TODAY))
        weatherItems.add(WeatherItem.Title(TOMORROW))
    }

    companion object {
        const val DEFAULT_QUERY = "se"
        const val LOCAL = "Local"
        const val TODAY = "Today"
        const val TOMORROW = "Tomorrow"
    }
}