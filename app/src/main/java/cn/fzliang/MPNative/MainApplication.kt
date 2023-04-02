package cn.fzliang.MPNative

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.soloader.SoLoader

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
        Fresco.initialize(this)
    }
}