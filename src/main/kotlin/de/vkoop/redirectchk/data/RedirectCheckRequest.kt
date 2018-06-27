package de.vkoop.redirectchk.data

data class RedirectCheckRequest(val callUrl: String, val targetUrl: String, val statusCode: Int = 301)