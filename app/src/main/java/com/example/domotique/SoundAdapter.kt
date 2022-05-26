package com.example.domotique

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_sound.view.*

const val MAXLENGTH = 15

class SoundAdapter(
    private var channels: List<Sound>,
) : RecyclerView.Adapter<SoundAdapter.SoundViewHolder>(){

    inner class SoundViewHolder(soundView: View) : RecyclerView.ViewHolder(soundView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sound, parent, false)
        return SoundViewHolder(view)
    }

    override fun onBindViewHolder(holder: SoundViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.itemView.apply {
            val model = ViewModelProvider(context as MainActivity).get(Communicator::class.java)
            val title = channels[position].name
            soundChannel.text = if (title.length >= MAXLENGTH) "${title.substring(0, MAXLENGTH)}...${title.substring(title.length - 7)}" else title
            soundSeekBar.progress = (channels[position].volume * 100).toInt()
            var previousVolume = soundSeekBar.progress
            soundSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekbar : SeekBar?, progress : Int, fromUser : Boolean) {
                    val jsonUtils = JsonHandlers()
                    val differential =  progress.toFloat() - previousVolume
                    println("diff : $differential")
                    jsonUtils.updateSoundAPP("http://${model.ip}:${model.port}/api/update_volume", channels[position].name, differential/100f, channels[position].isNotMuted)
                    previousVolume = progress
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                    println("Not yet implemented")
                    //TODO("Not yet implemented")
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    println("Not yet implemented")
                    //TODO("Not yet implemented")
                }
            })
            muteSwitch.isChecked = channels[position].isNotMuted
            var previousMute = muteSwitch.isChecked
            muteSwitch.setOnClickListener{
                val model = ViewModelProvider(context as MainActivity).get(Communicator::class.java)
                val jsonUtils = JsonHandlers()
                previousMute = !previousMute
                jsonUtils.updateSoundAPP("http://${model.ip}:${model.port}/api/update_volume", channels[position].name, 0f, !previousMute)
            }
        }
    }

    override fun getItemCount(): Int {
        return channels.size
    }
}