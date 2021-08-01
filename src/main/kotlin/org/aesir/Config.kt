package org.aesir


import com.sun.jna.Platform
import com.sun.jna.platform.win32.WinNT
import org.aesir.serialization.AesirYaml
import org.aesir.utils.debug
import org.aesir.utils.returnAfter
import org.yaml.snakeyaml.error.YAMLException
import java.io.File

/**
 * Access flags when trying to access CSGO Process.
 */
const val ACCESS_FLAGS = WinNT.PROCESS_QUERY_INFORMATION or WinNT.PROCESS_VM_READ or WinNT.PROCESS_VM_WRITE

val DATA_PATH = "${System.getenv("APPDATA")}\\Aesir\\"
val CONFIG_PATH = "${DATA_PATH}\\config.yml"

const val CREATE_CONFIG_IF_NOT_EXISTENT = true

val DEFAULT_CONFIG = AesirConfig()

data class AesirConfig(var aimbotConfig: AimbotConfig? = AimbotConfig(), var debugMode: Boolean = false) {

    companion object {

        /**
         * Read a file content in YAML and generates a AesirConfig instance by the content
         * @param filePath the path for the file where you want to base the config in, normally it's the CONFIG_PATH.
         */
        infix fun basedIn(filePath: String): AesirConfig {

            return DEFAULT_CONFIG

            debug("Creating config based in $filePath")

            return File(filePath).run {
                if (CREATE_CONFIG_IF_NOT_EXISTENT && !exists()) {
                    debug("Saving default config.")
                    DEFAULT_CONFIG saveTo this
                } else try {
                    AesirYaml.parse(this.readText(), AesirConfig::class.java)
                } catch (yamlException: YAMLException) {
                    DEFAULT_CONFIG saveTo CONFIG_PATH
                    AesirConfig basedIn CONFIG_PATH
                }
            }
        }

    }


    /**
     * Save this instance as a YAML in the file specified.
     * @param file, the file that you want to save the changes, normally it's the CONFIG_PATH file.
     */
    infix fun saveTo(file: File) = this.returnAfter {
        debug("Saving config to ${file.absolutePath}")
        AesirYaml.write(file.writer(), this)
    }

    /**
     * Save this instance as a YAML in the path specified.
     * @param filePath, the file path that you want to save the changes, normally it's the CONFIG_PATH.
     */
    infix fun saveTo(filePath: String) = this.returnAfter {
        debug("Saving config to path $filePath")
        AesirYaml.write(filePath, this)
    }

    /**
     * Convert this instance to a YAML.
     */
    override fun toString(): String = AesirYaml.convert(this)

}

data class RecoilConfig(
    var SHOW_CURRENT_RECOIL: Boolean = true
)

data class AimbotConfig(
    var fov: Int = 5,
    var softness: Int = 100
)