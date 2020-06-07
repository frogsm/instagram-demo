package com.frogsm.instagram_demo.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.frogsm.instagram_demo.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel by viewModels<MainActivityViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
        initBinding()
    }

    private fun initUi() {

    }

    private fun initBinding() {
        viewModel.liveData.observe(this) { state ->

            state.restartActivity?.observeOnlyOnce {
                finish()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}