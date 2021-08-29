package com.pluu.glide.sample.loader

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import java.io.InputStream

class CustomGlideUrlModelLoader(
    private val uriLoader: ModelLoader<GlideUrl, InputStream>
) : ModelLoader<GlideUrl, InputStream> {
    private val TAG = CustomGlideUrlModelLoader::class.java.simpleName

    override fun buildLoadData(
        model: GlideUrl,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<InputStream>? {
        return uriLoader.buildLoadData(
            GlideUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/640px-Google_2015_logo.svg.png"),
            width,
            height,
            options
        )
    }

    override fun handles(model: GlideUrl): Boolean {
        return true
    }

    class Factory : ModelLoaderFactory<GlideUrl, InputStream> {

        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<GlideUrl, InputStream> {
            return CustomGlideUrlModelLoader(
                multiFactory.build(GlideUrl::class.java, InputStream::class.java)
            )
        }

        override fun teardown() {
            // Do nothing
        }
    }
}
