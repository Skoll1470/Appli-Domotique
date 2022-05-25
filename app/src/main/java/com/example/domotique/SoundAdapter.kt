package com.example.domotique

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_lightbulb.view.*
import kotlinx.android.synthetic.main.item_sound.view.*

class SoundAdapter(
    private var channels: List<Sound>
) : RecyclerView.Adapter<SoundAdapter.SoundViewHolder>(){

    inner class SoundViewHolder(soundView: View) : RecyclerView.ViewHolder(soundView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sound, parent, false)
        return SoundViewHolder(view)
    }

    override fun onBindViewHolder(holder: SoundViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.itemView.apply {
            soundChannel.text = channels[position].name
            soundSeekBar.progress = (channels[position].volume * 100).toInt()
            soundSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekbar : SeekBar?, progress : Int, fromUser : Boolean) {
                    val jason = JsonHandlers()
                    jason.updateSoundAPP("adresse pour update les window", channels[position].name, progress.toFloat()/100.0f, channels[position].isNotMuted)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                    TODO("Not yet implemented")
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    TODO("Not yet implemented")
                }
            })
            muteSwitch.isChecked = channels[position].isNotMuted
            muteSwitch.setOnClickListener{
                val jason = JsonHandlers()
                jason.updateSoundAPP("adresse pour update les window", channels[position].name, channels[position].volume, !channels[position].isNotMuted)
            }
        }
    }

    override fun getItemCount(): Int {
        return channels.size
    }
}