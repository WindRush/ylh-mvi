package com.ylh.mvi.main

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.annotation.CallSuper
import androidx.lifecycle.lifecycleScope
import com.ylh.mvi.R
import com.ylh.mvi.base.BaseActivity
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

/**
 * @author: yangLiHai
 * @time: 2024/03/05
 * @desc:
 */
class MainActivity: BaseActivity<MainState, MainIntent, MainViewModel>() {
    override val vm: MainViewModel by viewModels()
    override val layout: Int = R.layout.activity_main

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<Button>(R.id.btn)?.setOnClickListener {
            vm.sendUIIntent(MainIntent.GetBanners)
        }
    }

    override fun registerEvent() {
        lifecycleScope.launchWhenStarted {
            vm.uiStateFlow.map {
                it.bannerUIState
            }.distinctUntilChanged().collect {
                when(it) {
                    is BannerUIState.INIT -> {

                    }
                    is BannerUIState.SUCCESS -> {
                        // bannerAdapter.setList(bannerUiState.models)
                    }
                }
            }
        }
    }
}