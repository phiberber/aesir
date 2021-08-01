package org.aesir.utils

infix fun <T: Any> T.returnAfter(block: T.() -> Unit) : T {
    block(this)
    return this
}

fun <T> waitWhile(condition: (T?) -> Boolean, whileThat: (T?) -> T?) : T? {
    var returnResult : T? = null
    while(condition(returnResult)) {
        returnResult = whileThat.invoke(returnResult)
        Thread.sleep(5500)
    }
    return returnResult
}