package heap

import java.util.*
import java.util.Arrays.sort
import kotlin.collections.ArrayList

interface Collection<Element> {
    val count: Int
        get

    val isEmpty: Boolean
        get() = count == 0

    fun insert(element: Element)

    fun remove(): Element?

    fun remove(index: Int): Element?
}

interface Heap<Element> : Collection<Element> {
    fun peek(): Element?

}

abstract class AbstractHeap<Element>(): Heap<Element> {
    abstract fun compare(a: Element, b: Element): Int

    var elements: ArrayList<Element> = ArrayList()

    override val count: Int
        get() = elements.size

    override fun peek(): Element? {
        return elements.first()
    }

    private fun leftChildIndex(index: Int): Int {
        return (2 * index) + 1
    }
    private fun rightChildIndex(index: Int) : Int {
        return (2 * index) + 2
    }

    private fun parentIndex(index: Int) : Int {
        return (index - 1) / 2
    }

    override fun insert(element: Element) {
        elements.add(element)
        siftUp(count - 1)
    }

    private fun siftUp(index: Int) {
        var child = index
        var parent = parentIndex(child)

        while(child > 0 && compare(elements[child], elements[parent]) > 0) {
            Collections.swap(elements, child, parent)
            child = parent
            parent = parentIndex(child)
        }
    }

    fun getNthSmallestElement(n: Element): Element? {
        var current = 1
        while(!isEmpty) {
            var element = remove()
            if(current == n) {
                return element
            }
            current++
        }
        return null
    }

    fun merge(heap: AbstractHeap<Element>) {
        elements.addAll(heap.elements)
        buildHeap()
    }

    private fun buildHeap() {
        if (!elements.isEmpty()) {
            (count / 2 downTo 0).forEach {
                siftDown(it)
            }
        }
    }

    fun isMinHeap(): Boolean {
        if(isEmpty) return true
        (count / 2 - 1 downTo 0).forEach {
            val left = leftChildIndex(it)
            val right = rightChildIndex(it)
            if(left < count && compare(elements[left], elements[it]) < 0) {
                return false
            }
            if (right < count && compare(elements[right], elements[it]) < 0) {
                return false
            }
        }
        return true
    }

    override fun remove() : Element? {
        if (isEmpty) return null

        Collections.swap(elements, 0, count - 1)
        val item = elements.removeAt(count - 1)
        siftDown(0)
        return item
    }


    override fun remove(index: Int): Element? {
        if(index >= elements.size) {
            return null
        }
        return if (index == count - 1) {
            return elements.removeAt(count -1)
        } else {
            Collections.swap(elements, index, count - 1)
            var item = elements.removeAt(count - 1)
            siftDown(index)
            return item
        }
    }

    private fun siftDown(index: Int) {
        var parent = index

        while(true) {
            var leftChild = leftChildIndex(parent)
            var rightChild = rightChildIndex(parent)
            var candidate = parent

            if(leftChild < count && compare(elements[leftChild], elements[candidate]) > 0) {
                candidate = leftChild
            }
            if(rightChild < count && compare(elements[rightChild], elements[candidate]) > 0) {
                candidate = rightChild
            }
            if(candidate == parent) {
                return
            }

            Collections.swap(elements, parent, candidate)
            parent = candidate
        }
    }




    private fun index(element: Element, i: Int): Int? {
        if (i >= count) {
            return null
        }
        if (compare(element, elements[i]) > 0) {
            return null
        }
        if (element == elements[i]) {
            return i
        }

        val leftChildIndex = index(element, leftChildIndex(i))
        if (leftChildIndex != null) return leftChildIndex

        val rightChildIndex = index(element, rightChildIndex(i))
        if (rightChildIndex != null) return rightChildIndex

        return null
    }

    protected fun heapify(values: ArrayList<Element>) {
        elements = values
        if(elements.isNotEmpty()) {
            (count / 2 downTo 0).forEach {
                siftDown(it)
            }
        }
    }
}


class ComparableHeapImpl<Element : Comparable<Element>>() : AbstractHeap<Element>()  {
    override fun compare(a: Element, b: Element): Int  {
        return a.compareTo(b)
    }
    companion object {
        fun <Element : Comparable<Element>> create(elements: ArrayList<Element>
        ): Heap<Element> {
            val heap = ComparableHeapImpl<Element>()
            heap.heapify(elements)
            return heap
        }
    }
}

class ComparatorHeapImpl<T: Any>(private val comparator: Comparator<T>
) : AbstractHeap<T>() {

    companion object {
        fun <T: Any> create(
            elements: ArrayList<T>,
            comparator: Comparator<T>
        ): Heap<T> {
            val heap = ComparatorHeapImpl(comparator)
            heap.heapify(elements)
            return heap
        }
    }

    override fun compare(a: T, b: T): Int =
        comparator.compare(a, b)
}