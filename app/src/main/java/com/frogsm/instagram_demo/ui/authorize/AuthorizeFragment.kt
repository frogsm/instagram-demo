package com.frogsm.instagram_demo.ui.authorize

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.frogsm.instagram_demo.R
import com.frogsm.instagram_demo.extensions.showLongSnackBar
import com.frogsm.instagram_demo.ui.ViewModelFactory
import com.frogsm.instagram_demo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_authorize.*
import javax.inject.Inject

class AuthorizeFragment : BaseFragment(R.layout.fragment_authorize) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<AuthorizeViewModel> { viewModelFactory }
    private val args by navArgs<AuthorizeFragmentArgs>()

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

            state.showSnackBar?.observeOnlyOnce {
                showLongSnackBar(it)
            }

            state.navigateBack?.observeOnlyOnce {
                findNavController().popBackStack()
            }
        }
    }
}