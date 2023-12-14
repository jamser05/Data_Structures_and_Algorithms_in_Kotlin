package basicsorting

fun MutableList<Int>.bubbleSort() {
    if(this.size < 2) {
        return
    }

    for(end in (1 until  this.size).reversed()) {
        var swapped = false
        for(current in 0 until end) {
            if(this[current] > this[current + 1]) {
                this.swapAt(current, current + 1)
                swapped = true
            }
        }
        if(!swapped) {
            return
        }
    }

}

fun MutableList<Int>.swapAt(e1: Int, e2: Int) {
    this[e1] = this[e2].also { this[e2] = this[e1] }
}