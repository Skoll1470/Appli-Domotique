package com.example.domotique

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.fuel.json.responseJson
import org.json.JSONObject

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
                    is Result.Success -> {
                        val data = result.get().obj()
                        println(data.get("joke"))
                        println(data)
                    }
                }
            }

        httpAsync.join()
    }

    fun getObjects : JSONArray(formatedAddress: String) {
        val donnees
       val httpAsync = formatedAddress
           .httpGet()
           .responseJson { request, response, result ->
               when (result) {
                   is Result.Failure -> {
                       val ex = result.getException()
                       println(ex)
                   }
                   is Result.Success -> {
                       val data = result.get()
                       donnees = data.array()
                       for(i in 0..donnees.length()-1) {
                          println("[Appli ${i+1}] : ${donnees.getJSONObject(i)}")
                       }
                   }
               }
           }
        httpAsync.join()
        return donnees
    }

    fun updateSoundAPP(formatedAddress: String, app: String, volume: Float, muted: Boolean){
        Fuel.post(formatedAddress)
            .jsonBody("{\"volume\" : $volume, \"id\" : \"$app\", \"muted\" : $muted}")
            .response { result -> }
    }

    fun updateDoor(formatedAddress: String, door: String, toggle: Boolean){
        Fuel.post(formatedAddress)
            .jsonBody("{\"open\" : $toggle, \"id\" : \"$door\"}")
            .response { result -> }
    }

    fun updateLightBulb(formatedAddress: String, light: String, intensity: Float, color : String){
        Fuel.post(formatedAddress)
            .jsonBody("{\"intensity\" : $intensity, \"id\" : \"$light\", \"color\" : \"$color\"}")
            .response { result -> }
    }

    fun updateWindow(formatedAddress: String, window: String, toggle: Boolean){
        Fuel.post(formatedAddress)
            .jsonBody("{\"open\" : $window, \"id\" : \"$app\"}")
            .response { result -> }
    }
}