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

    fun getSoundApps(formatedAddress: String) {
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
                       val donnees = data.array()
                       for(i in 0..donnees.length()-1) {
                          println("[Appli ${i+1}] : ${donnees.getJSONObject(i)}")
                       }
                       //println("[test] : ${donnee.get("volume")}")
                       //println(donnee.get("id"))
                       //println(donnee.get("muted"))
                       //println(donnee)
                   }
               }
           }
        httpAsync.join()
    }

    fun updateSoundAPP(formatedAddress: String, app: String, volume: Float, muted: Boolean){
        Fuel.post(formatedAddress)
            .jsonBody("{\"volume\" : $volume, \"id\" : \"$app\", \"muted\" : $muted}")
            .response { result -> }
    }
}