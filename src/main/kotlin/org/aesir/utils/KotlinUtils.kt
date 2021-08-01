package org.aesir.utils

import io.github.classgraph.ClassGraph

inline infix fun <reified T> String.constructClasses(crossinline afterConstruct: T.() -> Unit) {
    ClassGraph().enableAllInfo().whitelistPackages(this).scan().allClasses.forEach {
        afterConstruct(it.loadClass().getConstructor().newInstance() as T)
    }
}