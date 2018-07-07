package de.vkoop.redirectchk.services.input

import de.vkoop.redirectchk.data.RedirectCheckRequest
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.stereotype.Component
import java.io.InputStream

@Component
class ExcelReader : RequestInputReader {
    override fun read(inStream: InputStream): List<RedirectCheckRequest> {

        val workbook = WorkbookFactory.create(inStream)
        val firstSheet = workbook.getSheetAt(0)

        return firstSheet.asIterable()
                .map { mapRequestRow(it) }
                .toList()
    }

    private fun mapRequestRow(row: Row): RedirectCheckRequest {
        val source = row.getCell(0)?.stringCellValue ?: ""
        val target = row.getCell(1)?.stringCellValue ?: ""
        val statusCode = row.getCell(2)?.numericCellValue?.toInt()  ?: 301
        return RedirectCheckRequest(source, target, statusCode)
    }
}