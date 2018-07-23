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

    val success = bind { response?.success.toProperty()}

    init {
        rebindOnChange(responseProperty)
    }
}

class RedirectCheckRequestModel : ItemViewModel<RedirectCheckRequest>() {
    val callUrl = bind(RedirectCheckRequest::callUrl)
    val targetUrl = bind(RedirectCheckRequest::targetUrl)
    val statusCode = bind { item?.statusCode?.toProperty() }

    //TODO
    init {
        item = RedirectCheckRequest()
    }
}