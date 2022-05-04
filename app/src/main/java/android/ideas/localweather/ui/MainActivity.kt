package android.ideas.localweather.ui

import android.ideas.localweather.R
import android.ideas.localweather.common.EventObserver
import android.ideas.localweather.databinding.ActivityMainBinding
import android.ideas.localweather.ui.adapter.WeatherAdapter
import android.ideas.localweather.viewmodel.MainViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherAdapter: WeatherAdapter by lazy {
        WeatherAdapter()
    }
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setBindingAttr()
        setUiAttr()
    }

    private fun setBindingAttr() {
        with(binding) {
            lifecycleOwner = this@MainActivity
            viewModel = mainViewModel
        }
    }

    private fun setUiAttr() {
        mainViewModel.fetchLocalInfo()
        // binding
        with(binding) {
            rvLocalWeather.adapter = weatherAdapter

            srLocalWeather.setOnRefreshListener {
                mainViewModel.fetchLocalInfo()
                srLocalWeather.isRefreshing = false
            }
        }
        // observe
        with(mainViewModel) {
            localWeather.observe(this@MainActivity) { weatherItems ->
                weatherAdapter.submitList(weatherItems)
            }

            errorMessage.observe(this@MainActivity, EventObserver { message ->
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            })
        }
    }
}