package com.example.treasurefinder

import kotlin.random.Random

fun findTreasure(direction: String): Pair<Boolean, Int> {
    // Randomly determine if treasure is found
    val isTreasureFound = Random.nextBoolean()

    // If treasure is found, generate a random number between 1 and 8
    val treasureCount = if (isTreasureFound) {
        Random.nextInt(1, 9) // nextInt is exclusive of upper bound, so 9 gives max 8
    } else {
        0
    }

    return Pair(isTreasureFound, treasureCount)
}
