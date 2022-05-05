package com.example.domotique

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.door_fragment.*

class DoorFragment: Fragment() {
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

        val adapter = DoorAdapter(doors)
        rvDoors.adapter = adapter
        rvDoors.layoutManager = LinearLayoutManager(activity)
    }
}