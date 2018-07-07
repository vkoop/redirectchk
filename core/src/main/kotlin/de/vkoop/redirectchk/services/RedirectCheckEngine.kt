package de.vkoop.redirectchk.services

import de.vkoop.redirectchk.data.RedirectCheckRequest

interface RedirectCheckEngine {

    fun check(checks : List<RedirectCheckRequest>)

}