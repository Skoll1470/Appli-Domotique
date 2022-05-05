package com.example.domotique

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_sound.view.*

class SoundAdapter(
    private var channels: List<Sound>
) : RecyclerView.Adapter<SoundAdapter.SoundViewHolder>(){

    inner class SoundViewHolder(soundView: View) : RecyclerView.ViewHolder(soundView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sound, parent, false)
        return SoundViewHolder(view)
    }

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        holder.itemView.apply {
            soundChannel.text = channels[position].name
            soundSeekBar.progress = (channels[position].volume * 100).toInt()
            muteSwitch.isChecked = channels[position].isNotMuted
        }
    }

    override fun getItemCount(): Int {
        return channels.size
    }
}