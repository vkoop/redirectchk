package de.vkoop.redirectchk.data

data class RedirectCheckResponse(val request: RedirectCheckRequest, val redirectHops: List<Pair<String, Int>> = emptyList(), val success: Boolean = true)
