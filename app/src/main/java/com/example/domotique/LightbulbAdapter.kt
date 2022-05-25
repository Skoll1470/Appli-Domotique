package com.example.domotique

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_lightbulb.view.*
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

    override fun onBindViewHolder(holder: LightbulbViewHolder, position: Int) {
        holder.itemView.apply {
            lightbulbName.text = lightbulbs[position].id
            lightbulbIntensity.progress = (lightbulbs[position].intensity * 100).toInt()
            lightbulbIntensity.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekbar : SeekBar?, progress : Int, fromUser : Boolean) {
                    val jason = JsonHandlers()
                    //jason.updateSoundAPP("adresse pour update les window", lightbulbs[position].id, progress.toFloat()/100.0f, lightbulbs[position].color)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                    TODO("Not yet implemented")
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    TODO("Not yet implemented")
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