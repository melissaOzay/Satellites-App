package com.example.satellites_app.features.satallite.data.local.db.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SatelliteConverter {
    @TypeConverter
    fun fromListToString(list: List<Position>): String {
        val type = object: TypeToken<List<Position>>() {}.type
        return Gson().toJson(list, type)
    }
    @TypeConverter
    fun toData(dataString: String?): List<Position> {
        if(dataString.isNullOrEmpty()) {
            return mutableListOf()
        }
        val type: Type = object : TypeToken<List<Position>>() {}.type
        return Gson().fromJson(dataString, type)
    }
}