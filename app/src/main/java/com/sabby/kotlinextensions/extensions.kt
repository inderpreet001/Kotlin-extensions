package com.sabby.kotlinextensions

import android.media.Image
import android.widget.ImageView
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.Placeholder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

data class GlideImageOptions(
    @DrawableRes val errorHolder: Int,
    @DrawableRes val placeholder: Int? = null,
    val scaleType: GlideScaleType = GlideScaleType.Default,
    val isCircular: Boolean = false,
    val cache: GlideCache = GlideCache.Default
)

sealed class GlideScaleType {
    object Default : GlideScaleType()
    object CenterCrop : GlideScaleType()
    object FitCenter : GlideScaleType()
    object CenterInside : GlideScaleType()
    data class Custom(val height: Int, val width: Int) : GlideScaleType()
    data class CustomDimen(@DimenRes val height: Int, @DimenRes val width: Int) : GlideScaleType()
}

enum class GlideCache {
    Default,
    All,
    Data,
    None,
    Resource,
    Automatic
}

/**
 * Load Image using glide.
 *
 * If a placeholder resource is applied it will be shown else nothing is displayed for
 * placeholder
 */
fun ImageView.loadImage(url: String, @DrawableRes placeHolder: Int = -1) {
    Glide.with(context)
        .load(url)
        .apply { if (placeHolder != -1) this.placeholder(placeHolder) }
        .into(this)
}

fun ImageView.loadImageWithOptions(url: String?, options: GlideImageOptions) {
    Glide.with(context)
        .load(url)
        .apply {
            when (options.scaleType) {
                is GlideScaleType.Default -> {
                    //do nothing
                }
                is GlideScaleType.CenterCrop -> this.centerCrop()
                is GlideScaleType.FitCenter -> this.fitCenter()
                is GlideScaleType.CenterInside -> this.centerInside()
                is GlideScaleType.Custom -> with(options.scaleType) {
                    val (height, width) = this@with
                    this@apply.override(height, width)
                }
                is GlideScaleType.CustomDimen -> with(options.scaleType) {
                    val (height, width) = this
                    this@apply.override(height, width)
                }
            }

            when (options.cache) {
                GlideCache.Default -> {
                    //do nothing
                }
                GlideCache.All -> {
                    this.diskCacheStrategy(DiskCacheStrategy.ALL)
                }
                GlideCache.Automatic -> {
                    this.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                }
                GlideCache.None -> {
                    this.diskCacheStrategy(DiskCacheStrategy.NONE)
                }
                GlideCache.Data -> {
                    this.diskCacheStrategy(DiskCacheStrategy.DATA)
                }

                GlideCache.Resource -> {
                    this.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                }
            }
            this.error(options.errorHolder)
            if (options.placeholder != null) {
                this.placeholder(options.placeholder)
            }

            if (options.isCircular) {
                this.circleCrop()
            }
        }.into(this)

}