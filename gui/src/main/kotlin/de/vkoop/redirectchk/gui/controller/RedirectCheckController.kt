package de.vkoop.redirectchk.gui.controller

import de.vkoop.redirectchk.gui.model.RedirectCheckResponseModel
import de.vkoop.redirectchk.services.RedirectCheckStrategy
import de.vkoop.redirectchk.services.input.ExcelReader
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*
import java.io.FileInputStream

class RedirectCheckController : Controller() {

    val checkStrategy : RedirectCheckStrategy by di()
    val inputReader : ExcelReader by di()

    var checkResults: ObservableList<RedirectCheckResponseModel> = FXCollections.observableArrayList()

    fun executeChecks(){
        val fileInputStream = FileInputStream("testdata/test.xlsx")
        val toCheck = inputReader.read(fileInputStream)
        val responses = toCheck.map(checkStrategy::check)

        responses.map { val redirectCheckResponseModel = RedirectCheckResponseModel()

            redirectCheckResponseModel.item = it
            redirectCheckResponseModel
        }.forEach {
            checkResults.add(it)
        }
    }
}