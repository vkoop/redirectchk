package de.vkoop.redirectchk.services.reporter

import de.vkoop.redirectchk.data.RedirectCheckResponse

interface RedirectCheckReporter {

    fun report(results : List<RedirectCheckResponse>)

}