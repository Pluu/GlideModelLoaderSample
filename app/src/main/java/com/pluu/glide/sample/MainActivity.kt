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

        binding.btnDrawable.setOnClickListener {
            Glide.with(this)
                .load(R.drawable.ic_android)
                .into(binding.iv)
        }

        binding.btnAsset.setOnClickListener {
            Glide.with(this)
                .load("file:///android_asset/android_10.png")
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.iv)
        }

        binding.btnCustom.setOnClickListener {
            Glide.with(this)
                .load("https://source.android.com/setup/images/Android_symbol_green_RGB.png")
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.iv)
        }
    }
}