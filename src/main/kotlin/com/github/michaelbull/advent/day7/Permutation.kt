package com.github.michaelbull.advent.day7

suspend fun IntArray.forEachPermutation(
    from: Int = 0,
    to: Int = size,
    action: suspend (IntArray) -> Unit = {}
) {
    if (from >= to) {
        action(this)
    } else {
        for (i in from until to) {
            val swap = (from until i).none { this[it] == this[i] }

            if (swap) {
                swap(from, i)
                forEachPermutation(from + 1, to, action)
                swap(from, i)
            }
        }
    }
}

private fun IntArray.swap(left: Int, right: Int) {
    val temp = this[left]
    this[left] = this[right]
    this[right] = temp
}
