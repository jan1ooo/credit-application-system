package br.com.jan1ooo.creditrequestsystem.service

import br.com.jan1ooo.creditrequestsystem.entity.Address
import br.com.jan1ooo.creditrequestsystem.entity.Customer
import br.com.jan1ooo.creditrequestsystem.repository.CustomerRepository
import br.com.jan1ooo.creditrequestsystem.service.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    @MockK lateinit var  customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: CustomerService

    @Test
    fun `should create customer`(){
        // given
        val fakeCustomer: Customer = buildCustomer()
        every{customerRepository.save(any())} returns fakeCustomer
        // when
        val actual: Customer = customerService.save(fakeCustomer)
        // then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify (exactly = 1){ customerRepository.save(fakeCustomer)  }
    }

    private fun buildCustomer(
            firstName: String = "Janio",
            lastName: String = "Silva",
            cpf: String = "88573884096",
            email: String = "janinho@gmail.com",
            password: String = "12345",
            zipCode: String = "02900000",
            street: String = "Miguel Silveira",
            income: BigDecimal = BigDecimal.valueOf(1000.0),
            id: Long = 1L
    ) = Customer(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            password = password,
            address = Address(
                    zipCode = zipCode,
                    street = street
            ),
            income = income,
            id = id
    )
}