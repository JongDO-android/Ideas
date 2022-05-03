package android.ideas.localweather.ui

import android.ideas.localweather.R
import android.ideas.localweather.databinding.ActivityMainBinding
import android.ideas.localweather.ui.adapter.WeatherAdapter
import android.ideas.localweather.viewmodel.MainViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        with(binding) {
            val gridLayoutManager = GridLayoutManager(this@MainActivity, 3)
            rvLocalWeather.layoutManager = gridLayoutManager
            rvLocalWeather.adapter = weatherAdapter
        }
        mainViewModel.localWeather.observe(this) {
            weatherAdapter.submitList(it)
        }
    }
}