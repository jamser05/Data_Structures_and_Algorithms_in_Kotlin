package radixsort

fun main() {
    var list = mutableListOf<Int>(1, 3, 4, 8 ,9 ,10, 332, 444, 22, 1000)
    list.radixSortLSD()
    println(list)
}