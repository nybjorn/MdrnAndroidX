package info.nyberg.mdrnandroidx

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by nybjorn on 2017-11-14.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val svc = Intent(this, OverlayShowingService::class.java)
        checkPermissionOverlay()
        startService(svc)
        finish()

    }


    private val OVERLAY_PERMISSION_REQ_CODE: Int = 42

    @TargetApi(Build.VERSION_CODES.M)
    private fun checkPermissionOverlay() {
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "Нужны права на наложение поверх всех приложений", Toast.LENGTH_LONG).show()
            val intentSettings = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            startActivityForResult(intentSettings, OVERLAY_PERMISSION_REQ_CODE)
        }    }
}
