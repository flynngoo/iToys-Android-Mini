package com.itoys.android.image

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.itoys.R
import com.itoys.android.utils.expansion.isNotBlank
import com.itoys.android.utils.expansion.trimString
import jp.wasabeef.transformers.glide.BlurTransformation
import jp.wasabeef.transformers.glide.CropCenterTransformation
import jp.wasabeef.transformers.glide.CropCircleTransformation
import jp.wasabeef.transformers.glide.CropCircleWithBorderTransformation
import jp.wasabeef.transformers.glide.RoundedCornersTransformation

/**
 * @Author Gu Fanfan
 * @Email fanfan.work@outlook.com
 * @Date 2023/11/19
 */

@JvmOverloads
fun ImageView.loadImage(
    @RawRes @DrawableRes drawableId: Int,
    @RawRes @DrawableRes errorId: Int = drawableId,
) {
    Glide.with(this).load(drawableId).placeholder(drawableId).error(errorId).into(this)
}

@JvmOverloads
fun ImageView.loadImage(
    url: String?,
    @RawRes @DrawableRes placeholder: Int = R.drawable.image_rectangle_placeholder,
    @RawRes @DrawableRes error: Int = placeholder,
) {
    Glide.with(this).load(url).placeholder(placeholder).error(error).into(this)
}

@JvmOverloads
fun ImageView.loadResizeImage(
    url: String?,
    width: Int,
    height: Int,
    @RawRes @DrawableRes placeholder: Int = R.drawable.image_rectangle_placeholder,
    @RawRes @DrawableRes error: Int = placeholder,
) {
    val options = RequestOptions().placeholder(placeholder)
        .error(error)
        .override(width, height)

    Glide.with(this).load(url).apply(options).into(this)
}

@JvmOverloads
fun ImageView.loadCircleImage(
    url: String?,
    @RawRes @DrawableRes placeholder: Int = R.drawable.image_rectangle_placeholder,
    @RawRes @DrawableRes error: Int = placeholder,
) {
    val options = RequestOptions().transform(
        CropCenterTransformation(),
        CropCircleTransformation()
    )

    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .apply(options)
        .into(this)
}

@JvmOverloads
fun ImageView.loadCircleWithBorderImage(
    url: String?,
    @RawRes @DrawableRes placeholder: Int = R.drawable.image_rectangle_placeholder,
    @RawRes @DrawableRes error: Int = placeholder,
    borderSize: Int = 1,
    @ColorInt borderColor: Int = Color.WHITE,
) {
    val options = RequestOptions().transform(
        CropCenterTransformation(),
        CropCircleWithBorderTransformation(
            borderSize = borderSize,
            borderColor = borderColor
        )
    )

    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .apply(options)
        .into(this)
}

@JvmOverloads
fun ImageView.loadRoundCornerImage(
    url: String?,
    radius: Int = 0,
    @RawRes @DrawableRes placeholder: Int = R.drawable.image_rectangle_placeholder,
    @RawRes @DrawableRes error: Int = placeholder,
    cornerType: RoundCornerType = RoundCornerType.ALL,
) {
    val options = RequestOptions().transform(
        CenterCrop(),
        RoundedCornersTransformation(radius, cornerType = cornerType.getType())
    )

    Glide.with(this)
        .load(url.trimString())
        .placeholder(placeholder)
        .error(error)
        .apply(options)
        .into(this)
}

@JvmOverloads
fun ImageView.loadRoundCornerImage(
    @RawRes @DrawableRes drawableId: Int,
    radius: Int = 0,
    cornerType: RoundCornerType = RoundCornerType.ALL,
) {
    val options = RequestOptions().transform(
        CenterCrop(),
        RoundedCornersTransformation(radius, cornerType = cornerType.getType())
    )

    Glide.with(this)
        .load(drawableId)
        .placeholder(drawableId)
        .error(drawableId)
        .apply(options)
        .into(this)
}

@JvmOverloads
fun ImageView.loadRoundCornerImage(
    drawable: Drawable,
    radius: Int = 0,
    cornerType: RoundCornerType = RoundCornerType.ALL,
) {
    val options = RequestOptions().transform(
        CenterCrop(),
        RoundedCornersTransformation(radius, cornerType = cornerType.getType())
    )

    Glide.with(this)
        .load(drawable)
        .placeholder(drawable)
        .error(drawable)
        .apply(options)
        .into(this)
}

@JvmOverloads
fun ImageView.loadBlurImage(
    url: String?,
    radius: Int = 25,
    sampling: Int = 4,
    @RawRes @DrawableRes placeholder: Int = R.drawable.image_rectangle_placeholder,
    @RawRes @DrawableRes error: Int = placeholder,
) {
    val options = RequestOptions().transform(
        CropCenterTransformation(),
        BlurTransformation(this.context, radius, sampling)
    )

    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .apply(options)
        .into(this)
}

fun String.isLocalAddress() = this.startsWith("/")

fun String?.isRemoteAddress() = this.isNotBlank() && (this?.startsWith("http") == true)