package com.ynour.multiuse

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.ynour.multiuse.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    private lateinit var url: String
    private lateinit var myWebView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myWebView = binding.myWebView
        myWebView.webViewClient = WebViewClient()
        url = intent.getStringExtra(EXTRA_STRING).toString()

        val urlSplit = url.split('.')
        if (url == "") {
            myWebView.loadUrl("google.com")
        }
        if (urlSplit.size >= 2) {
            if (urlSplit[urlSplit.size - 1] == "com" ||
                urlSplit[urlSplit.size - 1] == "eg" ||
                urlSplit[urlSplit.size - 1] == "org" ||
                urlSplit[urlSplit.size - 1] == "net" ||
                urlSplit[urlSplit.size - 1] == "co" ||
                urlSplit[urlSplit.size - 1] == "xyz") {
                if (urlSplit[0] == "https://"){ myWebView.loadUrl(url) }
                else myWebView.loadUrl("https://$url")
            }else{
                myWebView.loadUrl("https://google.com/search?q=$url")
            }
        } else {
            myWebView.loadUrl("https://google.com/search?q=$url")
        }
        myWebView.settings.apply {
            javaScriptEnabled = true
            builtInZoomControls = true
        }
    }

    override fun onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}