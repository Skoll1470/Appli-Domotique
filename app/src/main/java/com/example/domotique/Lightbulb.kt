package com.example.domotique

import androidx.annotation.ColorInt

//import androidx.annotation.ColorInt

data class Lightbulb(
    var id: String,
    var intensity: Int,
    @ColorInt var color: Int,
)