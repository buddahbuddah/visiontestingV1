package com.example.buddah.visiontesting

import org.junit.Test

import org.junit.Assert.*
import java.util.*


class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    //helper function for randomly pulling points from list.

    @Test
    fun testRandom() {
        // Context of the app under test.
        var x = 0
        var y = 0
        var finalCount = 0
        while (y < 10) {
            while (x < 60) {
                var temp = (1 until 5).random()
                if (temp % 4 == 0)
                    finalCount++
                x++
            }
            println("results")
            println(finalCount)
            x = 0
            finalCount = 0
            y++

        }

    }
}
