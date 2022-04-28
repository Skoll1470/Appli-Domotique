package com.example.domotique

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class App(val volume: Float, val id: String, val muted: Boolean) {
    class Deserializer : ResponseDeserializable<App> {
        override fun deserialize(content: String) = Gson().fromJson(content, App::class.java)
    }
}