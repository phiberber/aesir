package org.aesir.plugins.impl

import org.aesir.model.Player.Companion.me
import org.aesir.plugins.Plugin
import org.jire.arrowhead.keyPressed
import java.awt.event.KeyEvent

class BunnyHop : Plugin() {

    override var tickInterval: Long = 3L

    override suspend fun onTick() {
        if(me.onGround && keyPressed(KeyEvent.VK_SPACE))
            me.jump()
    }

}