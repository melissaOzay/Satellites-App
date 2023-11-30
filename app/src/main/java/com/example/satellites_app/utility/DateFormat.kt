package com.example.satellites_app.utility

import java.text.SimpleDateFormat
import java.util.Locale

class DateFormat {
    companion object{
         fun format(date:String):String{
            val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val targetFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val newDate = originalFormat.parse(date)
            return targetFormat.format(newDate)
        }
    }
}