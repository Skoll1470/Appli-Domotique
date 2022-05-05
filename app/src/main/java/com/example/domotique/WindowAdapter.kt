package com.example.domotique

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_window.view.*

class WindowAdapter(
    private var windows: List<Window>
) : RecyclerView.Adapter<WindowAdapter.WindowViewHolder>(){

    inner class WindowViewHolder(windowView: View) : RecyclerView.ViewHolder(windowView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_window, parent, false)
        return WindowViewHolder(view)
    }

    override fun onBindViewHolder(holder: WindowViewHolder, position: Int) {
        holder.itemView.apply {
            windowName.text = windows[position].id
            windowToggle.isChecked = windows[position].toggle
        }
    }

    override fun getItemCount(): Int {
        return windows.size
    }


}