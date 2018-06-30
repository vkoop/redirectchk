package de.vkoop.redirectchk.services

import de.vkoop.redirectchk.data.RedirectCheckRequest
import de.vkoop.redirectchk.data.RedirectCheckResponse

interface RedirectCheckStrategy {

    fun check(request: RedirectCheckRequest) : RedirectCheckResponse

}