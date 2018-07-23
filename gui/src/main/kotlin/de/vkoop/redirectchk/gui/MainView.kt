package de.vkoop.redirectchk.gui

import de.vkoop.redirectchk.gui.controller.RedirectCheckController
import de.vkoop.redirectchk.gui.model.RedirectCheckRequestModel
import de.vkoop.redirectchk.gui.model.RedirectCheckRowViewModel
import javafx.scene.control.TableCell
import javafx.scene.control.TableRow
import javafx.util.Callback
import tornadofx.*
import java.net.URI
import java.net.URISyntaxException

class MainView : View("My View") {

    val controller: RedirectCheckController by inject()

    val newCheck: RedirectCheckRequestModel by inject()

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
                button("clear") {
                    action {
                        controller.clear()
                    }
                }

                paddingAll = 5
                spacing = 5.0
            }
        }
        center {
            vbox {
                tableview(controller.checks) {
                    column("Url", RedirectCheckRowViewModel::callUrl)
                    column("Target", RedirectCheckRowViewModel::targetUrl)
                    column("Code", RedirectCheckRowViewModel::statusCode)
                    column("Actual Target", RedirectCheckRowViewModel::actualRedirectUrl)
                    column("First code", RedirectCheckRowViewModel::firstCode)
                    column("Success", RedirectCheckRowViewModel::success).cellFormat {
                        if(it == true){
                            addClass(AppStyle.successCell)
                            removeClass(AppStyle.failureCell)
                        } else {
                            addClass(AppStyle.failureCell)
                            removeClass(AppStyle.successCell)
                        }
                    }

                    //columnResizePolicy = SmartResize.POLICY
                }

                form {
                    fieldset {
                        field("Source URL") {
                            textfield(newCheck.callUrl) {
                                required()
                                validator {
                                    urlValidation(it)
                                }
                            }
                        }
                        field("Target URL") {
                            textfield(newCheck.targetUrl) {
                                required()
                                validator {
                                    urlValidation(it)
                                }
                            }
                        }
                        field("Status Code") {
                            textfield(newCheck.statusCode) {
                                required()
                            }
                        }
                        button("Add check") {
                            enableWhen(newCheck.valid)
                            action {
                                controller.addCheck(newCheck)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun ValidationContext.urlValidation(value: String?): ValidationMessage? {
        if (value.isNullOrEmpty()) return null
        try {
            val uri = URI(value)
            if (!uri.isAbsolute) {
                return error("not absolute url")
            }
        } catch (e: URISyntaxException) {
            return error("not valid url")
        }
        return null
    }
}




