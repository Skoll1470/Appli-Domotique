package com.example.domotique

import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class VolumeGraphic {
    companion object{
        fun create(name : String, volume : Float, muted : Boolean, context : AppCompatActivity, linearLayout: LinearLayout){
            //creating parameters
            val params=LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )

            //creating Text View with app name
            val tv = TextView(context)
            tv.text=name
            tv.layoutParams=params
            linearLayout.addView(tv)

            //creating Seek Bar (slider) with value set to app current volume
            val sb = SeekBar(context)
            sb.progress = (volume*100).toInt()
            sb.layoutParams=params
            linearLayout.addView(sb)

            //creating Switch Button with value set to app muted state
            val cb = CheckBox(context)
            if(muted){
                cb.toggle()
            }
            cb.layoutParams=params
            linearLayout.addView(cb)
        }
    }
}