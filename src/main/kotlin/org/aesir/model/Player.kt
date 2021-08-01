package org.aesir.model

import org.aesir.Aesir.Companion.memory
import org.aesir.hooks.netvars
import org.aesir.hooks.signatures
import org.aesir.memory.AesirMemory
import org.aesir.model.property.Weapon
import org.jire.arrowhead.get

class Player(var memory: AesirMemory, val address: Int) {

    val health : Int
        get() = memory.process.int(address + netvars.m_iHealth)

    val lifeState : Long
        get() = memory.process.long(address + netvars.m_lifeState)

    var spotted : Boolean
        get() = memory.process.boolean(address + netvars.m_bSpotted)
        set(value) { memory.process[address + netvars.m_bSpotted] = value }

    val armor: Int
        get() = memory.process.int(address + netvars.m_ArmorValue)

    val team: Int
        get() = memory.process.int(address + netvars.m_iTeamNum)

    val scoped: Boolean
        get() = memory.process.boolean(address + netvars.m_bIsScoped)

    val defusing: Boolean
        get() = memory.process.boolean(address + netvars.m_bIsDefusing)

    val reloading: Boolean
        get() = memory.process.boolean(address + netvars.m_bInReload)

    val onGround : Boolean
        get() = memory.process.int(address + netvars.m_fFlags) and 1 == 1

    var weapon: Weapon = Weapon.NONE

    val activeWeaponHandle : Int
        get() = memory.process.int(address + netvars.m_hActiveWeapon)

    val crossHairId: Int
        get() = memory.process.int(address + netvars.m_iCrosshairId)

    val alive: Boolean
        get() = health > 0 && lifeState == 0L

    companion object {

        lateinit var me: Player

        fun fromEntityList(offset: Int): Player =
            Player(memory, memory.clientModule.int(signatures.dwEntityList + offset * 0x10))

        fun setupPlayers(aesirMemory: AesirMemory) {
            val playerAddress : Int = aesirMemory.clientModule[signatures.dwLocalPlayer]
            me = Player(aesirMemory, playerAddress)
        }

    }

    fun jump() {
        memory.clientModule[signatures.dwForceJump] = 6
    }

    fun shoot() {
        memory.clientModule[signatures.dwForceAttack] = 6
        Thread.sleep(20)
        memory.clientModule[signatures.dwForceAttack] = 4
    }


}

