package com.example.domotique

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.fragment_sound.*
import org.json.JSONArray
import org.json.JSONObject

class SoundFragment : Fragment() {

    val jsonUtils = JsonHandlers()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_sound, container, false)

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        val model = ViewModelProvider(requireActivity()).get(Communicator::class.java)

        val httpAsync  = "http://${model.ip}:${model.port}/api/request_infos"
            .httpGet()
            .responseJson{ request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }
                    is Result.Success -> {
                        val data = result.get()
                        val donnees = data.array()
                        val channels = mutableListOf<Sound>()

                        for(i in 0 until donnees.length()){
                            println("[Données reçues] : ${donnees.getJSONObject(i)} ")
                            val donnee = donnees.getJSONObject(i)
                            val volume = donnee.getDouble("volume").toFloat() / 65536.0f
                            channels.add(Sound(donnee.getString("id"), volume, donnee.getBoolean("muted")))
                        }
                        val adapter = SoundAdapter(channels)
                        rvSounds.adapter = adapter
                        rvSounds.layoutManager = LinearLayoutManager(activity)

                    }
                }
            }
        httpAsync.join()
    }

}