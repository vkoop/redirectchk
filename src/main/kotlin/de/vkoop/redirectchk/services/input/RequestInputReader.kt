package de.vkoop.redirectchk.services.input

import de.vkoop.redirectchk.data.RedirectCheckRequest
import java.io.InputStream

interface RequestInputReader {
    fun read(inStream: InputStream): List<RedirectCheckRequest>
}

