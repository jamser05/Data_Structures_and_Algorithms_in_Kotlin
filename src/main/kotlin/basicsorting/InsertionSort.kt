package basicsorting

fun MutableList<Int>.insertionSort() {
    if(this.size < 2) {
        return
    }

    for(end in 1 until this.size) {
        for(current in (1 ..end).reversed()) {
            if(this[current] < this[current - 1]) {
                this.swapAt(current, current - 1)
            } else {
                break
            }
        }
    }
}