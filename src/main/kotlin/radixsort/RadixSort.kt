package radixsort

fun MutableList<Int>.radixSortLSD() {
    val base = 10
    var digits = 1
    var done = false

    while(!done) {
        done = true

        var buckets = arrayListOf<MutableList<Int>>().apply {
            for(i in 0..9) {
                this.add(mutableListOf())
            }
        }

        this.forEach {
            number ->
            var remainingPart = number / digits
            var digit = remainingPart % base
            buckets[digit].add(number)

            if(remainingPart > 0) {
                done = false
            }
        }

        digits *= base

        this.clear()
        this.addAll(buckets.flatten())
    }
}