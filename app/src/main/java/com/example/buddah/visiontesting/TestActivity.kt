package com.example.buddah.visiontesting

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager

class TestActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.TYPE_APPLICATION_MEDIA)

        setContentView(R.layout.activity_test)
    }

}
