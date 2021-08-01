package org.aesir.plugins.impl

import org.aesir.Aesir.Companion.memory
import org.aesir.hooks.signatures
import org.aesir.model.Player
import org.aesir.model.Player.Companion.me
import org.aesir.plugins.Plugin
import org.aesir.utils.debug
import org.jire.arrowhead.keyPressed
import java.awt.event.KeyEvent.VK_CONTROL
import java.awt.event.KeyEvent.VK_SHIFT

class Triggerbot: Plugin() {

    override suspend fun onTick() {

        if(!keyPressed(VK_CONTROL))
            return

        val myTeam = me.team

        val crossHairId = me.crossHairId

        if(crossHairId != 0 || crossHairId < 64) {
            val player = Player.fromEntityList(crossHairId - 1)
            if(player.team != myTeam && player.alive) {
                me.shoot()
            }
        }

    }

}