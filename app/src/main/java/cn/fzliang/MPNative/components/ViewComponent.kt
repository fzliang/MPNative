package cn.fzliang.MPNative.components

import cn.fzliang.MPNative.MPRenderEngine
import com.facebook.litho.*
import com.facebook.litho.view.onClick
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaJustify

class ViewComponent(private val mpRenderEngine: MPRenderEngine, val id: String) : KComponent() {
    private var childIds = arrayListOf<String>()
    private var childIdsState: State<ArrayList<String>>? = null

    fun appendChild(id: String) {
        if (childIdsState != null) {
            childIdsState?.update {
                it.add(id)
                it
            }
            return
        }

        childIds.add(id)
    }

    override fun ComponentScope.render(): Component? {
        childIdsState = useState { childIds }

        return Column(
            style = Style.onClick {
                mpRenderEngine.triggerEvent("tap", id)
            }, alignItems = YogaAlign.CENTER, justifyContent = YogaJustify.CENTER
        ) {
            childIdsState?.value?.forEach {
                val view = mpRenderEngine.findViewById(it)
                println(it)
                if (view != null) {
                    child(view)
                }
            }
        }
    }
}