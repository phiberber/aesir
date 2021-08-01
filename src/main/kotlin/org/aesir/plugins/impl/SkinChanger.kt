package org.aesir.plugins.impl

import org.aesir.Aesir.Companion.memory
import org.aesir.hooks.netvars
import org.aesir.hooks.signatures
import org.aesir.model.Player.Companion.me
import org.aesir.model.property.Weapon
import org.aesir.plugins.Plugin
import org.aesir.utils.debug

class SkinChanger: Plugin() {

    override suspend fun onTick() {

        val weaponEntity = memory.clientModule.int(signatures.dwEntityList + ((me.activeWeaponHandle and 0xFFF) -1) * 0x10)

        if(weaponEntity <= 0) {
            me.weapon = Weapon.NONE
            return
        }

        val definitionIndex = memory.process.int(weaponEntity + netvars.m_iItemDefinitionIndex)
        val fallbackPaintKit = memory.process.int(weaponEntity + netvars.m_nFallbackPaintKit)

        fun setPaint(paintKit: Int) {
            println(fallbackPaintKit)
            if(fallbackPaintKit == paintKit) return
            println(memory.process.int(weaponEntity + netvars.m_nFallbackPaintKit))
            memory.process[weaponEntity + netvars.m_nFallbackPaintKit] = paintKit
            memory.engineModule[signatures.dwClientState + signatures.clientstate_delta_ticks] = -1
        }

        memory.process[weaponEntity + netvars.m_iItemIDHigh] = -1

        val weapon = Weapon[definitionIndex]

        me.weapon = weapon
        debug(weapon.name)

        when(weapon) {
            Weapon.DESERT_EAGLE -> setPaint(962)
            Weapon.AK47 -> setPaint(801)
            Weapon.AWP -> setPaint(279)
            Weapon.GLOCK -> setPaint(38)
            Weapon.USP_SILENCER -> setPaint(313)
        }

        suspend(3)

    }

}