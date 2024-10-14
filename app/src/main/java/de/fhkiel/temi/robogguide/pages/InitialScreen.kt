package de.fhkiel.temi.robogguide.pages

import android.app.Activity
import android.widget.Button
import com.robotemi.sdk.Robot
import de.fhkiel.temi.robogguide.R


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
            val tourScreen = Tourscreen(context, robot, ::handleInitScreen, true, false)
            tourScreen.handleTourScreen()
        }
        context.findViewById<Button>(R.id.easyshort).setOnClickListener {
            val tourScreen = Tourscreen(context, robot, ::handleInitScreen, true, false)
            tourScreen.handleTourScreen()
        }
        context.findViewById<Button>(R.id.advancedlong).setOnClickListener {
            val tourScreen = Tourscreen(context, robot, ::handleInitScreen, true, true)
            tourScreen.handleTourScreen()
        }
        context.findViewById<Button>(R.id.advancedshort).setOnClickListener {
            val tourScreen = Tourscreen(context, robot, ::handleInitScreen, false, true)
            tourScreen.handleTourScreen()
        }
    }
}
