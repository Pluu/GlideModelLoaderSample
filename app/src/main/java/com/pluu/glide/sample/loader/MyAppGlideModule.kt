package com.pluu.glide.sample.loader

import android.content.Context
import android.net.Uri
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
//        registry.prepend(
//            GlideUrl::class.java,
//            InputStream::class.java,
//            CustomGlideUrlModelLoader.Factory()
//        )
        registry.prepend(
            String::class.java,
            Uri::class.java,
            CustomUriModelLoader.Factory(context.resources)
        )
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.setLogLevel(Log.VERBOSE)
    }
}
