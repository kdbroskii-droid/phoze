package com.phoze.app

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.ImageView

class MainActivity : Activity() {
    private lateinit var root: FrameLayout
    private var webView: WebView? = null
    private val handler = Handler(Looper.getMainLooper())
    private val bootDurationMs = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.BLACK
        window.navigationBarColor = Color.BLACK
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        )

        root = FrameLayout(this).apply {
            setBackgroundColor(Color.BLACK)
        }
        setContentView(root)
        showBootScreen()
    }

    private fun showBootScreen() {
        val boot = FrameLayout(this).apply {
            setBackgroundColor(Color.BLACK)
            alpha = 1f
        }
        val logo = ImageView(this).apply {
            setImageResource(R.drawable.ic_apple_logo)
            scaleType = ImageView.ScaleType.FIT_CENTER
            contentDescription = "Apple logo"
        }
        val size = (112 * resources.displayMetrics.density).toInt()
        val params = FrameLayout.LayoutParams(size, size, Gravity.CENTER)
        boot.addView(logo, params)
        root.addView(boot, FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ))

        handler.postDelayed({
            showPhozeInterface()
            boot.animate()
                .alpha(0f)
                .setDuration(260)
                .withEndAction { root.removeView(boot) }
                .start()
        }, bootDurationMs)
    }

    private fun showPhozeInterface() {
        val view = WebView(this)
        webView = view
        view.setBackgroundColor(Color.rgb(16, 24, 39))
        view.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            databaseEnabled = true
            cacheMode = WebSettings.LOAD_DEFAULT
            allowFileAccess = true
            allowContentAccess = false
            builtInZoomControls = false
            displayZoomControls = false
            setSupportZoom(false)
            mediaPlaybackRequiresUserGesture = true
        }
        view.webChromeClient = WebChromeClient()
        view.isVerticalScrollBarEnabled = false
        view.isHorizontalScrollBarEnabled = false
        view.overScrollMode = View.OVER_SCROLL_NEVER
        root.addView(view, FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ))
        view.loadUrl("file:///android_asset/index.html")
    }

    @Deprecated("Deprecated in Android API")
    override fun onBackPressed() {
        val view = webView
        if (view != null) {
            view.evaluateJavascript(
                "(function(){const s=document.getElementById('settings');const a=document.getElementById('appview');if(a&&a.classList.contains('show')){a.classList.remove('show');return 'closed'}if(s&&s.classList.contains('show')){s.classList.remove('show');return 'closed'}return 'lock'})()"
            ) { result ->
                if (result == "\"lock\"") {
                    view.evaluateJavascript(
                        "if(typeof lock==='function'){lock()}else{window.history.back()}", null
                    )
                }
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        webView?.destroy()
        webView = null
        super.onDestroy()
    }
}
