package de.vkoop.redirectchk.services

import de.vkoop.redirectchk.data.RedirectCheckRequest
import de.vkoop.redirectchk.data.RedirectCheckResponse
import de.vkoop.redirectchk.util.follow
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import java.net.URI
import javax.inject.Inject

@Component
class RedirectCheckStrategyImpl : RedirectCheckStrategy {

    @Inject
    lateinit var client: WebClient


    override fun check(request: RedirectCheckRequest): RedirectCheckResponse {
        val hops = client.follow(URI.create(request.callUrl))
                .map { Pair(it.url, it.response.statusCode().value()) }
                .collectList()
                .block()

        val success = (hops.first().second == request.statusCode)
                && hops.last().first == request.targetUrl

        val response = RedirectCheckResponse(request, hops, success)

        return response
    }


}

