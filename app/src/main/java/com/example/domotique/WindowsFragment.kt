package com.example.domotique

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.windows_fragment.*

class WindowsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.windows_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val windows = mutableListOf(
            Window("Fenêtre 1", true),
            Window("Fenêtre 2", false)
        )

        val adapter = WindowAdapter(windows)
        rvWindows.adapter = adapter
        rvWindows.layoutManager = LinearLayoutManager(activity)
    }
}