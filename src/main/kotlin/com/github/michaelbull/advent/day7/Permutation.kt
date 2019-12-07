package com.github.michaelbull.advent.day7

suspend fun <T> List<T>.forEachPermutation(
    permutation: List<T> = emptyList(),
    action: suspend (List<T>) -> Unit
) {
    if (isEmpty()) {
        action(permutation)
    } else {
        indices.forEach {
            val spliced = subList(0, it) + subList(it + 1, size)
            val merged = permutation + this[it]
            spliced.forEachPermutation(merged, action)
        }
    }
}
