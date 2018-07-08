package de.vkoop.redirectchk.gui.model

import de.vkoop.redirectchk.data.RedirectCheckRequest
import de.vkoop.redirectchk.data.RedirectCheckResponse
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*

class RedirectCheckRowViewModel(val request: RedirectCheckRequest) : ViewModel() {
    val responseProperty = SimpleObjectProperty<RedirectCheckResponse>()
    var response by responseProperty

    val callUrl = bind { request.callUrl.toProperty() }
    val targetUrl = bind { request.targetUrl.toProperty() }
    val statusCode = bind { request.statusCode.toProperty() }

    val actualRedirectUrl = bind { response?.redirectHops?.lastOrNull()?.first.toProperty() }
    val firstCode = bind { response?.redirectHops?.firstOrNull()?.second.toProperty() }
}