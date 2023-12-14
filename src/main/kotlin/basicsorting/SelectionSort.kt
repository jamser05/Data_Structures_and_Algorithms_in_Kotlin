package basicsorting

fun MutableList<Int>.selectionSort() {
    if(this.size < 2) {
        return
    }

    for(end in 0 until this.size) {
        var smallest = end
        for(current in (end) until this.size) {
            if(this[current] < this[smallest]) {
                smallest = current
            }
        }

        if(smallest != end) {
            this.swapAt(smallest, end)
        }
    }
}