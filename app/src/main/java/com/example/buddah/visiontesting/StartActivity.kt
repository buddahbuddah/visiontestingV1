package com.example.buddah.visiontesting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_start)


        val startButton = findViewById<Button>(R.id.StartTest)
        startButton.setOnClickListener {
            setContentView(R.layout.activity_test)
        }

        val configureButton = findViewById<Button>(R.id.Configuration)

        startButton.setOnClickListener {
            setContentView(R.layout.activity_test)
        }
    }

}
