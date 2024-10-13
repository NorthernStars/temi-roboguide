package de.fhkiel.temi.robogguide.pages

import android.app.Activity
import android.widget.Button
import android.widget.LinearLayout
import com.robotemi.sdk.Robot
import de.fhkiel.temi.robogguide.LocationButtonManager
import de.fhkiel.temi.robogguide.R

class IndividualTourScreen(
                        private val activity: Activity,
                        private val mRobot: Robot,
                        private val handleInitScreen: () -> Unit) {

    public fun handleIndivTourScreen() {
        activity.setContentView(R.layout.all_locations)

        val layout = activity.findViewById<LinearLayout>(R.id.listoflocations)
        val locationButtonManager = LocationButtonManager(activity, mRobot)
        locationButtonManager.populateLocationButtons(layout)

        val back = activity.findViewById<Button>(R.id.backbuttonindiv)
        back.setOnClickListener {

            handleInitScreen()
        }
    }
}