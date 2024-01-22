package com.example.machinetesttask.task1.model


data class Tracks(
    val items: List<Item>,
    val limit: Int,
    val next: Any,
    val offset: Int,
    val previous: Any,
    val total: Int
)