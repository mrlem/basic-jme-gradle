import com.jme3.app.SimpleApplication
import com.jme3.asset.MaterialKey
import com.jme3.math.Vector3f
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import com.jme3.scene.shape.Box
import com.jme3.light.DirectionalLight
import com.jme3.scene.Spatial
import com.jme3.system.AppSettings

class Application : SimpleApplication() {

    private lateinit var pivot: Node

    override fun simpleInitApp() {
        // scene node
        val scene = assetManager.loadModel("Scenes/test_scene.j3o")
        rootNode.attachChild(scene)

        // parent node for additional boxes
        pivot = Node("pivot")
        rootNode.attachChild(pivot)

        // .. dull box
        val box1 = Box(1f, 1f, 1f)
        val dull = Geometry("Box", box1)
        dull.localTranslation = Vector3f(1f, -1f, 1f)
        dull.material = assetManager.loadAsset(MaterialKey("Materials/dull_green.j3m"))
        pivot.attachChild(dull)

        // .. shiny box
        val box2 = Box(1f, 1f, 1f)
        val shiny = Geometry("Box", box2)
        shiny.localTranslation = Vector3f(1f, 3f, 1f)
        shiny.material = assetManager.loadAsset(MaterialKey("Materials/shiny_green.j3m"))
        pivot.attachChild(shiny)

        // light node
        val sun = DirectionalLight()
        sun.direction = Vector3f(-0.1f, -0.7f, -1.0f).normalizeLocal()
        rootNode.addLight(sun)

        dump(rootNode, 0)
    }

    private fun dump(spatial: Spatial, level: Int) {
        val spacing = "  ".repeat(level)
        println("$spacing${spatial.name}")
        (spatial as? Node)?.children?.forEach {
            dump(it, level + 1)
        }
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
