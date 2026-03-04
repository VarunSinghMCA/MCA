package com.example.simplecounterapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel(){
    private val _count: MutableState<Int> = mutableIntStateOf(0)
    val count: MutableState<Int> = _count

    fun Increment(){ _count.value++ }
    fun Decrement(){ _count.value-- }

    fun Reset(){ _count.value = 0 }

    fun customIncrement(value: Int){ _count.value += value }
    fun customDecrement(value: Int){ _count.value -= value }

}