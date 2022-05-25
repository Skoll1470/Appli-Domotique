package com.example.domotique

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.door_fragment.*

class DoorFragment: Fragment() {

    val jason = JsonHandlers()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.door_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val doors = mutableListOf(
            Door("Porte 1", true),
            Door("Porte 2", false)
        )

        //val data = jason.getObject("ADRESSE POUR REQUEST LES SONS")
        //val doors = mutableListOf()
        //for(i in 0..data.length-1){
        //    val donnee = data.getJSONObject(i)
        //    channels.add(Door(donnee.get(1),donnee.get(0)))
        //}

        val adapter = DoorAdapter(doors)
        rvDoors.adapter = adapter
        rvDoors.layoutManager = LinearLayoutManager(activity)
    }
}