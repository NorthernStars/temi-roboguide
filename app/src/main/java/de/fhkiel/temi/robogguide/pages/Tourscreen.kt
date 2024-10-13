package de.fhkiel.temi.robogguide.pages

import android.app.Activity
import android.widget.Button
import android.widget.ProgressBar
import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest
import de.fhkiel.temi.robogguide.R
import de.fhkiel.temi.robogguide.triplogic.RoundTrip

class Tourscreen(private val context: Activity,
                 private val robot: Robot,
                 private val handleInitScreen: () -> Unit,
                 private val allStations: Boolean= false,
                 private val isAusführlich: Boolean = false,
) {

     fun handleTourScreen() {
        context.setContentView(R.layout.tour_screen)
        val bar = context.findViewById<ProgressBar>(R.id.progressBar)

        val trip = RoundTrip(robot, bar, allStations, isAusführlich, 0)
        robot.addOnGoToLocationStatusChangedListener(trip)

        val backButton = context.findViewById<Button>(R.id.backbutton)
        backButton.setOnClickListener {
            handleBackAction(trip)
            handleInitScreen()
        }

        val continueButton = context.findViewById<Button>(R.id.continuebutton)
        continueButton.setOnClickListener{
            robot.cancelAllTtsRequests();
            trip.index = (trip.index+1) % robot.locations.size
            robot.goTo(robot.locations[trip.index]);
        }
    }

    private fun handleBackAction(trip: RoundTrip) {
        context.setContentView(R.layout.first_screen)

        robot.removeOnGoToLocationStatusChangedListener(trip)
        robot.goTo(robot.locations.getOrNull(0) ?: "")
        val ttsRequest = TtsRequest.create(
            speech = "Okay! Ich gehe dann wieder zum Anfang. Viel Spaß im Museum!",
            isShowOnConversationLayer = false
        )
        robot.speak(ttsRequest)
    }
}