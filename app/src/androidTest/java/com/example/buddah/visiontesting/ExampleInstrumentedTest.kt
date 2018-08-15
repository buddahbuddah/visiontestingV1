package com.example.buddah.visiontesting

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.buddah.visiontesting", appContext.packageName)
    }


    @Test
    fun testRandom() {
        // Context of the app under test.
        var x = 0
        while (x < 10) {
            println((0 until 4).random())
            x++
        }
    }
}
//helper function for randomly pulling points from list.
fun ClosedRange<Int>.random() =
        Random().nextInt((endInclusive+1)-start) + start