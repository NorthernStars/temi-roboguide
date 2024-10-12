package de.fhkiel.temi.robogguide

import android.util.Log
import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener

class RoundTrip(private val mRobot: Robot) : OnGoToLocationStatusChangedListener {

    init {
        mRobot.goTo(mRobot.locations[0])
    }

    override fun onGoToLocationStatusChanged(
        location: String,
        status: String,
        descriptionId: Int,
        description: String
    ) {
        if(status == OnGoToLocationStatusChangedListener.COMPLETE) {
            val x = (mRobot.locations.indexOf(location) + 1) % mRobot.locations.size;
            // mRobot.goTo(mRobot.locations[x]);
            val ttsRequest: TtsRequest = TtsRequest.create(speech = "location: $location", isShowOnConversationLayer = false);
            mRobot.speak(ttsRequest);
        }
        Log.i(location, status);

    }
    }