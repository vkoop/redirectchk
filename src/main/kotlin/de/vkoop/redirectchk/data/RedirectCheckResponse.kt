package de.vkoop.redirectchk.data

data class RedirectCheckResponse(val request: RedirectCheckRequest, val actualUrl: String, val actualCode: Int)
