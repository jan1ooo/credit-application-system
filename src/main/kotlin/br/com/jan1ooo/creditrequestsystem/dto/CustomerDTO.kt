package br.com.jan1ooo.creditrequestsystem.dto

import br.com.jan1ooo.creditrequestsystem.entity.Address
import br.com.jan1ooo.creditrequestsystem.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDTO(
        @field:NotEmpty val firstName: String,
        @field:NotEmpty val lastName: String,
        @field:CPF(message = "This Invalid CPF") val cpf: String,
        @field:NotNull val income: BigDecimal,
        @field:Email(message = "Invalid Email")
        @field:NotEmpty val email: String,
        @field:NotEmpty val password: String,
        @field:NotEmpty val zipCode: String,
        @field:NotEmpty val street: String
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
