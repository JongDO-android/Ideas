package android.ideas.localweather.viewmodel

import android.ideas.localweather.repository.RemoteRepository
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: RemoteRepository
): ViewModel() {
}