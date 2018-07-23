package de.vkoop.redirectchk.gui

import javafx.scene.paint.Color
import tornadofx.*

class AppStyle : Stylesheet(){

    companion object {
        val successCell by cssclass("success")
        val failureCell by cssclass("failure")
    }

    init {
        successCell {
            backgroundColor += c("8BC34A")
        }

        failureCell {
            backgroundColor += c("D32F2F")
        }
    }
}