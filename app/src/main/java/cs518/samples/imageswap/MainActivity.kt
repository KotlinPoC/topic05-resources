package cs518.samples.imageswap

/**
 * App uses various resources
 * drawables
 * colour
 * string
 * raw     (sound)
 *
 * in xml:
 * use an alternate layout  landscape / portrait
 * use dimensions in layout
 * @author tricia
 *
 * TODO: fix the way I deal with the mediaplayer
 */

import android.media.MediaPlayer
import android.os.Bundle
import android.app.Activity
import android.content.pm.ActivityInfo
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    internal lateinit var bt01: ImageButton
    internal lateinit var bt02: ImageButton
    internal lateinit var tv: TextView
    internal var mp: MediaPlayer? = null
    internal var bt01image: Int = 0
    internal var bt01colour: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt01 = findViewById(R.id.imageButton1) as ImageButton
        bt02 = findViewById(R.id.imageButton2) as ImageButton

        tv = findViewById(R.id.message) as TextView
        mp = MediaPlayer.create(this, R.raw.meow)
        if (mp == null) {
            val bt03 = findViewById(R.id.imageButton3) as ImageButton
            // if problem with media player then disable tard button
            bt03.isEnabled = false
        }
        val name = resources.getResourceEntryName(R.drawable.cleanallboo)
        Log.d("name", name)
        bt01image = R.drawable.cleanallboo
        bt01colour = R.color.indigo
        swapimage()
    }

    override fun onPause() {
        super.onPause()
        // releasing the MediaPlayer resources
        if (mp != null) {
            try {
                mp!!.release()
            } catch (e: Exception) {
                Log.d(TAG, "Mediaplayer release " + e.toString())
            }

        }
    }

    fun button1Clicked(view: View) {
        Toast.makeText(this, "Button1 clicked", Toast.LENGTH_SHORT).show()
        swapimage()
    }

    fun button2Clicked(view: View) {
        Toast.makeText(this, "Button2 clicked", Toast.LENGTH_SHORT).show()
        swapimage()
    }

    fun button3Clicked(view: View) {
        Toast.makeText(this, "Button3 clicked", Toast.LENGTH_LONG).show()
        try {
            mp!!.start()
        } catch (e1: Exception) {
            Log.d(TAG, "Mediaplayer start failed ")
            try {
                mp!!.reset()
                e1.printStackTrace()
            } catch (e2: Exception) {
                Log.d(TAG, "Mediaplayer reset  failed ")
                e2.printStackTrace()
                Toast.makeText(this, "Meow failed, rotate then try again", Toast.LENGTH_SHORT).show()
            }

        }

    }

    fun swapimage() {
        // swap the two images in place and changes the message text colour.
        if (bt01image == R.drawable.cleanallboo) {
            bt01.setImageResource(R.drawable.cleanallyay)
            bt02.setImageResource(R.drawable.cleanallboo)
            bt01image = R.drawable.cleanallyay
            tv.setTextColor(resources.getColor(R.color.lightcoral))

        } else {
            bt02.setImageResource(R.drawable.cleanallyay)
            bt01.setImageResource(R.drawable.cleanallboo)
            bt01image = R.drawable.cleanallboo
            tv.setTextColor(resources.getColor(R.color.indigo))
        }
        val name = resources.getResourceEntryName(bt01image)
        Log.d("button 1 image name", name)
    }

    fun rotateScreen(view: View) {
        if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        else
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }  //rotateScreen()

    companion object {
        val TAG = "IMGSWP"
    }

}
