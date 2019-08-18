import com.jme3.scene.Node
import com.jme3.scene.Spatial

fun Spatial.find(name: String): Spatial? {
    if (this.name == name) {
        return this
    }

    return (this as? Node)
            ?.children
            ?.firstOrNull { it.find(name) != null }
}

fun Spatial.dump() {
    dump(0)
}

private fun Spatial.dump(level: Int) {
    val spacing = "  ".repeat(level)
    println("$spacing$name")
    (this as? Node)?.children?.forEach {
        it.dump(level + 1)
    }
}
