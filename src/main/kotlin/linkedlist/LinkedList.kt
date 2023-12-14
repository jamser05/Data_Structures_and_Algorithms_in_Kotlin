package linkedlist

class LinkedList<T> {
     var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0

    fun isEmpty(): Boolean {
        return size == 0
    }

    override fun toString(): String {
        if (isEmpty()) {
            return "Empty List"
        } else {
            return head.toString()
        }
    }

    fun push(value: T): LinkedList<T> {
        head = Node(value = value, next = head)
        if (tail == null) {
            tail = head
        }
        size++
        return this
    }

    fun append(value: T) {
        if (isEmpty()) {
            push(value)
            return
        }
        tail?.next = Node(value = value)

        tail = tail?.next
        size++
    }

    fun add(element: T): Boolean {
        append(element)
        return true
    }

    fun printInReverse() {
        this.nodeAt(0)?.printInReverse()
    }

    fun nodeAt(index: Int): Node<T>? {
        var currentNode = head
        var currentIndex = 0

        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }


    fun reversed() {
        this.nodeAt(0)?.reverseLinkedList()
    }

    fun reverseLink() {
        this.nodeAt(0)?.reverseLinkedListRecursively(this.head, null)
    }

    fun zipperLinkedList(head1: Node<T>?, head2: Node<T>?) : Node<T>? {
        return this.nodeAt(0)?.zipperList(head1, head2)
    }







}