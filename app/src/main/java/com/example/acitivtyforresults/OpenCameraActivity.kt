package com.example.acitivtyforresults

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.acitivtyforresults.databinding.ActivityMainBinding

class OpenCameraActivity : AppCompatActivity(), View.OnClickListener {

        private lateinit var binding: ActivityMainBinding

        private val CAMERA_REQUEST = 1888
        private val MY_CAMERA_PERMISSION_CODE = 100

        private val TAG = "MainActivity"
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.btn.setOnClickListener(this)
        }


        @RequiresApi(Build.VERSION_CODES.M)
        override fun onClick(p0: View?) {
            when (p0) {
                binding.btn -> {
                    openCamera()
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        private fun openCamera() {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
            } else {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }

        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode === MY_CAMERA_PERMISSION_CODE) {
                if (grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show()
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, CAMERA_REQUEST)
                } else {
                    Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()
                }
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode === CAMERA_REQUEST && resultCode === RESULT_OK) {
                val photo: Bitmap = data?.extras?.get("data") as Bitmap
//                binding.name.setImageBitmap(photo)
            }
        }

}