package de.vkoop.redirectchk.services

import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import de.vkoop.redirectchk.config.ApplicationConfiguration
import de.vkoop.redirectchk.data.RedirectCheckRequest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import javax.inject.Inject
import kotlin.test.assertTrue

@RunWith(SpringRunner::class)
@SpringBootTest( classes = [ApplicationConfiguration::class])
class RedirectCheckStrategyImplTest {

    private val port = 8089

    @JvmField
    @Rule
    final val wireMockRule = WireMockRule(port) // No-args constructor defaults to port 8080

    @Inject
    lateinit var checkStrategy: RedirectCheckStrategy

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
        assertTrue(response.redirectHops.last().first == request.targetUrl)
    }
}