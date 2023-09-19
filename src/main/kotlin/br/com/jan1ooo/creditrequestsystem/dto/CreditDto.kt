package br.com.jan1ooo.creditrequestsystem.dto

import br.com.jan1ooo.creditrequestsystem.entity.Credit
import br.com.jan1ooo.creditrequestsystem.entity.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
        @field:NotNull(message = "Invalid Input") val creditValue: BigDecimal,
        @field:Future val dayFirstOfInstallments: LocalDate,
        @field:Positive val numberOfInstallments: Int,
        @field:NotNull(message = "Invalid Input") val customerId: Long
) {

    fun toEntity(): Credit = Credit(
            creditValue = this.creditValue,
            dayFirstInstallement = this.dayFirstOfInstallments,
            numberOfInstallements = this.numberOfInstallments,
            customer = Customer(id = this.customerId)
    )

}
