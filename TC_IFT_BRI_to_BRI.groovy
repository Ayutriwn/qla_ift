import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

def waitTime = 30
def retryCount = 3

try {
    WebUI.openBrowser('')
    WebUI.setViewPortSize(1366, 768)
    WebUI.navigateToUrl('https://addons.cms.dev.bri.co.id/v2')

    def loggedIn = false
    for (int i = 0; i < retryCount && !loggedIn; i++) {
        try {
            WebUI.waitForElementVisible(findTestObject('Login/input_branchCode'), waitTime)
            WebUI.setText(findTestObject('Login/input_branchCode'), 'brownies')
            WebUI.setText(findTestObject('Login/input_username'), 'maker')
            WebUI.setEncryptedText(findTestObject('Login/input_password'), 'PoovGJtn/r6+9eFj8Tg4fQ==', FailureHandling.STOP_ON_FAILURE)
            WebUI.click(findTestObject('Login/button_SignIn'))
            WebUI.waitForElementPresent(findTestObject('Dashboard/menu_Transfer'), waitTime)
            loggedIn = true
        } catch (Exception e) {
            if (i == retryCount - 1) throw e
            WebUI.refresh()
        }
    }

    WebUI.click(findTestObject('Dashboard/menu_Transfer'))
    WebUI.click(findTestObject('Dashboard/submenu_FundTransfer'))
    WebUI.click(findTestObject('FundTransfer/button_CreateNew'))
    WebUI.click(findTestObject('FundTransfer/option_BRItoBRI'))
    WebUI.click(findTestObject('FundTransfer/dropdown_DebitAccount'))
    WebUI.click(findTestObject('FundTransfer/option_Account_Mery'))
    WebUI.setText(findTestObject('FundTransfer/input_ReceiverAccount'), '020601008855308')
    WebUI.setText(findTestObject('FundTransfer/input_Amount'), '1500000')
    WebUI.click(findTestObject('FundTransfer/button_Add'))
    WebUI.click(findTestObject('FundTransfer/button_SendForApproval'))
    WebUI.click(findTestObject('FundTransfer/button_Confirm'))
    WebUI.click(findTestObject('FundTransfer/button_Close'))
} catch (Exception e) {
    WebUI.takeScreenshot()
    throw new Exception("Test failed at step: " + e.getMessage())
} finally {
    WebUI.closeBrowser()
}