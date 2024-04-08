package com.ylh.mvi.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

/**
 * @author: yangLiHai
 * @time: 2024/03/05
 * @desc:
 */
abstract class BaseActivity<I: IUIState, S: IUIIntent, VM: BaseViewModel<I, S>>: AppCompatActivity() {
    protected abstract val vm: VM
    protected abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        registerEvent()
    }

    protected abstract fun registerEvent()
}