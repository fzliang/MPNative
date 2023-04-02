package cn.fzliang.MPNative

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.evgenii.jsevaluator.JsEvaluator
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.litho.*
import com.facebook.litho.fresco.FrescoImage
import java.lang.reflect.Method


class MainActivity : AppCompatActivity() {

    var webView: WebView? = null;
    private var mpRenderEngine: MPRenderEngine = MPRenderEngine(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        val jsEvaluator = JsEvaluator(this);
        webView = jsEvaluator.webView;

        openDebugMode();

        val myJavascriptInterface = JSInterface(this, mpRenderEngine);
        webView?.addJavascriptInterface(myJavascriptInterface, "mpNative");
        webView?.loadUrl("file:///android_asset/index.html");
    }

    override fun onResume() {
        super.onResume();
        openDebugMode();
    }

    private fun openDebugMode() {
        // 开启debug
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                val m: Method = WebView::class.java.getMethod(
                    "setWebContentsDebuggingEnabled",
                    Boolean::class.javaPrimitiveType
                );
                m.invoke(webView, true);
            } catch (e: Exception) {
                Log.d("webview", "Failed to setWebContentsDebuggingEnabled", e);
            }
        }
    }

}


//class ImageComponent(val imageUri: String) : KComponent() {
//    override fun ComponentScope.render(): Component {
//        val imageDraweeController = Fresco.newDraweeControllerBuilder().setUri(Uri.parse(imageUri))
//            .setAutoPlayAnimations(true).build();
//        return FrescoImage.create(context).controller(imageDraweeController).build();
//    }
//}