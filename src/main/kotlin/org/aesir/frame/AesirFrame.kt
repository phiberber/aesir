package org.aesir.frame

import org.jetbrains.kotlin.fir.resolve.getOrPut
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextArea

class AesirFrame : JFrame() {

    companion object {

        lateinit var frame: AesirFrame
        var variables = mutableMapOf<String, Any>()

    }

    var children = mutableMapOf<String, JLabel>()
    var panel = JPanel()

    init {

        title = "Aesir"
        size = Dimension(250, 350)

        defaultCloseOperation = EXIT_ON_CLOSE
        contentPane = panel

        panel.layout = null

    }

    fun updateVariables() {
        var i = 0
        for (variable in variables) {
            if (children.containsKey(variable.key)) {
                children[variable.key]?.text = variable.value.toString()
            } else {
                val label = JLabel(variable.value.toString())
                label.isVisible = true
                label.setBounds(25, 25 * i++, 300, 50)
                panel.add(label)
                children[variable.key] = label
            }
        }
        if(!isVisible)
            isVisible = true
    }

}