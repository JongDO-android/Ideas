package android.ideas.localweather.viewmodel
import android.ideas.localweather.common.Event
import android.ideas.localweather.data.WeatherItem
import android.ideas.localweather.remote.Result
import android.ideas.localweather.repository.RemoteRepository
import android.util.Log
import androidx.lifecycle.*
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

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> get() = _errorMessage

    private val _isRequestDone = MutableLiveData(false)
    val isRequestDone: LiveData<Boolean> get() = _isRequestDone

    fun fetchLocalInfo() = viewModelScope.launch {
        weatherItemsInit()
        _isRequestDone.value = false
        val result = withContext(Dispatchers.IO) {
            repository.fetchLocalInfo(DEFAULT_QUERY)
        }
        when(result) {
            is Result.Success -> result.data.map { fetchWeatherInfo(it.woeId) }
            is Result.Error -> _errorMessage.value = Event(ERROR_MESSAGE)
        }
        _localWeathers.value = weatherItems.toList()
        _isRequestDone.value = true
    }

    private suspend fun fetchWeatherInfo(woeId: Long) {
        val result = withContext(Dispatchers.IO) {
            repository.fetchWeatherInfo(woeId)
        }
        when(result) {
            is Result.Success -> {
                val title = result.data.title
                val todayWeather = result.data.consolidatedWeather[0]
                val tomorrowWeather = result.data.consolidatedWeather[1]
                weatherItems.add(WeatherItem.Weather(title, todayWeather, tomorrowWeather))
            }
            is Result.Error -> _errorMessage.value = Event(ERROR_MESSAGE)
        }
    }

    private fun weatherItemsInit() {
        weatherItems.clear()
        _localWeathers.value = weatherItems.toList()
        weatherItems.add(WeatherItem.Header())
    }

    companion object {
        const val DEFAULT_QUERY = "se"
        const val ERROR_MESSAGE = "네트워크 환경을 확인해주세요."
    }
}