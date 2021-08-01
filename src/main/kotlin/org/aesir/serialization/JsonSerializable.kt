package org.aesir.serialization

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.net.URL
import java.util.*

val objectMapper = ObjectMapper().registerModule(KotlinModule())

fun Any.toJson(pretty: Boolean = false): String = objectMapper.writeValueAsString(this)

inline fun <reified T : Any> String.fromJson(): T = objectMapper.readValue(this, T::class.java)
inline fun <reified T : Any> URL.fromJson(): T = objectMapper.readValue(this, T::class.java)

/*
fun json(build: JsonObjectBuilder.() -> Unit): JsonObject {
    val builder = JsonObjectBuilder()
    builder.build()
    return builder.json
}

class JsonObjectBuilder {
    val json = JsonObject()

    infix fun <T> String.to(value: T) {
        json.put(this, value)
    }
}*/