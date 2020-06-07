package com.frogsm.instagram_demo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.util.IntentPendingEventStoreImpl
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel by viewModels<MainActivityViewModel> { viewModelFactory }

    private val pendingEventStore by lazy { IntentPendingEventStoreImpl(intent) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
        initBinding()
    }

    private fun initUi() {
        pendingEventStore.tokenExpiredMessage?.let { message ->
            MaterialAlertDialogBuilder(this)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, null)
                .show()
        }
    }

    private fun initBinding() {
        viewModel.liveData.observe(this) { state ->
            state.restartActivity?.observeOnlyOnce {

                pendingEventStore.tokenExpiredMessage =
                    getString(R.string.error_access_token_expired)

                finish()
                startActivity(intent)
                overridePendingTransition(0, 0)
            }
        }
    }
}