package cn.fzliang.MPNative.components

import cn.fzliang.MPNative.MPRenderEngine
import com.facebook.litho.*
import com.facebook.litho.view.onClick
import com.facebook.litho.view.onTouch
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaJustify
class ViewComponent(private val mpRenderEngine: MPRenderEngine, val id: String) : KComponent() {
    private var childIds = mutableListOf<String>()
    private var childIdsState: State<MutableList<String>>? = null

    fun appendChild(id: String) {
        if (childIdsState != null) {
            childIdsState?.update { it ->
                val newChildIds = mutableListOf<String>()
                newChildIds.addAll(it)
                newChildIds.add(id)
                newChildIds
            }
            return
        }

        childIds.add(id)
    }

    override fun ComponentScope.render(): Component? {
        childIdsState = useState { childIds }

        return Column(
            style = Style.onClick(enabled = true){
                mpRenderEngine.triggerEvent("tap", id)
                true
            }, alignItems = YogaAlign.CENTER, justifyContent = YogaJustify.CENTER
        ) {
            childIdsState?.value?.forEach {
                val view = mpRenderEngine.findViewById(it)
                if (view != null) {
                    child(view)
                }
            }
        }
    }
}