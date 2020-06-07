package com.frogsm.instagram_demo.ui.base

import androidx.lifecycle.ViewModel
import com.frogsm.instagram_demo.ui.GlobalListener
import retrofit2.HttpException

abstract class BaseViewModel(
    private val globalListener: GlobalListener
) : ViewModel() {

    /**
     * 공통적인 HTTP Exception 에러 핸들링을 이곳에서 하고
     * 그 외의 핸들링은 하위 뷰에서 하도록 합니다.
     */
    protected fun <T> Result<T>.onFailureAfterHttpExceptionHandle(
        action: (exception: Throwable) -> Unit
    ): Result<T> = onFailure { throwable ->

        val httpException = throwable as? HttpException
        httpException?.run {
            when (code()) {
                // 토큰이 만료되거나 토큰정보가 잘못 되었을때, 화면 재시작
                400 -> globalListener.onAllErrorsForToken()
            }
        }

        action(throwable)
    }
}