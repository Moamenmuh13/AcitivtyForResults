package com.example.acitivtyforresults

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.acitivtyforresults.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    private  val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btn.setOnClickListener(this)
    }


    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val returnString = it.data!!.getStringExtra("input")
                binding.name.text = returnString
                Log.d(TAG, "Text: $binding.name")


            }
        }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.btn -> {
                val i = Intent(this , SecondActivity::class.java)
                getResult.launch(i)
            }
        }
    }
}