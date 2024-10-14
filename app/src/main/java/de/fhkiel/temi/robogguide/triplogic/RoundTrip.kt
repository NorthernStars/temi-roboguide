package de.fhkiel.temi.robogguide.triplogic

import android.app.Activity
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener
import de.fhkiel.temi.robogguide.R

class RoundTrip(
    private val mRobot: Robot,
    private val bar: ProgressBar,
    private val allStations: Boolean= false,
    private val isAusführlich: Boolean = false,
    private val isIndividual: Boolean = false,
    var index: Int =0,
    private val locations: List<String> = mRobot.locations,
    private val activity: Activity
    ) : OnGoToLocationStatusChangedListener {

    init {
        mRobot.goTo(locations[0])
    }

    override fun onGoToLocationStatusChanged(
        location: String,
        status: String,
        descriptionId: Int,
        description: String) {
        if(status == OnGoToLocationStatusChangedListener.COMPLETE) {
            val ttsRequest: TtsRequest = TtsRequest.create(speech = "location: $location", isShowOnConversationLayer = false);
            mRobot.speak(ttsRequest)
            bar.progress = index * 100 / locations.size
        }
        Log.i("Movement", "$descriptionId: $description, $status");
        if(descriptionId in 2000..2009)activity.findViewById<TextView>(R.id.error_text).text= description
         else if (descriptionId == 500) activity.findViewById<TextView>(R.id.error_text).text = ""

    }
}