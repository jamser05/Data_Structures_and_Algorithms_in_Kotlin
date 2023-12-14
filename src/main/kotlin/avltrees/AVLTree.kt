package avltrees

import kotlin.math.pow

class AVLTree<T: Comparable<T>> {
    var root: AVLNode<T>? = null

    fun insert(value: T) {
        root = insert(root, value)
    }

    private fun insert(node: AVLNode<T>?, value: T ): AVLNode<T> {
        node ?: return AVLNode(value)

        if (value < node.value) {
            node.leftChild = insert(node.leftChild, value)
        } else {
            node.rightChild = insert(node.rightChild, value)
        }

        val balancedNode = balanced(node)
        balancedNode.height = maxOf(balancedNode.leftHeight ?: 0 , balancedNode.rightHeight ?: 0) + 1
        return balancedNode
    }

    fun remove(value: T) {
        root = remove(root, value)
    }

    private fun remove(node: AVLNode<T>?, value: T): AVLNode<T>? {
        node ?: return null

        when {
            value == node.value -> {
                if (node.rightChild == null && node.leftChild == null) {
                    return null
                }
                if (node.rightChild == null) {
                    return node.leftChild
                }
                if (node.leftChild == null) {
                    return node.rightChild
                }
            }
            value > node.value -> node.rightChild = remove(node.rightChild, value)
            else -> node.leftChild = remove(node.leftChild, value)
        }

        val balancedNode = balanced(node)
        balancedNode.height = maxOf(balancedNode.leftHeight ?: -1, balancedNode.rightHeight ?: -1)
        return balancedNode
    }

    fun leftRotate(node: AVLNode<T>) : AVLNode<T> {
        val pivot = node.rightChild
        node.rightChild = pivot?.leftChild

        pivot?.rightChild = node

        pivot?.height =  maxOf(pivot?.leftHeight!!, pivot.rightHeight) + 1
        node.height = maxOf(node.leftHeight, node.rightHeight) + 1

        return pivot
    }

    fun rightRotate(node: AVLNode<T>) : AVLNode<T> {
        val pivot = node.leftChild
        node.leftChild = pivot?.rightChild

        pivot?.leftChild = node

        pivot?.height = maxOf(pivot?.leftHeight!!, pivot.rightHeight) + 1
        node.height = maxOf(node.leftHeight, node.rightHeight) + 1

        return pivot
    }


    fun leftRightRotate(node: AVLNode<T>) : AVLNode<T> {
        val leftChild = node.leftChild ?: node
        node.leftChild = leftRotate(leftChild)
        return rightRotate(node)
    }

    fun rightLeftRotate(node: AVLNode<T>) : AVLNode<T> {
        val rightChild = node.rightChild ?: node
        node.rightChild = leftRotate(rightChild)
        return leftRotate(node)
    }






    private fun balanced(node: AVLNode<T>): AVLNode<T> {
        return when(node.balanceFactor) {
            // leftHeavy
            // -1
            2 -> {
                if (node.leftChild?.balanceFactor == -1) {
                    leftRightRotate(node)
                } else {
                    rightRotate(node)
                }
            }
            // rightHeavy
            // 1
            -2 -> {
                if(node.rightChild?.balanceFactor == 1) {
                    rightLeftRotate(node)
                } else {
                    leftRotate(node)
                }
            } else -> node
        }
    }

    override fun toString() = root?.toString() ?: "Empty Tree"

    fun countLeafNodes(node: AVLNode<T>?): Int {
        node ?: return 0

        if(node.leftChild == null && node.rightChild == null) {
            return 1
        }

        return countLeafNodes(node.leftChild) + countLeafNodes(node.rightChild)
    }
}

