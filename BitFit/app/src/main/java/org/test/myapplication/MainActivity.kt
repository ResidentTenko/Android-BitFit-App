package org.test.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // go to TrailDetailActivity on click
        val newTrailButton = findViewById<Button>(R.id.addTrail)
        newTrailButton.setOnClickListener{
            val intent = Intent(this, TrailDetailActivity::class.java)
            startActivity(intent)
        }
    }
}