package de.vkoop.redirectchk.services

import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import de.vkoop.redirectchk.data.RedirectCheckRequest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertTrue


@RunWith(JUnit4::class)
class RedirectCheckStrategyImplTest {

    private val port = 8089

    @JvmField
    @Rule
    val wireMockRule = WireMockRule(port) // No-args constructor defaults to port 8080


    val checkStrategy: RedirectCheckStrategy = RedirectCheckStrategyImpl();

    @Test
    fun tests() {
        val sourceUrl = "/in-url"
        val redirectUrl = "/new-url"
        stubFor(get(sourceUrl)
                .willReturn(
                        permanentRedirect(redirectUrl)
                ))

        stubFor(get(redirectUrl)
                .willReturn(
                        ok()
                )
        )

        val request = RedirectCheckRequest("http://localhost:$port$sourceUrl", "http://localhost:$port$redirectUrl")

        val response = checkStrategy.check(request)
        assertTrue(response.hops.last().first == request.targetUrl)
    }
}