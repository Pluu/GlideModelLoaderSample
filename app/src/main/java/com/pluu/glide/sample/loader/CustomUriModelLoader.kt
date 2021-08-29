package com.pluu.glide.sample.loader

import android.content.ContentResolver
import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import android.net.Uri
import android.util.Log
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.pluu.glide.sample.R

class CustomUriModelLoader(
    private val resources: Resources,
    private val uriLoader: ModelLoader<Uri, Uri>
) : ModelLoader<String, Uri> {
    private val TAG = CustomUriModelLoader::class.java.simpleName

    override fun buildLoadData(
        model: String,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<Uri>? {
        val uri = getResourceUri(R.drawable.ic_florist)
        return if (uri == null) {
            null
        } else {
            uriLoader.buildLoadData(uri, width, height, options)
        }
    }

    private fun getResourceUri(model: Int): Uri? {
        return try {
            Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE
                        + "://"
                        + resources.getResourcePackageName(model)
                        + '/'
                        + resources.getResourceTypeName(model)
                        + '/'
                        + resources.getResourceEntryName(model)
            )
        } catch (e: NotFoundException) {
            Log.w(TAG, "Received invalid resource id: $model", e)
            null
        }
    }

    override fun handles(model: String): Boolean {
        return true
    }

    class Factory(
        private val resources: Resources
    ) : ModelLoaderFactory<String, Uri> {

        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<String, Uri> {
            return CustomUriModelLoader(
                resources,
                multiFactory.build(Uri::class.java, Uri::class.java)
            )
        }

        override fun teardown() {
            // Do nothing
        }
    }
}