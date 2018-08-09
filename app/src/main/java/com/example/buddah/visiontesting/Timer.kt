package com.example.buddah.visiontesting

import android.os.Handler


object Timer {

    // Delay mechanism

    interface DelayCallback {
        fun afterDelay()
    }

    fun delay(secs: Int, delayCallback: DelayCallback) {
        val handler = Handler()
        handler.postDelayed({ delayCallback.afterDelay() }, (secs * 1000).toLong()) // afterDelay will be executed after (secs*1000) milliseconds.
    }
}