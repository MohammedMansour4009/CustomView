package com.example.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCheck.setOnClickListener {
            binding.spinnerArea.checkView()
            binding.spinnerSize.checkView()
            binding.spinnerCurb.checkView()
        }


        binding.customRecyclerView.apply {
            recyclerView.adapter =
        }

    }


}