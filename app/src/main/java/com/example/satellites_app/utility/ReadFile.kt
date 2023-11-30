package com.example.satellites_app.utility

import java.io.BufferedReader
import java.io.InputStreamReader

class ReadFile {
    companion object{
        fun readJsonFromAssets(fileName: String): String {
            val inputStream = javaClass.classLoader?.getResourceAsStream("assets/$fileName")
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()

            var line: String?
            try {
                while (reader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                inputStream?.close()
            }

            return stringBuilder.toString()
        }
    }
}