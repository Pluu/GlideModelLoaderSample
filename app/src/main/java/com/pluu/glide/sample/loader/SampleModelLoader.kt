package com.pluu.glide.sample.loader

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import java.io.InputStream

class SampleModelLoader(
    private val uriLoader: ModelLoader<GlideUrl, InputStream>
) : ModelLoader<Sample, InputStream> {
    private val TAG = "Base64ModelLoader"

    override fun buildLoadData(
        model: Sample,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<InputStream>? {
        val url =
            GlideUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/2346px-Android_logo_2019_%28stacked%29.svg.png")
        return uriLoader.buildLoadData(url, width, height, options)
    }

    override fun handles(model: Sample): Boolean {
        return true
    }

    class Factory : ModelLoaderFactory<Sample, InputStream> {

        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<Sample, InputStream> {
            return SampleModelLoader(
                multiFactory.build(GlideUrl::class.java, InputStream::class.java)
            )
        }

        override fun teardown() {
            // Do nothing
        }
    }
}

