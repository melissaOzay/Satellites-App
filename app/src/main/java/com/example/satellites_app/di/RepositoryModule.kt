package com.example.satellites_app.di

import com.example.satellites_app.features.satallite.domain.repository.SatelliteRepository
import com.example.satellites_app.features.satallite.data.repository.SatelliteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideSatelliteRepository(
        satelliteRepositoryImpl: SatelliteRepositoryImpl,
    ): SatelliteRepository
}