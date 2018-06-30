package de.vkoop.redirectchk.services.reporter

import de.vkoop.redirectchk.data.RedirectCheckResponse
import org.springframework.stereotype.Component

@Component
class RedirectConsoleReporter : RedirectCheckReporter {
    override fun report(results: List<RedirectCheckResponse>) {
        results.forEach { println(it) }
    }
}