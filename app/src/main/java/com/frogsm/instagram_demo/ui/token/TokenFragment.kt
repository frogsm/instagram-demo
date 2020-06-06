package com.frogsm.instagram_demo.ui.token

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.ui.ViewModelFactory
import com.frogsm.instagram_demo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_token.*
import javax.inject.Inject

class TokenFragment : BaseFragment(R.layout.fragment_token) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<TokenViewModel> { viewModelFactory }
    private val args by navArgs<TokenFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initBinding()
        viewModel.start(args.clientId, args.clientSecretId, args.redirectUri)
    }

    private fun initUi() {
        webView.apply {
            with(settings) {
                javaScriptEnabled = true
                useWideViewPort = true
                loadWithOverviewMode = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                textZoom = 100
            }

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {
                    return handleUri(request.url)
                }

                private fun handleUri(uri: Uri): Boolean {
                    val url = uri.toString()

                    return when {
                        url.startsWith(context.getString(R.string.redirect_url)) -> {
                            val code = uri.getQueryParameter("code")
                            code?.run { viewModel.onAuthorizeCodeObtained(code) }
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
            }
        }
    }

    private fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner) { state ->

            state.displayUrl?.observeOnlyOnce {
                webView.loadUrl(it)
            }
        }
    }
}