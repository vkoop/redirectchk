package de.vkoop.redirectchk.services

import de.vkoop.redirectchk.data.RedirectCheckRequest
import de.vkoop.redirectchk.data.RedirectCheckResponse
import de.vkoop.redirectchk.util.follow
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import java.net.URI

@Component
class RedirectCheckImpl : RedirectCheck {

    override fun check(request: RedirectCheckRequest): RedirectCheckResponse {
        val client = WebClient.create();
        val redirectSteps = client.follow(URI.create(request.callUrl)).collectList().block()

        val lastHop = redirectSteps.last()
        val firstHop = redirectSteps.first()

        val response = RedirectCheckResponse(request, lastHop.url, firstHop.response.statusCode().value())

        return response
    }

}

