package com.itoys.android.uikit

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.gyf.immersionbar.ImmersionBar
import com.itoys.R
import com.itoys.android.logcat.asLog
import com.itoys.android.logcat.logcat
import com.itoys.android.uikit.components.indicator.IToysPagerTitleView
import com.itoys.android.uikit.components.indicator.IndicatorConfig
import com.itoys.android.utils.expansion.color
import com.itoys.android.utils.expansion.dp2px
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator

/**
 * @Author Gu Fanfan
 * @Email fanfan.work@outlook.com
 * @Date 2023/10/31
 */

/**
 * 沉浸式状态栏
 */
fun Activity.initBar(
    @ColorRes statusBarColor: Int = R.color.uikit_colorful_white,
    @ColorRes navigationBarColor: Int = R.color.uikit_colorful_white,
    statusBarDarkFont: Boolean = true,
    fullScreen: Boolean = false,
    fitsSystemWindows: Boolean = true,
    enableKeyboard: Boolean = false,
) {
    try {
        ImmersionBar.with(this)
            .fullScreen(fullScreen)
            .statusBarColor(statusBarColor)
            .statusBarDarkFont(statusBarDarkFont)
            .fitsSystemWindows(fitsSystemWindows)
            .keyboardEnable(enableKeyboard)
            .navigationBarColor(navigationBarColor)
            .init()
    } catch (e: Exception) {
        logcat(priority = Log.ERROR) { e.asLog() }
    }
}

/**
 * Remove [RecyclerView] all item decoration.
 */
fun RecyclerView.clearDecorations() {
    if (itemDecorationCount > 0) {
        for (i in itemDecorationCount - 1 downTo 0) {
            removeItemDecorationAt(i)
        }
    }
}

fun MagicIndicator.magicIndicator(
    indicatorList: List<String>,
    viewPager: ViewPager2?,
    config: IndicatorConfig = IndicatorConfig.DEFAULT,
    callback: ((Int) -> Unit)? = null,
) {
    val helper = FragmentContainerHelper(this)
    val navigator = CommonNavigator(context)
    navigator.isAdjustMode = config.adjustMode

    navigator.adapter = object : CommonNavigatorAdapter() {
        override fun getCount(): Int = indicatorList.size

        override fun getTitleView(context: Context, index: Int): IPagerTitleView {
            val titleView = IToysPagerTitleView(context)
            titleView.text = indicatorList[index]
            titleView.normalTextTypeface = config.textTypeface
            titleView.selectedTextTypeface = config.selectedTextTypeface
            titleView.textSize = config.textSize
            titleView.normalColor = context.color(config.normalColor)
            titleView.selectedColor = context.color(config.selectedColor)
            titleView.setOnClickListener {
                if (viewPager == null) helper.handlePageSelected(index)
                viewPager?.setCurrentItem(index, false)
                navigator.onPageScrolled(index, 0f, 0)
                callback?.invoke(index)
            }
            return titleView
        }

        override fun getIndicator(context: Context): IPagerIndicator? {
            return if (config.withIndicator) {
                val indicator = LinePagerIndicator(context)
                indicator.lineWidth = 24.dp2px().toFloat()
                indicator.mode = LinePagerIndicator.MODE_EXACTLY
                indicator.setColors(context.color(config.indicatorColor))
                indicator.roundRadius = 2.dp2px().toFloat()
                indicator.lineHeight = 2.dp2px().toFloat()
                indicator
            } else {
                return null
            }
        }
    }

    this.navigator = navigator
    viewPager?.bindMagicIndicator(this)
    viewPager?.offscreenPageLimit = indicatorList.size
}

fun ViewPager2?.bindMagicIndicator(magicIndicator: MagicIndicator) {
    this?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            magicIndicator.onPageSelected(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
            magicIndicator.onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }
    })
}

