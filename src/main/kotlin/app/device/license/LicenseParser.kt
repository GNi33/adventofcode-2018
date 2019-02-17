package app.device.license

class LicenseParser(private val license: String) {

    fun getMetaDataSum() {

        val licenseValues = splitLicenseInput()



    }

    fun splitLicenseInput(): List<Int> {
        return license.split(" ").map { it.toInt() }
    }

}