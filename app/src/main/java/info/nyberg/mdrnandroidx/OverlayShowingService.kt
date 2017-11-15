package info.nyberg.mdrnandroidx

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.View
import android.view.WindowManager

/**
 * Created by nybjorn on 2017-11-14.
 */
class OverlayShowingService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private lateinit var wm: WindowManager

    private lateinit var lineView: View

    override fun onCreate() {
        super.onCreate()

        wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        lineView = View(this)
        lineView.setBackgroundColor(Color.GREEN)
        val lineLayout = WindowManager.LayoutParams(
                1,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT)
        lineView.setLayoutParams(lineLayout)

        val params = WindowManager.LayoutParams(
                3,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT)
        params.gravity = Gravity.RIGHT or Gravity.TOP
        params.x = 0
        params.y = 0
        wm.addView(lineView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        wm.removeView(lineView)

    }
}