fun MagicIndicator.magicIndicator(
    indicatorList: List<String>,
    viewPager: ViewPager?,
    config: IndicatorConfig = IndicatorConfig.DEFAULT,
    callback: ((Int) -> Unit)? = null,
) {
    val helper = FragmentContainerHelper(this)
    val navigator = CommonNavigator(context)
    navigator.isAdjustMode = config.adjustMode

    navigator.adapter = object : CommonNavigatorAdapter() {
        override fun getCount(): Int = indicatorList.size

        override fun getTitleView(context: Context, index: Int): IPagerTitleView {
            val titleView = IToysPagerTitleView(context)
            titleView.text = indicatorList[index]
            titleView.normalTextTypeface = config.textTypeface
            titleView.selectedTextTypeface = config.selectedTextTypeface
            titleView.textSize = config.textSize
            titleView.normalColor = context.color(config.normalColor)
            titleView.selectedColor = context.color(config.selectedColor)
            titleView.setOnClickListener {
                if (viewPager == null) helper.handlePageSelected(index)
                viewPager?.setCurrentItem(index, false)
                navigator.onPageScrolled(index, 0f, 0)
                callback?.invoke(index)
            }
            return titleView
        }

        override fun getIndicator(context: Context): IPagerIndicator? {
            return if (config.withIndicator) {
                val indicator = LinePagerIndicator(context)
                indicator.lineWidth = 24.dp2px().toFloat()
                indicator.mode = LinePagerIndicator.MODE_EXACTLY
                indicator.setColors(context.color(config.indicatorColor))
                indicator.roundRadius = 2.dp2px().toFloat()
                indicator.lineHeight = 2.dp2px().toFloat()
                indicator
            } else {
                return null
            }
        }
    }

    this.navigator = navigator
    viewPager?.let { pager ->
        ViewPagerHelper.bind(this, pager)
        pager.offscreenPageLimit = indicatorList.size
    }
}

fun MagicIndicator.magicIndicator(
    indicatorList: List<String>,
    config: IndicatorConfig = IndicatorConfig.DEFAULT,
    callback: ((Int) -> Unit)? = null,
) {
    val helper = FragmentContainerHelper(this)
    val navigator = CommonNavigator(context)
    navigator.isAdjustMode = config.adjustMode

    navigator.adapter = object : CommonNavigatorAdapter() {
        override fun getCount(): Int = indicatorList.size

        override fun getTitleView(context: Context, index: Int): IPagerTitleView {
            val titleView = IToysPagerTitleView(context)
            titleView.text = indicatorList[index]
            titleView.normalTextTypeface = config.textTypeface
            titleView.selectedTextTypeface = config.selectedTextTypeface
            titleView.textSize = config.textSize
            titleView.normalColor = context.color(config.normalColor)
            titleView.selectedColor = context.color(config.selectedColor)
            titleView.setOnClickListener {
                helper.handlePageSelected(index)
                callback?.invoke(index)
            }
            return titleView
        }

        override fun getIndicator(context: Context): IPagerIndicator? {
            return if (config.withIndicator) {
                val indicator = LinePagerIndicator(context)
                indicator.lineWidth = 24.dp2px().toFloat()
                indicator.mode = LinePagerIndicator.MODE_EXACTLY
                indicator.setColors(context.color(config.indicatorColor))
                indicator.roundRadius = 2.dp2px().toFloat()
                indicator.lineHeight = 2.dp2px().toFloat()
                indicator
            } else {
                return null
            }
        }
    }

    this.navigator = navigator
}

fun FragmentManager.addFragment(containerViewId: Int, fragment: Fragment?, tag: String) {
    fragment?.let {
        val trans = beginTransaction()
        trans.add(containerViewId, fragment, tag)
        trans.commit()
    }
}

fun FragmentManager.removeFragmentByTag(tag: String) {
    val fragment = findFragmentByTag(tag)
    fragment?.let {
        val trans = beginTransaction()
        trans.remove(fragment)
        trans.commit()
    }
}