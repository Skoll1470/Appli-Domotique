package com.example.domotique

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class AppsRunning(val apps: Array<App>) {
    class Deserializer : ResponseDeserializable<AppsRunning> {
        override fun deserialize(content: String) = Gson().fromJson(content, AppsRunning::class.java)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AppsRunning

        if (!apps.contentEquals(other.apps)) return false

        return true
    }

    override fun hashCode(): Int {
        return apps.contentHashCode()
    }
}