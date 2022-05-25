package com.example.domotique

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.await
import com.github.kittinunf.fuel.core.awaitResponse
import com.github.kittinunf.fuel.core.awaitResult
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.FuelJson
import com.github.kittinunf.result.Result
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result.Success
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class JsonHandlers {

    fun getJoke() {
        val httpAsync = "https://geek-jokes.sameerkumar.website/api?format=json"
            .httpGet()
            .responseJson { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }
                    is Success -> {
                        val data = result.get().obj()
                        println(data.get("joke"))
                        println(data)
                    }
                }
            }

        httpAsync.join()
    }


    fun updateSoundAPP(formattedAddress: String, app: String, volume: Float, muted: Boolean) {
        Fuel.post(formattedAddress)
            .jsonBody("{\"volume\" : $volume, \"id\" : \"$app\", \"muted\" : $muted}")
            .response { result -> }
    }

    fun updateDoor(formattedAddress: String, door: String, toggle: Boolean) {
        Fuel.post(formattedAddress)
            .jsonBody("{\"open\" : $toggle, \"id\" : \"$door\"}")
            .response { result -> }
    }

    fun updateLightBulb(formattedAddress: String, light: String, intensity: Int, color: Int) {
        Fuel.post(formattedAddress)
            .jsonBody("{\"intensity\" : $intensity, \"id\" : \"$light\", \"color\" : $color}")
            .response { result -> }
    }

    fun updateWindow(formattedAddress: String, window: String, toggle: Boolean) {
        Fuel.post(formattedAddress)
            .jsonBody("{\"open\" : $window, \"id\" : \"$window\"}")
            .response { result -> }
    }
}