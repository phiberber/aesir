package org.aesir

import kotlinx.coroutines.*
import net.rubygrapefruit.platform.internal.Platform
import org.aesir.frame.AesirFrame
import org.aesir.hooks.Hooks
import org.aesir.memory.AesirMemory
import org.aesir.model.Player
import org.aesir.plugins.PluginBootstrap
import org.jire.arrowhead.keyPressed
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class Aesir private constructor(val config: AesirConfig) {

    var debugMode = false

    companion object {

        lateinit var memory: AesirMemory

        infix fun basedIn(config: AesirConfig): Aesir = Aesir(config)

        infix fun autoInitializeWith(config: AesirConfig): Aesir = basedIn(config).initialize()

    }

    fun initialize(): Aesir {
        debugMode = config.debugMode

        Hooks.fetchData()

        AesirMemory.setupPlatform(Platform.current())

        memory = AesirMemory basedOn Platform.current()

        Player.setupPlayers(memory)

        PluginBootstrap.initializePlugins()

        return this
    }

}

suspend fun main() {

    AesirFrame.frame = AesirFrame()

    println("===========================================")
    println("Aesir the land of gods.")
    println("Authors: Pb600 and PeixeDev.")
    println("Created by gods, made for gods.")
    println("===========================================")
    println("[Aesir] » Loading config $CONFIG_PATH")

    File(DATA_PATH).apply {
        if (!isDirectory)
            mkdirs()
    }

    Aesir autoInitializeWith (AesirConfig basedIn CONFIG_PATH)

    while (true) {
        PluginBootstrap.plugins.forEach {
            if (it.enabled && (it.suspendedTicks == 0 || --it.suspendedTicks == 0)) {
                it.onTick()
            }
        }
        Thread.sleep(5)
    }
}

