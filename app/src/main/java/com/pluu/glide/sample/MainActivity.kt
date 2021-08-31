package com.pluu.glide.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pluu.glide.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClear.setOnClickListener {
            binding.iv.setImageDrawable(null)
        }

        binding.btnSample.setOnClickListener {
            Glide.with(this)
                .load("file:///android_asset/android_10.png")
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.iv)
        }

        binding.btnSample2.setOnClickListener {
            Glide.with(this)
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Apple_logo_grey.svg/647px-Apple_logo_grey.svg.png")
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.iv)
        }
    }
}