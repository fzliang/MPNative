package cn.fzliang.MPNative

import cn.fzliang.MPNative.components.TextComponent
import cn.fzliang.MPNative.components.ViewComponent
import com.facebook.litho.KComponent
import com.facebook.litho.LithoView
import com.facebook.rendercore.utils.ThreadUtils.runOnUiThread

class MPRenderEngine {

    val mpCompIdMap: MutableMap<String, KComponent> = mutableMapOf()

    val mContext: MainActivity

    constructor(context: MainActivity) {
        mContext = context
    }

    fun findViewById(id: String): KComponent? {
        return mpCompIdMap[id]
    }

    fun createNode(id: String) {
        val view = ViewComponent(this, id)
        mpCompIdMap[id] = view
    }

    fun createTextNode(id: String, text: String) {
        val view = TextComponent(this, id, text)
        mpCompIdMap[id] = view
    }

    fun appendChild(id: String, childId: String) {
        val view = findViewById(id)
        if (view is ViewComponent) {
            view.appendChild(childId)
        }
    }

    fun updateText(id: String, text: String) {
        val view = findViewById(id)
        if (view is TextComponent) {
            view.updateText(text)
        }
    }

    fun triggerEvent(eventName: String, id: String) {
        mContext.webView?.loadUrl("javascript:onNativeEvent('tap', " + id + ")")
    }

    fun render(id: String) {
        Thread(Runnable {
            runOnUiThread {
                val view = mpCompIdMap["" + id]

                if (view != null) {
                    val lithoView = LithoView.create(mContext, view)
                    mContext.setContentView(lithoView)
                }
            }
        }).start()
    }
}