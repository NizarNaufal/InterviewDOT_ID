package id.interview.dotid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.interview.dotid.support.showActivity
import id.interview.dotid.view.HomeActivity
import java.util.*

class Root : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val myTimer = Timer()
        myTimer.schedule(object : TimerTask() {
            override fun run() {
                // If you want to modify a view in your Activity
                runOnUiThread {
                    showActivity(HomeActivity::class.java)
                }
            }
        }, 500)
    }
}