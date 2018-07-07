package de.vkoop.redirectchk.gui

import de.vkoop.redirectchk.gui.controller.RedirectCheckController
import de.vkoop.redirectchk.gui.model.RedirectCheckResponseModel
import tornadofx.*

class MainView : View("My View") {

    val controller: RedirectCheckController by inject()

    override val root = borderpane {
        top {
            button("execute") {
                action {
                    controller.executeChecks()
                }
            }
        }
        center {
            tableview(controller.checkResults){
                column("Url", RedirectCheckResponseModel::callUrl)
                column("Target", RedirectCheckResponseModel::targetUrl)
                column("Code", RedirectCheckResponseModel::expectedStatusCode)
                column("Actual Target", RedirectCheckResponseModel::actualTarget)
                column("First code", RedirectCheckResponseModel::firstCode)
                column("Success", RedirectCheckResponseModel::success)

                columnResizePolicy = SmartResize.POLICY
            }
        }
    }
}

