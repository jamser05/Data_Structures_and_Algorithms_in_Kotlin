package linkedlist

data class Node<T>(var value: T, var next: Node<T>? = null) {
    override fun toString(): String {
        return if (next != null) {
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }

    fun printInReverse() {
        this.next?.printInReverse()

        if (this.next != null) {
            print(" -> ")
        }
        print(this.value.toString())
    }

    fun reverseLinkedList(): Node<T>? {
        var current = this
        var prev : Node<T>? = null

        while(current != null) {
            var next = current.next
            current.next = prev
            prev = current
            current = next ?: break
        }

        return prev
    }

    fun reverseLinkedListRecursively(head: Node<T>?, prev: Node<T>? = null): Node<T>? {
        if(head == null) {
            return prev
        }

        var next = head.next
        head.next = prev
        return reverseLinkedListRecursively(next, head)
    }

    fun zipperList(head1: Node<T>?, head2: Node<T>?): Node<T>? {
        var result = head1
        var current1 = head1?.next
        var current2 = head2
        var counter = 0

        while(current1 != null && current2 != null) {
            if(counter % 2 == 0) {
                result?.next = current2
                current2 = current2.next ?: break
            } else  {
                result?.next = current1 ?: break
                current1 = current1.next ?: break
            }
            result = result?.next
            counter++
        }

        if(current1 != null) {
            result?.next = current1
        }

        if(current2 != null) {
            result?.next = current2
        }

        return head1
    }
}