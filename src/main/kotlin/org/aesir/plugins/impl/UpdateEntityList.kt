package org.aesir.plugins.impl

import com.badlogic.gdx.scenes.scene2d.actions.Actions.delay
import com.sun.jna.Memory
import org.aesir.Aesir.Companion.memory
import org.aesir.hooks.netvars
import org.aesir.hooks.signatures
import org.aesir.model.Player
import org.aesir.plugins.Plugin
import org.aesir.utils.debug
import org.jire.arrowhead.keyPressed
import java.awt.event.KeyEvent
import kotlin.math.sign

class UpdateEntityList: Plugin() {

    override suspend fun onTick() {

        fun getUsersTable(index: Int): Memory? {
            val clientState = memory.engineModule.int(signatures.dwClientState)
            val userInfoTable = memory.process.int(clientState + signatures.dwClientState_PlayerInfo)
            val items = memory.process.int(memory.process.int(userInfoTable + 0x40) + 0xC)
            return memory.process.read(memory.process.int(items + 0x28 + ((index) * 0x34)), 320)
        }

        for(i in 0..32) {
            val m_flDetectedByEnemySensorTime = 0x3944 + 0x34
            val sensorTime = if(!keyPressed(KeyEvent.VK_ALT)) 86400f else 0f
            val player = Player.fromEntityList(i)
            val playerInfo = getUsersTable(i)
            player.spotted = true
            memory.process[player.address + m_flDetectedByEnemySensorTime] = sensorTime
        }

    }


}