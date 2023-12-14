package heap

fun main(args: Array<String>) {
    val array = arrayListOf(1, 12, 3, 4, 1, 6, 8, 7)
    val array2 = arrayListOf(1, 12, 3, 4, 1, 6, 8, 7)

    val inverseComparator = object : Comparator<Int> {
        override fun compare(o1: Int, o2: Int): Int {
            return o2.compareTo(o1)
        }
    }

    val maxHeap = ComparableHeapImpl.create(array)

    println("MaxHeap Below")
    while(!maxHeap.isEmpty) {
        println(maxHeap.remove())
    }

    println("MinHeap below")

    val minHeap = ComparatorHeapImpl.create(array2, inverseComparator)
    while(!minHeap.isEmpty) {
        println(minHeap.remove())
    }

    val priorityQueue = ComparableHeapImpl.create(array)
    while(!priorityQueue.isEmpty) {
        println(priorityQueue.remove())
    }
    priorityQueue.insert(9)


}