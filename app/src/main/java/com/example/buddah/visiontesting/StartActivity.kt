package com.example.buddah.visiontesting

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import kotlinx.android.synthetic.main.start_activity.*


class StartActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.start_activity)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            setContentView(R.layout.activity_main)
        }

        val configureButton = findViewById<Button>(R.id.configureButton)
        configureButton.setOnClickListener {
            //setContentView(R.layout.)
        }
    }
}

