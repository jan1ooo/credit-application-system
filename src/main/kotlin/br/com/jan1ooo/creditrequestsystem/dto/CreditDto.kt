package br.com.jan1ooo.creditrequestsystem.dto

import br.com.jan1ooo.creditrequestsystem.entity.Credit
import br.com.jan1ooo.creditrequestsystem.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
        val creditValue: BigDecimal,
        val dayFirstOfInstallments: LocalDate,
        val numberOfInstallments: Int,
        val customerId: Long
) {

    fun toEntity(): Credit = Credit(
            creditValue = this.creditValue,
            dayFirstInstallement = this.dayFirstOfInstallments,
            numberOfInstallements = this.numberOfInstallments,
            customer = Customer(id = this.customerId)
    )

}
