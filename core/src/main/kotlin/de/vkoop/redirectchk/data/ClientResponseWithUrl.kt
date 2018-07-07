package de.vkoop.redirectchk.data

import org.springframework.web.reactive.function.client.ClientResponse

data class ClientResponseWithUrl(val response: ClientResponse, val url: String)