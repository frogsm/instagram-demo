package com.frogsm.instagram_demo.ui.mediacollection

import androidx.lifecycle.viewModelScope
import com.frogsm.instagram_demo.data.token.AccessTokenData
import com.frogsm.instagram_demo.data.token.TokenDataSource
import com.frogsm.instagram_demo.domain.token.ValidateAccessToken
import com.frogsm.instagram_demo.ui.GlobalListener
import com.frogsm.instagram_demo.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaCollectionViewModel @Inject constructor(
    globalListener: GlobalListener,
    private val tokenDataSource: TokenDataSource,
    private val validateAccessToken: ValidateAccessToken
) : BaseViewModel(globalListener), MediaCollectionController {

    override fun start() {
        viewModelScope.launch {
            launch { validateAccessToken() }
        }
    }

    private suspend fun validateAccessToken(
    ) = withContext(Dispatchers.IO) {

        validateAccessToken(Unit)
            .onSuccess {
                val tokenData = AccessTokenData("", "asdfasdf")
                tokenDataSource.updateAccessToken(tokenData)
            }
            .onFailureAfterHttpExceptionHandle {
                cancel()
            }
    }
}