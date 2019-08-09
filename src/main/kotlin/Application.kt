import com.jme3.app.SimpleApplication
import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.math.Vector3f
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import com.jme3.scene.shape.Box

class Application : SimpleApplication() {

    private lateinit var pivot: Node

    override fun simpleInitApp() {
        // blue box
        val box1 = Box(1f, 1f, 1f)
        val blue = Geometry("Box", box1)
        blue.localTranslation = Vector3f(1f, -1f, 1f)
        val mat1 = Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
        mat1.setColor("Color", ColorRGBA.Blue)
        blue.material = mat1

        // red box
        val box2 = Box(1f, 1f, 1f)
        val red = Geometry("Box", box2)
        red.localTranslation = Vector3f(1f, 3f, 1f)
        val mat2 = Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
        mat2.setColor("Color", ColorRGBA.Red)
        red.material = mat2

        // parent node
        pivot = Node("pivot")
        pivot.attachChild(blue)
        pivot.attachChild(red)
        rootNode.attachChild(pivot)
    }

    override fun simpleUpdate(tpf: Float) {
        pivot.rotate(.4f * tpf, .4f * tpf, 0f)
    }

}

fun main() {
    Application().start()

    /*
  In case you run into trouble with fullscreen mode, try setting those manually (screen rate sometimes gets badly
  detected by AWT).

    val defs = AppSettings(true).apply {
        isFullscreen = true
        width = 1600
        height = 900
        frequency = 60
        samples = 2
    }
    Application().apply {
        isShowSettings = false
        setSettings(defs)
        start()
    }
    */
}
