package de.fhkiel.temi.robogguide

import android.content.Context
import android.util.Log
import android.widget.LinearLayout
import com.google.android.material.switchmaterial.SwitchMaterial
import com.robotemi.sdk.Robot

class LocationToggleManager(private val context: Context, private val mRobot: Robot?) {
    val toggledList = mutableListOf<String>()

    fun populateLocationToggles(layout: LinearLayout) {

        mRobot?.locations?.forEach { location ->
            val button = SwitchMaterial(context).apply {
                text = location
                setOnClickListener {
                    Log.i("moveTo", text.toString())
                    if(toggledList.contains(location))toggledList.remove(location)
                    else toggledList.add(location)
                    Log.i("Tour", toggledList.toString())
                }
            }
            layout.addView(button)
        }
    }
}