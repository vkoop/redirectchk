package de.vkoop.redirectchk.gui

import de.vkoop.redirectchk.gui.controller.RedirectCheckController
import de.vkoop.redirectchk.gui.model.RedirectCheckRowViewModel
import tornadofx.*

class MainView : View("My View") {

    val controller: RedirectCheckController by inject()

    override val root = borderpane {
        top {
            hbox {
                button("load") {
                    action {
                        controller.loadChecks()
                    }
                }
                button("execute") {
                    action {
                        controller.executeChecks()
                    }
                }
            }
        }
        center {
            vbox {
                tableview(controller.checks){
                    column("Url", RedirectCheckRowViewModel::callUrl)
                    column("Target", RedirectCheckRowViewModel::targetUrl)
                    column("Code", RedirectCheckRowViewModel::statusCode)
                    column("Actual Target", RedirectCheckRowViewModel::actualRedirectUrl)
                    column("First code", RedirectCheckRowViewModel::firstCode)

                    columnResizePolicy = SmartResize.POLICY
                }
            }
        }
    }
}

