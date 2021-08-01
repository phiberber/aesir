package org.aesir.memory

import net.rubygrapefruit.platform.internal.Platform
import org.aesir.*
import org.aesir.utils.*
import org.jire.arrowhead.Module
import org.jire.arrowhead.Process
import org.jire.arrowhead.processByName

val csgoProcessName get() = "csgo$processSuffix"
val clientModuleName get() = "client$moduleSuffix"
val engineModuleName get() = "engine$moduleSuffix"

var processSuffix = ""
var moduleSuffix = ""

class AesirMemory(val process: Process) {

    lateinit var aesir : Aesir
    var clientModule : Module
    var engineModule : Module

    companion object {

        fun setupPlatform(platform: Platform) {
            when {
                platform.isWindows -> {
                    processSuffix += ".exe"
                    moduleSuffix += ".dll"
                }
                platform.isLinux -> {
                    processSuffix += "_linux"
                    moduleSuffix += "_client.so"
                }
                platform.isMacOs -> {
                    processSuffix += "_osx"
                    moduleSuffix += ".dylib"
                }
            }
        }

        /**
         * Run a AesirMemory instance based in a processName, CSGO Process -> AesirMemory
         * @param processName, the csgo process name.
         */
        infix fun basedOn(platform: Platform) : AesirMemory {

            debug("Loading process with platform ${platform.id}")

            val CSGOExe = processByName(csgoProcessName, ACCESS_FLAGS) ?: waitWhile ( { it == null }, {
                debug("Please open cs go.")
                processByName(csgoProcessName, ACCESS_FLAGS)
            })!!

            CSGOExe.loadModules()

            console("Found CS:GO process.")

            return AesirMemory(CSGOExe)
        }

    }

    infix fun setInstance(newAesir: Aesir) : AesirMemory {
        this.aesir = newAesir
        return this
    }

    init {
        debug("» Loading Modules.")

        debug("Loading Player Module.")

        debug(process.modules.keys.joinToString(","))

        clientModule = process.modules[clientModuleName] ?: waitWhile( { it == null } , {
            debug("Can't find player module ${process.modules[clientModuleName]}")
            process.modules[clientModuleName]
        })!!

        debug("${clientModule.name} size")

        debug ("Loading Engine Module.")

        engineModule = process.modules[engineModuleName] ?: waitWhile( { it == null } , {
            debug("Can't find player module ${process.modules[engineModuleName]}")
            process.modules[engineModuleName]
        })!!
    }

}