package org.aesir.utils

import org.aesir.Aesir
import org.aesir.model.Player.Companion.me

lateinit var currentAesirInstance : Aesir

infix fun <T : Any> T.debug(text: String) {
    if(!::currentAesirInstance.isInitialized || currentAesirInstance.debugMode) {
        println("[${this::class.java.simpleName}] Debug » $text")
    }
}

infix fun <T : Any> T.warning(text: String) {
    println("[${this::class.java.simpleName}] Warning » $text")
}

infix fun <T : Any> T.console(text: String) {
    println("[${this::class.java.simpleName}] » $text")
}

infix fun <T : Any> T.throwError(text: String) : Nothing {
    throw Exception(text)
}

class Console {

    companion object {

        infix fun performCommand(command: String) {
            val realCommand = command.toLowerCase()

            debug("Performing $realCommand")

            if(realCommand == "update hooks") {

            }

            ::me.get().javaClass.getDeclaredMethod(realCommand).invoke(me)
        }

    }

}