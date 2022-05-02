package android.ideas.localweather.di

import android.ideas.localweather.repository.RemoteRepository
import android.ideas.localweather.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @ViewModelScoped
    @Provides
    fun provideRepository(): RemoteRepository = WeatherRepository()
}