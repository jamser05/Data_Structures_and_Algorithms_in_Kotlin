package tree

import binaryTrees.BinaryNode

fun main(args: Array<String>) {
    val three = TreeNode(3)
    val two = TreeNode(2)
    val one = TreeNode(1)
    val four = TreeNode(4)
    val five = TreeNode(5)
    val six = TreeNode(6)
    val seven = TreeNode(7)
    val eight = TreeNode(8)

    one.add(two)
    one.add(three)
    two.add(four)
    three.add(five)
    four.add(five)
    four.add(six)
    four.add(seven)
    four.add(eight)

    one.dsfTreePrintValues { println(it.value) }
    println("BFS traversal printing")
    one.bfsTreePrintValues { println(it.value) }

    println("sum")



}