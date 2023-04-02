package cn.fzliang.MPNative

import android.webkit.JavascriptInterface
import android.widget.Toast

class JSInterface (private val mContext: MainActivity, private val mpRenderEngine: MPRenderEngine) {
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun render(id: String) {
        mpRenderEngine.render(id);
        Toast.makeText(mContext, "mpNative", Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun createNode(id: String) {
        mpRenderEngine.createNode(id);
    }

    @JavascriptInterface
    fun createTextNode(id: String, text: String) {
        mpRenderEngine.createTextNode(id, text)
    }

    @JavascriptInterface
    fun appendChild(id: String, childId: String) {
        mpRenderEngine.appendChild(id, childId)
    }

    @JavascriptInterface
    fun spliceAppend(id: String, childIds: Array<String>) {
        for (childId in childIds) {
            mpRenderEngine.appendChild(id, childId)
        }
    }

    @JavascriptInterface
    fun removeChild(id: String, childId: String) {

    }

    @JavascriptInterface
    fun insertBefore(id: String, childId: String, beforeChildId: String) {

    }

    @JavascriptInterface
    fun replaceChild(id: String, childId: String, oldChildId: String) {

    }

    @JavascriptInterface
    fun setId(id: String, propId: String) {

    }

    @JavascriptInterface
    fun setStyle(id: String, styleJson: String) {

    }

    @JavascriptInterface
    fun updateText(id: String, text: String) {
        mpRenderEngine.updateText(id, text);
    }
}