package android.ideas.localweather.viewmodel

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
    private val tempLocalWeathers = mutableListOf<LocalWeather>()
    private val _localWeathers = MutableLiveData<List<LocalWeather>>()
    val localWeather: LiveData<List<LocalWeather>> get() = _localWeathers

    private val _isRequestDone = MutableLiveData(false)
    val isRequestDone: LiveData<Boolean> get() = _isRequestDone

    fun fetchLocalInfo() = viewModelScope.launch {
        _isRequestDone.value = false
        val result = withContext(Dispatchers.IO) {
            repository.fetchLocalInfo(DEFAULT_QUERY)
        }
        when(result) {
            is Result.Success -> {
                fetchWeatherInfo(result.data.map { it.woeId })
            }
            is Result.Error -> {

            }
        }
        _isRequestDone.value = true
    }

    private fun fetchWeatherInfo(woeIds: List<Long>) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            woeIds.forEach { woeId ->
                when(val result = repository.fetchWeatherInfo(woeId)) {
                    is Result.Success -> {
                        tempLocalWeathers.add(result.data[0])
                        tempLocalWeathers.add(result.data[1])
                    }
                    is Result.Error -> {

                    }
                }
            }
        }
        Log.i("MainViewModel :", "${tempLocalWeathers.size}")
        _localWeathers.value = tempLocalWeathers.toList()
    }

    companion object {
        const val DEFAULT_QUERY = "se"
    }
}