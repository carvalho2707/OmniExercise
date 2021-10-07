package pt.tiagocarvalho.omni

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class OmniGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        // Since omni images are public we can store the cache in external storage
        builder.setDiskCache(ExternalPreferredCacheDiskCacheFactory(context))
    }
}
