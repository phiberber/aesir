package org.aesir.utils

import java.net.URL

val String.url: URL
    get() = URL(this)