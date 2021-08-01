package org.aesir.plugins

import io.github.classgraph.ClassGraph

object PluginBootstrap {

    val plugins = mutableListOf<Plugin>()

    fun initializePlugins() {
        plugins.clear()

        ClassGraph().enableAllInfo().whitelistPackages("org.aesir.plugins.impl").scan().use { result ->
            val pluginClasses = result.getSubclasses("org.aesir.plugins.Plugin")
            pluginClasses.loadClasses().forEach { clazz ->
                plugins += (clazz.newInstance() as Plugin).initialize()
            }
        }

    }
}