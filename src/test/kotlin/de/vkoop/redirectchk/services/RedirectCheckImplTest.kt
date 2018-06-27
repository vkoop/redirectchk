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
class RedirectCheckImplTest {

    private val port = 8089

    @JvmField
    @Rule
    val wireMockRule = WireMockRule(port) // No-args constructor defaults to port 8080


    val check: RedirectCheck = RedirectCheckImpl();

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

        val response = check.check(request)
        assertTrue(response.actualUrl == request.targetUrl)
    }
}