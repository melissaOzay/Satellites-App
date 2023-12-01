package com.example.satellites_app.di

import com.example.satellites_app.features.satallite.data.local.SatelliteLocalDS
import com.example.satellites_app.features.satallite.data.local.SatelliteLocalDSImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun provideSatelliteLocalDs(
        satelliteLocalDSImpl: SatelliteLocalDSImpl
    ): SatelliteLocalDS
}