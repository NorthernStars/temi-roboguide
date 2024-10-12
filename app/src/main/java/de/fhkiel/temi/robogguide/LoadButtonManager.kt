package de.fhkiel.temi.robogguide

import android.content.Context
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import com.robotemi.sdk.Robot

class LocationButtonManager(private val context: Context, private val mRobot: Robot?) {

    fun populateLocationButtons(layout: LinearLayout) {
        mRobot?.locations?.forEach { location ->
            Log.i("buttonCreation", location)
            val button = Button(context).apply {
                text = location
                setOnClickListener {
                    Log.i("moveTo", text.toString())
                    mRobot.goTo(text.toString())
                }
            }
            layout.addView(button)
        }
    }
}