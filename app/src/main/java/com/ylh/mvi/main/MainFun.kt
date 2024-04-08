package com.ylh.mvi.main

import com.ylh.mvi.base.IUIIntent
import com.ylh.mvi.base.IUIState
import com.ylh.mvi.bean.ArticleModel
import com.ylh.mvi.bean.BannerModel

/**
 * @author: yangLiHai
 * @time: 2024/03/05
 * @desc:
 */

data class MainState(val bannerUIState: BannerUIState, val detailUIState: DetailUIState): IUIState { }

sealed class MainIntent: IUIIntent {
    object GetBanners: MainIntent()
    class GetDetailBanner: MainIntent()
}


sealed class BannerUIState {
    object INIT: BannerUIState()
    class SUCCESS(val models: List<BannerModel>?): BannerUIState()
}

sealed class DetailUIState {
    object  INIT: DetailUIState()
    data class SUCCESS(val article: ArticleModel): DetailUIState()
}