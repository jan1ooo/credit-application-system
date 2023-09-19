package br.com.jan1ooo.creditrequestsystem.dto

import br.com.jan1ooo.creditrequestsystem.entity.Customer
import jakarta.validation.constraints.NotEmpty
import java.math.BigDecimal

data class CustomerUpdateDto(
        @field:NotEmpty val firstName: String,
        @field:NotEmpty val lastName: String,
        @field:NotEmpty val income: BigDecimal,
        @field:NotEmpty val zipCode: String,
        @field:NotEmpty val street: String
) {
    fun toEntity(customer: Customer): Customer{
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.street = this.street
        customer.address.zipCode = this.zipCode
        return customer
    }
}
