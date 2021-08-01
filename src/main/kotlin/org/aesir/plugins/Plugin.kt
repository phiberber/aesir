package org.aesir.plugins

import org.aesir.utils.debug

abstract class Plugin {

    var enabled = true
    var suspendedTicks = 0

    open var tickInterval = 8L
    open var keybind = -1

    abstract suspend fun onTick()

    private fun onInitialize() {}

    fun suspend(ticks: Int) {
        this.suspendedTicks = ticks
    }

    fun initialize(): Plugin {
        debug("Initializing ${javaClass.simpleName}")
        this.onInitialize()
        return this
    }

}