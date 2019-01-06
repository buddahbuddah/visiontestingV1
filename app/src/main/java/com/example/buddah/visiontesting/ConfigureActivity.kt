package com.example.buddah.visiontesting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.app.Activity
import android.content.Context
import android.view.WindowManager


class ConfigureActivity : Activity(), View.OnClickListener {


    internal var image_index = 0
    internal var toSave = 0

    private val mImageIds = intArrayOf(R.drawable.redcircle25px, R.drawable.redcircle10px)

    public override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_configure)

        val btnPrevious = findViewById<View>(R.id.previous_btn) as Button
        btnPrevious.setOnClickListener(this)
        val btnNext = findViewById<View>(R.id.next_btn) as Button
        btnNext.setOnClickListener(this)
        val btnSave = findViewById<View>(R.id.save_btn) as Button
        btnSave.setOnClickListener(this)


        showImage()

    }

    private fun showImage() {

        val imgView = findViewById<View>(R.id.imageView2 ) as ImageView
        imgView.setImageResource(mImageIds[image_index])

    }

    private fun Save(toSave : Int) {
        //saves to cache
        val fOut = openFileOutput("configure.txt", Context.MODE_PRIVATE)
        fOut.write(toSave)
        fOut.close()
        //this.openFileOutput("temp.txt", Context.MODE_PRIVATE).use {
        //    it.write(toSave)
        //}
    }
    override fun onClick(v: View) {

        when (v.id) {

            R.id.previous_btn -> {

                image_index--

                if (image_index == -1) {
                    image_index = MAX_IMAGE_COUNT - 1
                }

                showImage()
            }

            R.id.next_btn -> {

                image_index++

                if (image_index == MAX_IMAGE_COUNT) {
                    image_index = 0
                }

                showImage()
            }

            R.id.save_btn -> {

                toSave = image_index
                Save(toSave)

            }
        }

    }

    companion object {
        private val MAX_IMAGE_COUNT = 2
    }
}