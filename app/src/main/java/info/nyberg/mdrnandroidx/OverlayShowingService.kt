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
import android.widget.ImageView

/**
 * Created by nybjorn on 2017-11-14.
 */
class OverlayShowingService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private lateinit var wm: WindowManager

    private lateinit var lineView: View

    private lateinit var notchView: ImageView

    override fun onCreate() {
        super.onCreate()

        wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        notchView = ImageView(this)
        notchView.setImageResource(R.drawable.notch)
        val params2 = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT)
        params2.gravity = Gravity.TOP or Gravity.CENTER
        wm.addView(notchView, params2)


        lineView = View(this)
        lineView.setBackgroundColor(Color.GREEN)

        val params = WindowManager.LayoutParams(
                3,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT)
        params.gravity = Gravity.RIGHT or Gravity.TOP
        params.x = 0
        params.y = 0
        wm.addView(lineView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        wm.removeView(lineView)
        wm.removeView(notchView)

    }
}