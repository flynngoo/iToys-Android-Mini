package com.itoys.android.core.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.itoys.android.core.mvi.AbsListViewModel
import com.itoys.android.core.mvi.IUIIntent
import com.itoys.android.core.mvi.IUIState
import com.itoys.android.core.mvi.ListUIIntent
import com.itoys.android.core.mvi.ListUIState
import com.itoys.android.utils.expansion.collect
import com.itoys.databinding.CoreLayoutListBinding

/**
 * @Author Gu Fanfan
 * @Email fanfan.work@outlook.com
 * @Date 2023/12/8
 */
abstract class AbsMviListActivity<VM : AbsListViewModel<out IUIIntent, out IUIState>> : AbsMviActivity<CoreLayoutListBinding, VM>() {

    /**
     * 开启刷新
     */
    open val enableRefresh = true

    /**
     * 开启加载更多
     */
    open val enableLoadMore = true

    override fun createViewBinding() = CoreLayoutListBinding.inflate(layoutInflater)

    override fun initialize(savedInstanceState: Bundle?) {
        binding?.titleBar?.setTitle(listTitle())
        // 显示加载中
        binding?.page?.showLoading(refresh = false)

        setupHeader()
        setupFooter()
        setupList()
    }

    override fun initData() {
        super.initData()
        viewModel?.sendListIntent(ListUIIntent.Refresh)
    }

    override fun addObserver() {
        super.addObserver()

        viewModel?.apply { collect(listState, ::collectList) }
    }

    /**
     * collect list
     */
    protected open fun collectList(list: ListUIState?) {
        when (list) {
            is ListUIState.LoadingComplete -> {
                if (list.isRefresh) {
                    binding?.page?.showContent()
                } else {
                    binding?.page?.finishLoadMore(list.isSuccessful)
                }
            }

            ListUIState.NoMore -> {
                binding?.page?.finishLoadMoreWithNoMoreData()
            }

            ListUIState.ShowEmpty -> binding?.page?.showEmpty()

            else -> { /* 空实现 */ }
        }
    }

    override fun addClickListen() {
        super.addClickListen()

        binding?.page?.apply {
            // 刷新
            setEnableRefresh(enableRefresh)
            if (enableRefresh) {
                onRefresh { viewModel?.sendListIntent(ListUIIntent.Refresh) }
            }

            // 加载更多
            setEnableLoadMore(enableLoadMore)
            if (enableLoadMore) {
                onLoadMore { viewModel?.sendListIntent(ListUIIntent.LoadMore) }
            }
        }
    }

    /**
     * list title
     */
    abstract fun listTitle(): String

    /**
     * 设置list
     */
    abstract fun setupList()

    /**
     * header
     */
    open fun headerView(parent: ViewGroup): View? = null

    /**
     * 设置 header
     */
    private fun setupHeader() {
        binding?.header?.let { group ->
            headerView(group)?.apply {
                group.addView(this)
            }
        }
    }

    /**
     * footer
     */
    open fun footerView(parent: ViewGroup): View? = null

    /**
     * 设置 footer
     */
    private fun setupFooter() {
        binding?.footer?.let { group ->
            footerView(group)?.apply {
                group.addView(this)
            }
        }
    }
}