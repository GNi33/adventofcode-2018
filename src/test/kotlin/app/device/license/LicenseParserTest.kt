package app.device.license


import app.util.IInputReader
import app.util.InputReader
import org.junit.Test
import org.junit.Assert.assertEquals

internal class LicenseParserTest {

    private val inputReader = InputReader(IInputReader.MODE.TEST)
    private val input = inputReader.getDataForDay(8)
    private val licenseParser = LicenseParser(input.first())

    @Test
    fun splitLicenseInput() {
        assertEquals(16, licenseParser.splitLicenseInput().size)
    }

    @Test
    fun getMetaDataSum() {
        assertEquals(138, licenseParser.getMetaDataSum())
    }

    @Test
    fun getValueOfRootNode() {
        assertEquals(66, licenseParser.getValueOfRootNode())
    }
}