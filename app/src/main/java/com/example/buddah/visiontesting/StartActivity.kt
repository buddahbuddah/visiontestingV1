package com.example.buddah.visiontesting

import android.content.Intent
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
            //setContentView(R.layout.activity_test)
            LaunchTest()
        }

        val configureButton = findViewById<Button>(R.id.Configuration)
        configureButton.setOnClickListener {
            LaunchConfiguration()
        }

        val checkButton = findViewById<Button>(R.id.Check)
        checkButton.setOnClickListener {
            Check()
        }

    }

    private fun LaunchTest(){
        val intent = Intent(this, TestActivity::class.java)
        startActivity(intent)
    }

    private fun LaunchConfiguration(){
        val intent = Intent(this, ConfigureActivity::class.java)
        startActivity(intent)
    }


    private fun Check(){

        val fIn = openFileInput("configure.txt")
        val fileContentsInt = fIn.read()
        fIn.close()
        println("The value of configure file is: " + fileContentsInt)
    }

}
