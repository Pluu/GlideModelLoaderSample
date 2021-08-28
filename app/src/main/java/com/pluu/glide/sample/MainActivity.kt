package com.pluu.glide.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pluu.glide.sample.databinding.ActivityMainBinding
import com.pluu.glide.sample.loader.Sample

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClear.setOnClickListener {
            binding.iv.setImageResource(R.drawable.ic_adb)
        }

        binding.btnSample.setOnClickListener {
            Glide.with(this)
                .load(Sample(">>>>>"))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.iv)
        }
    }
}