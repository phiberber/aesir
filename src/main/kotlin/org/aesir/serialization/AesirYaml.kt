package org.aesir.serialization

import org.aesir.CONFIG_PATH
import org.aesir.DEFAULT_CONFIG
import org.aesir.utils.debug
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.error.YAMLException
import java.io.File
import java.io.OutputStreamWriter

class AesirYaml {
    companion object {

        private var yaml = Yaml()

        @JvmStatic
        fun <T : Any> write(fileWriter: OutputStreamWriter, content: T) {
            debug("Writing YAML to writer with content: ${content::class.java.simpleName}")
            yaml.dump(content, fileWriter)
        }

        @JvmStatic
        fun <T : Any> write(filePath: String, content: T) {
            debug("Writing YAML to $filePath with content: ${content::class.java.simpleName}")
            return yaml.dump(content, File(filePath).writer())
        }

        @JvmStatic
        fun <T : Any> convert(content: T): String {
            debug("Converting " + content::class.java + " to YAML ")
            return yaml.dump(content)
        }

        @JvmStatic
        fun <T> parse(fileContent: String, classTo: Class<T>) : T {
            debug("Loading YAML as ${classTo.simpleName}")
            return yaml.loadAs(fileContent, classTo)
        }

    }


}

val Any.yaml : String get() = AesirYaml.convert(this)
