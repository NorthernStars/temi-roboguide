package de.fhkiel.temi.robogguide.pages

import android.app.Activity
import android.widget.Button
import com.robotemi.sdk.Robot
import de.fhkiel.temi.robogguide.R
import de.fhkiel.temi.robogguide.triplogic.RoundTrip


class InitialScreen(
    private val context: Activity,
    private val robot: Robot) {

    fun handleInitScreen() {
        context.setContentView(R.layout.first_screen)

        context.findViewById<Button>(R.id.individual)?.setOnClickListener {
            robot.let {
                val indivTour = IndividualTourScreen(context, it, ::handleInitScreen)
                indivTour.handleIndivTourScreen()
            }
        }

        context.findViewById<Button>(R.id.easylong).setOnClickListener {

            val tourScreen: Tourscreen = Tourscreen(context, robot, ::handleInitScreen, false, false)
            tourScreen.handleTourScreen()
        }
    }
}
