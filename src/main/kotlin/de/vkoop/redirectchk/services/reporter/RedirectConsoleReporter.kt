package de.vkoop.redirectchk.services.reporter

import de.vkoop.redirectchk.data.RedirectCheckResponse
import org.springframework.stereotype.Component

@Component
class RedirectConsoleReporter : RedirectCheckReporter {
    override fun report(results: List<RedirectCheckResponse>) {

        val groupedBySuccess = results.groupBy { it.success }

        printSuccess("Success:")
        groupedBySuccess.get(true).orEmpty()
                .forEach { printSuccess( it.toString()) }

        println()
        printError("Error:")
        groupedBySuccess.get(false).orEmpty()
                .forEach { printError(it.toString()) }
    }
}

fun printSuccess(txt : String) {
    println("\u001b[32m" + txt)
}

fun printError(txt : String) {
    println("\u001b[31m" + txt)
}