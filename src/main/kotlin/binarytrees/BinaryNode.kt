package binaryTrees

import queue.ArrayListQueue
import queue.Queue
import stacks.Stack
import stacks.StackImpl
import java.lang.Integer.max

typealias Visitor<T> = (T) -> Unit

class BinaryNode<T: Comparable<T>>(var value: T)  {
    val min: BinaryNode<T>?
        get() = leftChild?.min ?: this

    var leftChild: BinaryNode<T>? = null
    var rightChild: BinaryNode<T>? = null

    val isBinarySearchTree: Boolean
        get() = isBST(this, min = null, max= null)


    fun printValuesStacks(root: BinaryNode<T>?) {
        if(root == null) {
            return
        }

        val stack = StackImpl<BinaryNode<T>>()
        stack.push(this)

        while(!stack.isEmpty) {
            var current = stack.pop()
            println(current?.value)

            if(current?.rightChild != null) {
                stack.push(current.rightChild!!)
            }
            if(current?.leftChild != null) {
                stack.push(current.leftChild!!)
            }
        }

    }

    fun dsfPrintValues(root: BinaryNode<T>?) {
        if(root == null) {
            return
        }

        println(root.value)

        dsfPrintValues(root.leftChild)
        dsfPrintValues(root.rightChild)
    }

    fun bfsPrintValues(root: BinaryNode<T>) {
        var queue = ArrayListQueue<BinaryNode<T>>()
        queue.enqueue(this)

        while(!queue.isEmpty) {
            var current = queue.dequeue()
            println(current?.value)
            if(current?.leftChild != null) {
                queue.enqueue(current.leftChild!!)
            }
            if(current?.rightChild != null) {
                queue.enqueue(current.rightChild!!)
            }
        }
    }

    fun maxPathBinaryTree(root: BinaryNode<T>?): Int {
        if (root == null) {
            return 0
        }
        if (root.leftChild == null && root.rightChild == null) {
            return Integer.MIN_VALUE
        }

        return root.value as Int + max(maxPathBinaryTree(root.leftChild), maxPathBinaryTree(root.rightChild))
    }

    fun findTarget(root: BinaryNode<T>?, target: Int): Boolean {
        if(root == null) {
            return false
        }

        if(root.value == target) {
            return true
        }

        return findTarget(root.leftChild, target) || findTarget(root.rightChild, target)
    }

    fun findTargetUsingStacks(target: Int): Boolean{
        if(this == null) {
            return false
        }
        if(this.value == target) {
            return true
        }

        var stack = StackImpl<BinaryNode<T>>()
        stack.push(this)

        while(!stack.isEmpty) {
            var current = stack.pop()
            if(current?.value == target) {
                return true
            }

            if(current?.rightChild != null) {
                stack.push(current.rightChild!!)
            }

            if(current?.leftChild != null) {
                stack.push(current.leftChild!!)
            }
        }

        return false
    }

    fun minInBinaryTree(root: BinaryNode<T>?): Int {
        if(root == null) {
            return Integer.MAX_VALUE
        }

        return minOf(root.value as Int, minOf(minInBinaryTree(root.leftChild), minInBinaryTree(root.rightChild)))
    }

    fun binaryTreeSumUsingStacks(): Int {
        var stack = StackImpl<BinaryNode<T>>()
        stack.push(this)
        var sum = 0

        while(!stack.isEmpty) {
            var current = stack.pop()
            sum += current?.value as Int

            if(current.rightChild != null) {
                stack.push(current.rightChild!!)
            }
            if(current.leftChild != null) {
                stack.push(current.leftChild!!)
            }
        }
        return sum
    }

    fun binaryTreeSumWithQueue(): Int {
        var queue = ArrayListQueue<BinaryNode<T>>()
        queue.enqueue(this)
        var sum = 0

        while(!queue.isEmpty) {
            var current = queue.dequeue()
            sum += current?.value as Int

            if(current.leftChild != null) {
                queue.enqueue(current.leftChild!!)
            }

            if(current.rightChild != null) {
                queue.enqueue(current.rightChild!!)
            }
        }
        return sum
    }

    fun binaryTreeSumRecursively(root: BinaryNode<T>?) : Int {
        if(root == null) {
            return 0
        }

        return root.value as Int + binaryTreeSumRecursively(root.leftChild) + binaryTreeSumRecursively(root.rightChild)
    }








    private fun isBST(tree: BinaryNode<T>?, min: T?, max: T?) :Boolean{
        tree ?: return true

        if(min != null && tree.value <= min) {
            return false
        } else if (max != null && tree.value >= max) {
            return false
        }

        return isBST(tree.leftChild, min, tree.value) && isBST(tree.rightChild, tree.value, max)
    }

    override fun equals(other: Any?): Boolean {
        return if(other != null && other is BinaryNode<*>) {
            this.value == other.value &&
                    this.leftChild == other.leftChild &&
                    this.rightChild == other.rightChild
        } else {
            false
        }

    }

    override fun toString() = diagram(this)

    private fun diagram(
        node: BinaryNode<T>?,
        top: String = "",
        root: String = "",
        bottom: String = ""
    ): String {
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
        leftChild?.traversePostOrder(visit)
        rightChild?.traversePostOrder(visit)
        visit(value)
    }

    // solution 1
    fun height(node: BinaryNode<T>? = this): Int {
        return node?.let { 1 + maxOf(height(node.leftChild), height(node.rightChild)) } ?: -1
    }

    //solution 2
    fun serialize(node: BinaryNode<T> = this): ArrayListQueue<T?> {
        val list = ArrayListQueue<T?>()
        node.traversePreOrderWithNull { list.enqueue(it) }
        return list
    }

    fun deserialize(list: ArrayListQueue<T?>): BinaryNode<T>? {
        val rootValue = list.dequeue() ?: return null

        val root = BinaryNode(rootValue)

        root.leftChild = deserialize(list)
        root.rightChild = deserialize(list)

        return root
    }


    fun traversePreOrderWithNull(visit: (T?) -> Unit) {
        visit(value)
        leftChild?.traversePreOrderWithNull(visit) ?: visit(null)
        rightChild?.traversePreOrderWithNull(visit) ?: visit(null)
    }

    fun binaryTreeHeight(root: BinaryNode<T>? = this) : Int {
        root ?: return -1
        var leftHeight = binaryTreeHeight(root.leftChild)
        var rightHieght = binaryTreeHeight(root.rightChild)

        return maxOf(leftHeight, rightHieght) + 1
    }



}


