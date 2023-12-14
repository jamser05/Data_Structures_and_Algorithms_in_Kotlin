package basicsorting

fun main(args: Array<String>) {
    var list = mutableListOf<Int>(1, 9, 3, 5, 7, 10)
    list.bubbleSort()
    println(list)
    list.random()
    list.selectionSort()
    println(list)
    list.random()
    list.insertionSort()
    println(list)
}