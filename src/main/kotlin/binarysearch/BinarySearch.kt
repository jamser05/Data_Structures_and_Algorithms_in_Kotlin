package binarysearch


// O(n) time & O(n) Space
fun MutableList<Int>.binarySearchRec(start: Int, end: Int, target: Int): Int {
    if(start > end) {
        return 0
    }

    val middle = start + ((end - start) / 2)

    return when {
        this[middle] == target -> middle
        this[middle] > target -> this.binarySearchRec(start, middle - 1, target)
        else -> this.binarySearchRec(middle + 1, end, target)
    }
}

// O(n) Time & O(1) Space
fun MutableList<Int>.binarySearchIterative(target: Int) : Int  {
    var start = 0
    var end = this.size - 1

    while(start <= end) {
        var middle = start + ((end - start) / 2)

        if(this[middle] == target) {
            return middle
        } else if (this[middle] > target) {
            end = middle - 1
        } else {
            start = middle + 1
        }
    }

    return -1
}