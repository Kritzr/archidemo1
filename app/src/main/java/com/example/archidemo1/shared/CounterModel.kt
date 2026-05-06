// FILE: app/src/main/java/com/example/archidemo1/shared/CounterModel.kt

package com.example.archidemo1.shared

data class CounterModel(val count: Int = 0) {
    fun increment() = copy(count = count + 1)
    fun decrement() = copy(count = count - 1)
    fun reset()     = copy(count = 0)
}