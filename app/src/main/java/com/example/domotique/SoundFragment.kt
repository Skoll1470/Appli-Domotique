package com.example.domotique

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_sound.*

class SoundFragment : Fragment() {

    val jason = JsonHandlers()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_sound, container, false)

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        //jason.getSoundApps("http://192.168.0.102:9500/api/request_infos")
        jason.updateSoundAPP("http://192.168.0.102:9500/api/update_volume", "Playback", -0.4f, false)

        //val data = jason.getObject("ADRESSE POUR REQUEST LES SONS")
        //val channels = mutableListOf()
        //for(i in 0..data.length-1){
        //    val donnee = data.getJSONObject(i)
        //    channels.add(Sound(donnee.get(1),donnee.get(0),donnee.get(2)))
        //}

        val channels = mutableListOf(
            Sound("Spotify", 0.75f, false),
            Sound("VLC", 0.33f, true),
            Sound("Discord", 0.75f, false),
            Sound("Android Studio", 0.33f, true),
            Sound("Counter-Strike: Global Offensive", 0.75f, false),
            Sound("Firefox", 0.33f, true),
            Sound("Sons systèmes", 0.75f, false),
        )

        val adapter = SoundAdapter(channels)
        rvSounds.adapter = adapter
        rvSounds.layoutManager = LinearLayoutManager(activity)


    }

}