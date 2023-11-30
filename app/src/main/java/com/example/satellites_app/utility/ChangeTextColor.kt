package com.example.satellites_app.utility

import android.graphics.Color
import android.widget.TextView

class ChangeTextColor {
    companion object{
        fun changeAlpha(alpha:Int, tvName: TextView, tvActive: TextView){
            tvName.setTextColor(Color.argb(alpha, 0, 0, 0))
            tvActive.setTextColor(Color.argb(alpha, 0, 0, 0))

        }
        }
}