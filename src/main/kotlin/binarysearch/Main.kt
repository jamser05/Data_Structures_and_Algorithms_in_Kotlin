package binarysearch

fun main(args: Array<String>) {
    var list = mutableListOf<Int>(1, 2, 3,4 ,5, 6, 7, 8)
    println(list.binarySearchIterative(4))
    println(list.indexOf(4))
    println(list.binarySearchRec(0, list.size - 1, 4))
}