package com.pluu.glide.sample.loader

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import java.io.InputStream

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        // GlideUrl Loader
//        registry.prepend(
//            GlideUrl::class.java,
//            InputStream::class.java,
//            CustomGlideUrlModelLoader.Factory()
//        )
        // Uri Loader
//        registry.prepend(
//            String::class.java,
//            Uri::class.java,
//            CustomUriModelLoader.Factory(context.resources)
//        )
        // Assert Loader
        registry.prepend(
            String::class.java,
            InputStream::class.java,
            CustomAssetModelLoader.Factory()
        )
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.setLogLevel(Log.VERBOSE)
    }
}
