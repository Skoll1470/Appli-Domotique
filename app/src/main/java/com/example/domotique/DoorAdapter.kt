package com.example.domotique

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_door.view.*
import kotlinx.android.synthetic.main.item_window.view.*

class DoorAdapter(
    private var doors: List<Door>
) : RecyclerView.Adapter<DoorAdapter.DoorViewHolder>(){

    inner class DoorViewHolder(doorView: View) : RecyclerView.ViewHolder(doorView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_door, parent, false)
        return DoorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoorViewHolder, position: Int) {
        holder.itemView.apply {
            doorName.text = doors[position].id
            doorToggle.isChecked = doors[position].toggle
        }
    }

    override fun getItemCount(): Int {
        return doors.size
    }


}