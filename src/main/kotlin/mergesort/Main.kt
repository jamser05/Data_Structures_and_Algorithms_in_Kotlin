package mergesort

fun main(args: Array<String>) {
    var listOne = mutableListOf<Int>(9, 8, 7, 6, 5, 4, 3, 2, 1)
    println(listOne.mergeSort())
}