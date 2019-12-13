package com.github.michaelbull.advent.day13

class Grid private constructor(
    private val tiles: MutableMap<Position, Tile>
) : MutableMap<Position, Tile> by tiles {

    constructor() : this(mutableMapOf())

    inline fun position(predicate: (Tile) -> Boolean): Position {
        return entries.first { (_, tile) -> predicate(tile) }.key
    }

    operator fun get(x: Long, y: Long): Tile {
        return tiles.getOrElse(Position(x, y)) { Tile.Empty }
    }

    operator fun set(x: Long, y: Long, tile: Tile) {
        tiles[Position(x, y)] = tile
    }
}
