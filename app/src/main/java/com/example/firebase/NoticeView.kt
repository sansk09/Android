package com.example.firebase

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class NoticeView : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        var url = intent.getStringExtra("URL")
        var webview = findViewById<WebView>(R.id.wb)

        var chromeClient = object : WebChromeClient(){

        }

        webview.webChromeClient = chromeClient
        webview.loadUrl(url.toString())
        webview.settings.builtInZoomControls=true

    }
}