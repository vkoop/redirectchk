package de.vkoop.redirectchk.services.input

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.FileInputStream

@RunWith(JUnit4::class)
class ExcelReaderTest {
    val reader = ExcelReader()

    @Test
    fun test(){
        val fileInputStream =  FileInputStream("src/test/resources/test1.xlsx")
        val requests = reader.read(fileInputStream)

        assertEquals(2, requests.size)

        val request = requests.get(0)
        assertEquals(301, request.statusCode)
        assertEquals("https://domain.local", request.callUrl)
        assertEquals("https://domain.local/redirect", request.targetUrl)
    }
}