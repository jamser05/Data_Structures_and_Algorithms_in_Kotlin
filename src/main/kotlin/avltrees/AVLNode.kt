package avltrees

typealias Visitor<T> = (T) -> Unit

class AVLNode<T>(var value: T) {
    var height = 0

    val rightHeight: Int
        get() = rightChild?.height ?: -1

    val leftHeight: Int
        get() = leftChild?.height ?: -1

    val balanceFactor: Int
        get() = (leftHeight - rightHeight)

    var leftChild: AVLNode<T>? = null
    var rightChild: AVLNode<T>? = null

    val min: AVLNode<T>
        get() = leftChild?.min ?: this

    override fun toString() = diagram(this)

    private fun diagram(node: AVLNode<T>?,
                        top: String = "",
                        root: String = "",
                        bottom: String = ""): String {
        return node?.let {
            if (node.leftChild == null && node.rightChild == null) {
                "$root${node.value}\n"
            } else {
                diagram(node.rightChild, "$top ", "$top┌──", "$top│ ") +
                        root + "${node.value}\n" + diagram(node.leftChild, "$bottom│ ", "$bottom└──", "$bottom ")
            }
        } ?: "${root}null\n"
    }

    fun traverseInOrder(visit: Visitor<T>) {
        leftChild?.traverseInOrder(visit)
        visit(value)
        rightChild?.traverseInOrder(visit)
    }

    fun traversePreOrder(visit: Visitor<T>) {
        visit(value)
        leftChild?.traversePreOrder(visit)
        rightChild?.traversePreOrder(visit)
    }

    fun traversePostOrder(visit: Visitor<T>) {
        leftChild?.traversePreOrder(visit)
        rightChild?.traversePreOrder(visit)
        visit(value)
    }

}
