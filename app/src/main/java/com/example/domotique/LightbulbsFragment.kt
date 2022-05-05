package com.example.domotique

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.lightbulb_fragment.*
import kotlinx.android.synthetic.main.windows_fragment.*
import vadiole.colorpicker.ColorModel
import vadiole.colorpicker.ColorPickerDialog

class LightbulbsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lightbulb_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lightbulbs = mutableListOf(
            Lightbulb("Ampoule Salon", .6f, Color.valueOf(0x893746)),
            Lightbulb("Ampoule chambre 1", 1f, Color.valueOf(0xffffff))
        )

        val adapter = LightbulbAdapter(lightbulbs)
        rvLightbulbs.adapter = adapter
        rvLightbulbs.layoutManager = LinearLayoutManager(activity)


    }
}