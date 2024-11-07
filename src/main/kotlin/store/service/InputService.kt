package store.service

import store.model.Product
import store.utils.Validator
import store.view.InputView

class InputService {

    private fun separatePurchaseInput(): List<String> {
        val input = InputView.getPurchaseInfo()
        return Validator.validatePurchaseInfo(input)
    }

}