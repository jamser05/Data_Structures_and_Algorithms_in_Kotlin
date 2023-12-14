package queue

interface Queue<T> {
    fun enqueue(element: T): Boolean

    fun dequeue(): T?

    val count: Int

    val isEmpty: Boolean
        get() = count == 0

    fun peek(): T?
}