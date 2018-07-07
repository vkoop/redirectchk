package de.vkoop.redirectchk.gui.model

import de.vkoop.redirectchk.data.RedirectCheckResponse
import tornadofx.*

class RedirectCheckResponseModel : ItemViewModel<RedirectCheckResponse>() {
    val callUrl = bind { item?.request?.callUrl.toProperty()}
    val targetUrl = bind {item?.request?.targetUrl.toProperty()}
    val expectedStatusCode = bind { item?.request?.statusCode.toProperty()}
    val actualTarget = bind {item?.hops?.last()?.first.toProperty()}
    val firstCode = bind {item?.hops?.first()?.second.toProperty()}
    val success = bind(RedirectCheckResponse::success)
}