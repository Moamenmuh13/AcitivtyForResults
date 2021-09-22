package com.example.acitivtyforresults

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley.newRequestQueue
import com.example.acitivtyforresults.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding


    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding
        binding.btn.setOnClickListener(this)
        binding.get.setOnClickListener(this)
    }


    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val returnString = it.data!!.getStringExtra("input")
                binding.name.text = returnString
            }
        }

    override fun onClick(p0: View?) {

        when (p0) {
            binding.btn -> {
                val i = Intent(this, SecondActivity::class.java)
                getResult.launch(i)
            }
            binding.get -> {
                stringRequest()

            }
        }
    }

    private fun stringRequest() {
        val queue = newRequestQueue(this)
        val url = "https://www.google.com"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                binding.name.text = "Response is Complete"
            },
            {
                binding.name.text = "That didn't work!"
            })
        queue.add(stringRequest)
    }

}