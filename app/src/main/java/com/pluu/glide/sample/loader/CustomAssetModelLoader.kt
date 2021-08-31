package com.pluu.glide.sample.loader

import android.content.ContentResolver
import android.content.res.Resources.NotFoundException
import android.net.Uri
import android.util.Log
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import java.io.InputStream

class CustomAssetModelLoader(
    private val uriLoader: ModelLoader<Uri, InputStream>
) : ModelLoader<String, InputStream> {
    private val TAG = CustomAssetModelLoader::class.java.simpleName

    private val ASSET_PATH_SEGMENT = "android_asset"
    private val ASSET_PREFIX = ContentResolver.SCHEME_FILE + ":///" + ASSET_PATH_SEGMENT + "/"

    override fun buildLoadData(
        model: String,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<InputStream>? {
        val uri = getAssetUri(model)
        return if (uri == null) {
            null
        } else {
           return uriLoader.buildLoadData(uri, width, height, options)
        }
    }

    private fun getAssetUri(model: String): Uri? {
        return try {
            // file:///android_asset/android_10.png
            Uri.parse(ASSET_PREFIX + "android_studio.png")
        } catch (e: NotFoundException) {
            Log.w(TAG, "Received invalid asset file: $model", e)
            null
        }
    }

    override fun handles(model: String): Boolean {
        return true
    }

    class Factory : ModelLoaderFactory<String, InputStream> {

        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<String, InputStream> {
            return CustomAssetModelLoader(
                multiFactory.build(Uri::class.java, InputStream::class.java)
            )
        }

        override fun teardown() {
            // Do nothing
        }
    }
}