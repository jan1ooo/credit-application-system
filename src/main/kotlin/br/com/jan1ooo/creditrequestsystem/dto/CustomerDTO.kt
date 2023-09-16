package br.com.jan1ooo.creditrequestsystem.dto

import br.com.jan1ooo.creditrequestsystem.entity.Address
import br.com.jan1ooo.creditrequestsystem.entity.Customer
import java.math.BigDecimal

data class CustomerDTO(
        val firstName: String,
        val lastName: String,
        val cpf: String,
        val income: BigDecimal,
        val email: String,
        val password: String,
        val zipCode: String,
        val street: String
) {

    fun toEntity(): Customer = Customer(
            firstName = this.firstName,
            lastName = this.lastName,
            cpf = this.cpf,
            email = this.email,
            password = this.password,
            income =  this.income,
            address = Address(zipCode = this.zipCode, street = this.street)
    )

}
