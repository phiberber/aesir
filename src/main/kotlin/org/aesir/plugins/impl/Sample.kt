package org.aesir.plugins.impl

import org.aesir.frame.AesirFrame
import org.aesir.model.Player.Companion.me
import org.aesir.plugins.Plugin

class Sample: Plugin() {

    override suspend fun onTick() {

        AesirFrame.variables["team"] = "Team: ${me.team}"
        AesirFrame.variables["alive"] = "Alive: ${me.alive}"
        AesirFrame.variables["health"] = "Health: ${me.health}"
        AesirFrame.variables["lifestate"] = "Life State: ${me.lifeState}"
        AesirFrame.variables["armor"] = "Armor: ${me.armor}"
        AesirFrame.variables["weapon"] = "Weapon: ${me.weapon.name.replace('_', ' ').toLowerCase().capitalize()}"
        AesirFrame.variables["scoped"] = "Scoped: ${me.scoped}"
        AesirFrame.variables["spotted"] = "Spotted: ${me.spotted}"
        AesirFrame.variables["defusing"] = "Defusing: ${me.defusing}"
        AesirFrame.variables["reload"] = "Reloading: ${me.reloading}"
        AesirFrame.variables["ground"] = "Ground: ${me.onGround}"

        AesirFrame.frame.updateVariables()

    }

}