package app.device.license

import app.device.model.LicenseNode

class LicenseParser(private val license: String) {

    fun getMetaDataSum(): Int {

        val licenseValues = splitLicenseInput()
        val licenseTree = parseLicense(licenseValues)

        return licenseTree.metaDataSum
    }

    fun getValueOfRootNode(): Int {

        val licenseValues = splitLicenseInput()
        val licenseTree = parseLicense(licenseValues)

        return licenseTree.valueOfNode
    }

    fun parseLicense(licenseValues: List<Int>): LicenseNode {
        return parseNode(licenseValues.iterator())
    }

    private fun parseNode(license: Iterator<Int>): LicenseNode {

        val childNodeCount = license.next()
        val metaDataCount = license.next()

        val childNodes = (0 until childNodeCount).map {
            parseNode(license)
        }

        val metaData = (0 until metaDataCount).map {
            license.next()
        }

        return LicenseNode(metaData, childNodes)
    }

    fun splitLicenseInput(): List<Int> {
        return license.split(" ").map { it.toInt() }
    }
}