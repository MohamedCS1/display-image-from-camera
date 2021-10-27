package com.example.imagedisplay

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    var imageview:ImageView? = null
    var button:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageview = findViewById(R.id.dispaly_image)
        button = findViewById(R.id.bu_get_image)

        val getresult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            if (it.resultCode == RESULT_OK)
            {
                val bitmap = it.data?.extras?.get("data")
                imageview!!.setImageBitmap(bitmap as Bitmap)
            }
        }

        button!!.setOnClickListener {
            val intent = Intent()
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE
            getresult.launch(intent)
        }
    }
}