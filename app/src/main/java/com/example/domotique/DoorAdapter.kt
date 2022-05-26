package com.example.domotique

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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
            var previousClosed = doorToggle.isChecked
            doorToggle.setOnClickListener{
                val model = ViewModelProvider(context as MainActivity).get(Communicator::class.java)
                val jsonUtils = JsonHandlers()
                previousClosed = !previousClosed
                jsonUtils.updateDoor("http://${model.ip}:${model.port}/api/update_door", doors[position].id, !previousClosed)
            }
        }
    }

    override fun getItemCount(): Int {
        return doors.size
    }


}