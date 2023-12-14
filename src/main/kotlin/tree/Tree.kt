package tree

import com.sun.source.tree.Tree
import queue.*
import stacks.*

typealias Visitor<T> = (TreeNode<T>) -> Unit

class TreeNode<T>(val value: T) {
    private val children: MutableList<TreeNode<T>> = mutableListOf()

    // adds child to the end of the list (Children)
    // adds a child node to a node
    fun add(child: TreeNode<T>) {
        children.add(child)
    }
    // This simple code uses recursion to process the next node
    // takes in a parameter visit of type TreeNode<T> -> Unit
    // passes the "this (object)" into the visit lambda
    // visits every child in the treeNode Object


    fun dsfTreePrintValues(visit: Visitor<T>) {
        visit(this)
        children.forEach {
            it.dsfTreePrintValues(visit)
        }
    }

    fun bfsTreePrintValues(visit: Visitor<T>) {
        visit(this)
        var queue = ArrayListQueue<TreeNode<T>>()
        queue.enqueue(this)

        while (!queue.isEmpty) {
            var current = queue.dequeue()
            current?.children?.forEach {
                it.bfsTreePrintValues(visit)
            }
        }
    }


    fun forEachLevelOrder(visit: Visitor<T>) {
        visit(this)
        val queue = ArrayListQueue<TreeNode<T>>()
        children.forEach {
            queue.enqueue(it)
        }

        var node = queue.dequeue()
        while (node != null) {
            visit(node)
            node.children.forEach {
                queue.enqueue(it)
            }
            node = queue.dequeue()
        }
    }

//    fun search(value: T): TreeNode<T>? {
//        var result: TreeNode<T>? = null
//
//        forEachDepthFirst {
//            if(it.value == value) {
//                result = it
//            }
//        }
//
//        return result
//    }

    fun printLevelTraversal(visit: Visitor<T>) {
        var queue = ArrayListQueue<TreeNode<T>>()
        var nodesLeftInCurrentLevel = 0
        queue.enqueue(this)

        while(queue.isEmpty.not()) {
            nodesLeftInCurrentLevel = queue.count
            while(nodesLeftInCurrentLevel > 0) {
                val node = queue.dequeue()
                node?.let {
                    print("${node.value} ")
                    node.children.forEach { queue.enqueue(it) }
                    nodesLeftInCurrentLevel--
                } ?: break
            }
            println()
        }
    }

    private var result = 0

    fun maxDepth(root : TreeNode<T>? = this): Int {
        root ?: return  0
        depthFirstSearch(root, 1)
        return result
    }

    private fun depthFirstSearch(root: TreeNode<T>, depth: Int) {
        root ?: return
        result = maxOf(depth, result)
        root.children.forEach {
            depthFirstSearch(it, depth + 1)
        }
    }

    fun maxDepthLevelOrder(root: TreeNode<T>? = this): Int {
        root ?: return 0
        var depth = 0
        var queue = ArrayListQueue<TreeNode<T>>()
        queue.enqueue(root!!)

        while(!queue.isEmpty) {
            var size = queue.count
            for(i in 0..size - 1) {
                var node = queue.dequeue()
                node?.let {
                    node.children.forEach {
                        queue.enqueue(it)
                    }
                }
            }
            depth++
        }
        return depth
    }

    fun treeLevelOrder(root: TreeNode<Int>?): List<List<Int>> {
        root ?: return emptyList()
        var result = mutableListOf<List<Int>>()
        var queue = ArrayListQueue<TreeNode<Int>?>()
        queue.enqueue(root!!)

        while(!queue.isEmpty) {
            var levelOrderList = mutableListOf<Int>()
            for(i in 0..queue.count - 1) {
                var node = queue.dequeue()
                node?.let {
                    node.children.forEach {
                        queue.enqueue(it)
                    }
                }
                levelOrderList.add(node?.value!!)
            }
            result.add(levelOrderList)
        }
        return result
    }
}