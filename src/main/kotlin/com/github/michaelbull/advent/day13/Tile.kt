package com.github.michaelbull.advent.day13

sealed class Tile {
    object Empty : Tile()
    object Wall : Tile()
    object Block : Tile()
    object HorizontalPaddle : Tile()
    object Ball : Tile()
}

fun Long.toTile(): Tile {
    return when (this) {
        0L -> Tile.Empty
        1L -> Tile.Wall
        2L -> Tile.Block
        3L -> Tile.HorizontalPaddle
        4L -> Tile.Ball
        else -> error("Unknown tile $this")
    }
}
