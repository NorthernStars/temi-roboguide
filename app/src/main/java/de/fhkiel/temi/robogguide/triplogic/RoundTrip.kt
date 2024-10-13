package de.fhkiel.temi.robogguide.triplogic

import android.util.Log
import android.widget.ProgressBar
import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener

class RoundTrip(
    private val mRobot: Robot,
    private val bar: ProgressBar,
    private val allStations: Boolean= false,
    private val isAusführlich: Boolean = false,
    var index: Int =0
) : OnGoToLocationStatusChangedListener {

    init {
        mRobot.goTo(mRobot.locations[0])
    }

    override fun onGoToLocationStatusChanged(
        location: String,
        status: String,
        descriptionId: Int,
        description: String) {
        if(status == OnGoToLocationStatusChangedListener.COMPLETE) {
            val ttsRequest: TtsRequest = TtsRequest.create(speech = "location: $location", isShowOnConversationLayer = false);
            mRobot.speak(ttsRequest)
            bar.progress = index * 100/mRobot.locations.size
        }
        Log.i(location, status);

    }
    }