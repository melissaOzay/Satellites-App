package com.example.satellites_app.data

import android.content.Context
import androidx.room.Room
import com.example.satellites_app.data.local.db.SatelliteAppDB
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SatelliteDBModule {

    @Singleton
    @Provides
    fun provideSatelliteDataBase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, SatelliteAppDB::class.java, "satellite_db")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()

    @Reusable
    @Provides
    fun provideSatelliteListDao(db: SatelliteAppDB) = db.getSatelliteDao()
}