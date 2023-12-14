package mergesort

fun MutableList<Int>.mergeSort() : MutableList<Int>{
    if(this.size < 2) {
        return this
    }

    var middle = this.size / 2

    var leftList = this.subList(0, middle).mergeSort()
    var rightList = this.subList(middle, this.size).mergeSort()

    return merge(leftList, rightList)
}

fun MutableList<Int>.merge(list1: MutableList<Int>, list2: MutableList<Int>): MutableList<Int> {
    var leftIndex = 0
    var rightIndex = 0
    var result = mutableListOf<Int>()

    while(leftIndex < list1.size && rightIndex < list2.size) {
        var leftElement = list1[leftIndex]
        var rightElement = list2[rightIndex]

        if(leftElement < rightElement) {
            result.add(leftElement)
            leftIndex++
        } else if(leftElement > rightElement) {
            result.add(rightElement)
            rightIndex++
        } else {
            result.add(leftElement)
            leftIndex++
            result.add(rightElement)
            rightIndex++
        }
    }

    if(leftIndex < list1.size) {
        result.addAll(list1.subList(leftIndex, list1.size))
    }

    if(rightIndex < list2.size) {
        result.addAll(list2.subList(rightIndex, list2.size))
    }

    return result
}