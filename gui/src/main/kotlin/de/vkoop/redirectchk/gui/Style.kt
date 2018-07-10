package de.vkoop.redirectchk.gui

import tornadofx.*

class AppStyle : Stylesheet(){

    companion object {
        val successCell by cssclass("success-cell")
        val failureCell by cssclass("failure-cell")
    }

    init {
        successCell {
            backgroundColor += c(0,255,0,1.0)
        }

        failureCell {
            backgroundColor += c(255,0,0,1.0)
        }
    }
}