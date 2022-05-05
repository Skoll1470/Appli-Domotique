package com.example.domotique

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColorInt
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
            colorPickerButton.foreground = lightbulbs[position].color.toDrawable()
            colorPickerButton.setOnClickListener {

                val currentColor = it.foreground.toString().split("@")[1].toLong(16)
                val value = Color.valueOf(currentColor).toArgb()

                println("[Current color] : $currentColor")

                val colorPicker: ColorPickerDialog = ColorPickerDialog.Builder()//  set Color Model. ARGB, RGB or HSV
                    .setInitialColor(value)
                    .setColorModel(ColorModel.HSV)
                    .setColorModelSwitchEnabled(true)
                    .setButtonOkText(android.R.string.ok)
                    .setButtonCancelText(android.R.string.cancel)
                    .onColorSelected { color: Int ->
                        colorPickerButton.foreground = color.toDrawable()
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