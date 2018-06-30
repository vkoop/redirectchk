package de.vkoop.redirectchk.services

import de.vkoop.redirectchk.data.ClientResponseWithUrl
import de.vkoop.redirectchk.data.RedirectCheckRequest
import de.vkoop.redirectchk.data.RedirectCheckResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux
import java.net.URI

@Component
class RedirectCheckImpl : RedirectCheck {

    override fun check(request: RedirectCheckRequest): RedirectCheckResponse {
        val client = WebClient.create();
        val redirectSteps = follow(URI.create(request.callUrl), client).collectList().block()

        val lastHop = redirectSteps.last()
        val firstHop = redirectSteps.first()

        val response = RedirectCheckResponse(request, lastHop.url, firstHop.response.statusCode().value())

        return response
    }

    fun follow(requestUri: URI, client: WebClient): Flux<ClientResponseWithUrl> {
        return client.get().uri(requestUri)
                .exchange()
                .toFlux()
                .flatMap { response ->
                    val redirectLocation = response.headers().header("Location")

                    val responseFlux = Flux.just(ClientResponseWithUrl(response, requestUri.toString()))
                    if (redirectLocation.isNotEmpty() && response.statusCode().is3xxRedirection) {
                        val newUrl = redirectLocation.get(0)
                        var newURI = URI.create(newUrl)

                        if (!newURI.isAbsolute) {
                            newURI = requestUri.resolve(newURI)
                        }

                        Flux.concat(responseFlux, follow(newURI, client))
                    } else {
                        responseFlux
                    }
                }
    }
}

