package de.vkoop.redirectchk.gui.controller

import de.vkoop.redirectchk.gui.model.RedirectCheckRequestModel
import de.vkoop.redirectchk.gui.model.RedirectCheckRowViewModel
import de.vkoop.redirectchk.services.RedirectCheckStrategy
import de.vkoop.redirectchk.services.input.ExcelReader
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*
import java.io.FileInputStream

class RedirectCheckController : Controller() {

    val checkStrategy: RedirectCheckStrategy by di()
    val inputReader: ExcelReader by di()

    var checks: ObservableList<RedirectCheckRowViewModel> = FXCollections.observableArrayList()

    fun executeChecks() {
        checks.forEach {
            it.response = checkStrategy.check(it.request)
        }
    }

    fun loadChecks() {
        chooseFile("", emptyArray()).firstOrNull()?.apply {
            val fileInputStream = FileInputStream(this)
            inputReader.read(fileInputStream)
                    .map { RedirectCheckRowViewModel(it) }
                    .apply { checks.addAll(this) }
        }
    }

    fun clear() = checks.clear()

    fun addCheck(newCheck: RedirectCheckRequestModel) {
        newCheck.commit()

        val response = checkStrategy.check(newCheck.item)
        val row = RedirectCheckRowViewModel(newCheck.item)
        row.response = response

        checks.add(row)
    }
}