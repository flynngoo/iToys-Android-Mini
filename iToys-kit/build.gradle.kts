plugins {
    alias(libs.plugins.itoys.android.library)
}

android {
    namespace = "com.itoys"
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.activity.ktx)
    implementation(libs.core.ktx)
    implementation(libs.material)
    implementation(libs.multidex)

    implementation(libs.brv)
    implementation(libs.eventbus.live)
    implementation(libs.fragment.ktx)
    implementation(libs.glide)
    kapt(libs.glide.compiler)
    implementation(libs.glide.transformers)
    implementation(libs.glide.transformers.gpu)
    implementation(libs.gson)
    implementation(libs.immersionbar)
    implementation(libs.jackson)
    implementation(libs.keyboard.event)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.lottie)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.magic.indicator)
    implementation(libs.mmkv)
    implementation(libs.title.bar)
    implementation(libs.timber)
    implementation(libs.recycler.view)
    implementation(libs.refresh.header.classics)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.jackson)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.retrofit.converter.scalars)
    implementation(libs.okhttp.logger)
    implementation(libs.okdownload)
    implementation(libs.okdownload.ktx)
    implementation(libs.viewpager2)
    implementation(libs.zxing)

    implementation(files("libs/ProgressBar-1.0.1.aar"))
    implementation(files("libs/ImageViewPager-1.0.0.aar"))
    implementation(files("libs/lib-hybrid-1.0.0.aar"))
}