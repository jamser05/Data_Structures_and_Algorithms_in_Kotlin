package binarytrees

import binaryTrees.BinaryNode


fun main(args: Array<String>) {
    val three = BinaryNode(3)
    val two = BinaryNode(2)
    val one = BinaryNode(1)
    val four = BinaryNode(4)
    val five = BinaryNode(6)

    three.leftChild = two
    two.leftChild = one
    three.rightChild = four
    four.rightChild = five

    three.printValuesStacks(three)

    println("printing values using Depth-first Search")
    three.dsfPrintValues(three)

    println("printing values using Queues")
    three.bfsPrintValues(three)

    println("Print maxPath sum")
    println(three.maxPathBinaryTree(three))

    println("Does Value Exit?")
    println(three.findTarget(three, 9))

    println("Does value exist with stacks")
    println(three.findTargetUsingStacks(9))

    println("Find Min Value in Binary Tree")
    println(three.minInBinaryTree(three))

    println("Find Tree Sum Recursively")
    println(three.binaryTreeSumRecursively(three))

    println("Find Tree Sum With Stacks")
    println(three.binaryTreeSumUsingStacks())

    println("Find Tree Sum With Queue")
    println(three.binaryTreeSumWithQueue())




}