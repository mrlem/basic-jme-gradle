import com.jme3.app.SimpleApplication
import com.jme3.asset.MaterialKey
import com.jme3.math.Vector3f
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import com.jme3.scene.shape.Box
import com.jme3.scene.Spatial
import com.jme3.system.AppSettings

class Application : SimpleApplication() {

    private lateinit var pivot: Spatial
    private lateinit var cube: Spatial

    override fun simpleInitApp() {
        // create scene
        // .. import it
        val scene = assetManager.loadModel("Scenes/test_scene.j3o")
        rootNode.attachChild(scene)
        // .. add stuff to it
        (scene as Node).attachChild(createSampleBoxes())

        rootNode.dump()

        // lookup spatials we need to update
        cube = scene.find("Cube")!!
        pivot = scene.find("Pivot")!!
    }

    override fun simpleUpdate(tpf: Float) {
        pivot.rotate(0f, .4f * tpf, 0f)
        cube.rotate(0f, -.25f * tpf, 0f)
    }

    private fun createSampleBoxes(): Node {
        val node = Node("Pivot")

        // .. dull box
        val box1 = Box(1f, 1f, 1f)
        val dull = Geometry("Box", box1)
        dull.localTranslation = Vector3f(1.5f, -1f, 0f)
        dull.material = assetManager.loadAsset(MaterialKey("Materials/dull_green.j3m"))
        node.attachChild(dull)

        // .. shiny box
        val box2 = Box(1f, 1f, 1f)
        val shiny = Geometry("Box", box2)
        shiny.localTranslation = Vector3f(-1.5f, -1f, 0f)
        shiny.material = assetManager.loadAsset(MaterialKey("Materials/shiny_green.j3m"))
        node.attachChild(shiny)

        return node
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
