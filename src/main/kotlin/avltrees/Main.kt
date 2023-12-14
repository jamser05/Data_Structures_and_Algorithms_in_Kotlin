package avltrees

fun main(args: Array<String>) {
    val myTree = AVLTree<Int>()

    (1..3).forEach {
        myTree.insert(it)
    }

    println(myTree)
    println(myTree.countLeafNodes(myTree.root))
}