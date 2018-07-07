package de.vkoop.redirectchk.util

import de.vkoop.redirectchk.data.ClientResponseWithUrl
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux
import java.net.URI


fun WebClient.follow(requestUriString: URI): Flux<ClientResponseWithUrl> {
    return get()
            .uri(requestUriString)
            .exchange()
            .toFlux()
            .flatMap { response ->
                val redirectLocation = response.headers().header("Location")

                val responseFlux = Flux.just(ClientResponseWithUrl(response, requestUriString.toString()))
                if (redirectLocation.isNotEmpty() && response.statusCode().is3xxRedirection) {
                    val newUrl = redirectLocation.get(0)
                    var newURI = URI.create(newUrl)

                    if (!newURI.isAbsolute) {
                        newURI = requestUriString.resolve(newURI)
                    }

                    Flux.concat(responseFlux, follow(newURI))
                } else {
                    responseFlux
                }
            }
}