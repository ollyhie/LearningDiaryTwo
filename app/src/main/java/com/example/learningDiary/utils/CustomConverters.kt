package com.example.learningDiary.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.learningDiary.models.Genre

class CustomConverters {

    @TypeConverter
    fun listToString(value: List<String>) = value.joinToString(", ")

    @TypeConverter
    fun genreListToString(value: List<Genre>) = value.joinToString(", ")

    @TypeConverter
    fun stringToList(value: String) = value.split(", ").map { it.trim() }
}