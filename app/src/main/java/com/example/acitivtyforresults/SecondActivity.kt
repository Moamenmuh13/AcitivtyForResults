package com.example.acitivtyforresults

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.acitivtyforresults.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.done.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.done -> {
                sendResult()
            }
        }
    }

    private fun sendResult() {
        val personName = binding.editTextTextPersonName.text.toString()
        val intent = Intent()
        intent.putExtra("input", personName)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }


}