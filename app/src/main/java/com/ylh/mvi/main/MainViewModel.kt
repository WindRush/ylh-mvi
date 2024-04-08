package com.ylh.mvi.main

import com.ylh.mvi.base.BaseViewModel

/**
 * @author: yangLiHai
 * @time: 2024/03/05
 * @desc:
 */
class MainViewModel: BaseViewModel<MainState, MainIntent>() {
    override fun initUIState(): MainState = MainState(BannerUIState.INIT, DetailUIState.INIT)
    override fun handleIntent(intent: MainIntent) {
        when(intent) {
            MainIntent.GetBanners -> {
                // 假装成功了
                sendUIState {
                    copy(bannerUIState = BannerUIState.SUCCESS(null))
                }
            }
            is MainIntent.GetDetailBanner -> {

            }
        }
    }
}