# RxBilling2 Release Notes

## 0.2.1-experimental
Released 2020-06-03

* Updated to fully support Google Play Library 2.2.1
  * Added better support for BillingClient.onPurchasesUpdated()
  * Added RxBilling.checkPurchased(skuType: String) to trigger purchase update handler when the app loads
  * Added support for BillingFlowParams.setObfuscatedAccountId() and BillingFlowParams.setObfuscatedProfileId()
* Added toJson() to data classes
* Added direct constructor from Google objects for data classes
* Added String.toSha256() and String.toMD5()
* **Breaking changes**
  * Removed Purchased object and merged it with PurchaseResponse  

## 0.1.1-experimental
Released 2020-06-02

* Initial experimental release
* Code is minimally functional and likely has some serious issues still