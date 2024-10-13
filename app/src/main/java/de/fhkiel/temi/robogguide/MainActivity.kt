package de.fhkiel.temi.robogguide

import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.robotemi.sdk.Robot
import com.robotemi.sdk.listeners.OnRobotReadyListener
import de.fhkiel.temi.robogguide.database.DatabaseHelper
import de.fhkiel.temi.robogguide.pages.InitialScreen
import java.io.IOException

// ADB Connect (ip address)
    class MainActivity : AppCompatActivity(), OnRobotReadyListener {
    private var mRobot: Robot? = null
    private lateinit var database: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_screen)

        // use database
        val databaseName = "roboguide.db"
        database = DatabaseHelper(this, databaseName)

        try {
            database.initializeDatabase() // Initialize the database and copy it from assets

            /*
            // EXAMPLE CODE TO ONLY COPY DATABASE TO DIRECTLY USE THE DATABASE FILE
            database.initializeDatabase(withOpen = false)
            val dbFile = database.getDBFile()
            val sqLiteDatabase = database.getDatabase()
            */

            val places = database.getTableDataAsJson("places") // Fetch data as JSON
            val locations = database.getTableDataAsJson("locations") // Fetch data as JSON
            val texts = database.getTableDataAsJson("texts") // Fetch data as JSON

            Log.i("MainActivity", "Places: $places")
            Log.i("MainActivity", "Locations: $locations")
            Log.i("texts", "Locations: $texts")

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun onStart() {
        super.onStart()
        Robot.getInstance().addOnRobotReadyListener(this)
    }


    override fun onStop() {
        super.onStop()
        Robot.getInstance().removeOnRobotReadyListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        database.closeDatabase()
    }

    override fun onRobotReady(isReady: Boolean) {
        if (isReady){
            mRobot = Robot.getInstance()
            mRobot?.hideTopBar()

            val activityInfo: ActivityInfo = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
            Robot.getInstance().onStart(activityInfo)
            mRobot?.let { robot-> run {
                val initScreen: InitialScreen = InitialScreen(this,  robot);
                initScreen.handleInitScreen();
            } }
            Log.i("Robot", mRobot?.locations.toString())
            //mRobot?.goTo(mRobot?.locations?.get(0) ?: "");

        }
    }
}