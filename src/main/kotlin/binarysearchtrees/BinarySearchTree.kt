package binarysearchtrees

import binaryTrees.*
import com.sun.source.tree.BinaryTree
import jdk.incubator.vector.VectorOperators.Binary

class BinarySearchTree<T: Comparable<T>>() {
    var root: BinaryNode<T>? = null

    fun insert(value: T) {
        root = insert(root, value)
    }

    private fun insert(node: BinaryNode<T>?, value: T): BinaryNode<T> {
        root ?: return BinaryNode(value = value)

        if(node?.value!! > value) {
            node.leftChild = insert(node.leftChild, value)
        } else {
            node.rightChild = insert(node.rightChild, value)
        }

        return node
    }

    fun remove(value: T) {
        root = remove(root, value)
    }

    private fun remove(node: BinaryNode<T>?, value: T): BinaryNode<T>? {
        node ?: return null

        when {
            node.value == value -> {
                if (node.leftChild == null && node.rightChild == null) {
                    return null
                }
                if (node.leftChild == null) {
                    return node.rightChild
                }
                if (node.rightChild == null) {
                    return node.leftChild
                }
            }

            node.value > value -> node.leftChild = remove(node.leftChild, value)
            else -> node.rightChild = remove(node.rightChild, value)
        }
        return node
    }
}