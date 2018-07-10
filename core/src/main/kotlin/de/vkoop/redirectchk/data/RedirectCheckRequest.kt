package de.vkoop.redirectchk.data

data class RedirectCheckRequest(var callUrl: String? = null, var targetUrl: String? = null, var statusCode: Int = 301)
