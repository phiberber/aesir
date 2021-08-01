package org.aesir.utils

import org.jire.arrowhead.Source
import org.jire.arrowhead.unsign

fun Source.uint(address: Long, offset: Long = 0) = int(address, offset).unsign()
fun Source.uint(address: Int, offset: Long = 0) = int(address, offset).unsign()