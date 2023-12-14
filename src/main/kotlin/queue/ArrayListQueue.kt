package queue

class ArrayListQueue<T> : Queue<T> {
    var arrayQueueList = arrayListOf<T>()

    override fun enqueue(element: T): Boolean {
        arrayQueueList.add(element)
        return true
    }

    override fun dequeue(): T? {
        if(isEmpty) {
            return null
        }
        return arrayQueueList.removeAt(0)
    }

    override val count: Int
        get() = arrayQueueList.size

    override fun peek(): T? {
        return arrayQueueList.getOrNull(0)
    }
}
