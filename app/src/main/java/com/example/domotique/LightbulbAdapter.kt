package com.example.domotique

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_lightbulb.view.*
import kotlinx.android.synthetic.main.item_sound.view.*
import vadiole.colorpicker.ColorModel
import vadiole.colorpicker.ColorPickerDialog

class LightbulbAdapter(
    private var lightbulbs: List<Lightbulb>
) : RecyclerView.Adapter<LightbulbAdapter.LightbulbViewHolder>(){

    inner class LightbulbViewHolder(lightbulbView: View) : RecyclerView.ViewHolder(lightbulbView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LightbulbViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lightbulb, parent, false)
        return LightbulbViewHolder(view)
    }

    override fun onBindViewHolder(holder: LightbulbViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.itemView.apply {
            val model = ViewModelProvider(context as MainActivity).get(Communicator::class.java)
            lightbulbName.text = lightbulbs[position].id
            lightbulbIntensity.progress = (lightbulbs[position].intensity * 100).toInt()
            var previousIntensity = lightbulbIntensity.progress
            lightbulbIntensity.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekbar : SeekBar?, progress : Int, fromUser : Boolean) {
                    val jsonUtils = JsonHandlers()
                    val differential =  progress.toFloat() - previousIntensity
                    println("diff : $differential")
                    //jsonUtils.updateLightBulb("http://${model.ip}:${model.port}/api/update_lightbulb", lightbulbs[position].id, progress, lightbulbs[position].color)
                    previousIntensity = progress
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
            colorPickerButton.background = lightbulbs[position].color.toArgb().toDrawable() //.toDrawable()
            colorPickerButton.setOnClickListener {

                val colorPicker: ColorPickerDialog = ColorPickerDialog.Builder()//  set Color Model. ARGB, RGB or HSV
                    //.setInitialColor(value)
                    .setColorModel(ColorModel.HSV)
                    .setColorModelSwitchEnabled(true)
                    .setButtonOkText(android.R.string.ok)
                    .setButtonCancelText(android.R.string.cancel)
                    .onColorSelected { color: Int ->
                        colorPickerButton.background = color.toDrawable()
                    }
                    .create()

                val fm = context as MainActivity

                colorPicker.show(fm.supportFragmentManager, "color_picker")
            }
        }
    }

    override fun getItemCount(): Int {
        return lightbulbs.size
    }
}