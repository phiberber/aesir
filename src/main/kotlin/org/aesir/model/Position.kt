package org.aesir.model

data class Position(var x: Int, var y: Int, var z: Int) {

    fun add(position: Position) {
        x += position.x
        y += position.y
        z += position.z
    }

    fun reduce(position: Position) {
        x -= position.x
        y -= position.y
        z -= position.z
    }

}