package cn.fzliang.MPNative.components

import android.graphics.Color
import cn.fzliang.MPNative.MPRenderEngine
import com.facebook.litho.*
import com.facebook.litho.kotlin.widget.Text

class TextComponent() : KComponent() {
    var id: String = ""
    private var text: String = ""
    private var textState: State<String>? = null
    private lateinit var mpRenderEngine: MPRenderEngine

    constructor(mpRenderEngine: MPRenderEngine, id: String, text: String) : this() {
        this.mpRenderEngine = mpRenderEngine
        this.id = id
        this.text = text
    }

    fun updateText(_text: String) {
        if (textState != null) {
            textState!!.update(_text)
            return
        }

        this.text = _text
    }

    override fun ComponentScope.render(): Component {
        textState = useState { text }
        return Text(text = textState!!.value, textSize = 24.dp)
    }
}