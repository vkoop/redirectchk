package de.vkoop.redirectchk.services

import de.vkoop.redirectchk.data.RedirectCheckRequest
import de.vkoop.redirectchk.services.reporter.RedirectCheckReporter
import org.springframework.stereotype.Component
import javax.inject.Inject

@Component
class RedirectCheckEngineImpl : RedirectCheckEngine {

    @Inject
    lateinit var  reporterRedirects: List<RedirectCheckReporter>;

    @Inject
    lateinit var checker: RedirectCheck

    override fun check(checks: List<RedirectCheckRequest>) {
        val results = checks.map { checker.check(it) }
        reporterRedirects.forEach { it.report(results) }
    }
}