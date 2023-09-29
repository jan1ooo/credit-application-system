package br.com.jan1ooo.creditrequestsystem.repository

import br.com.jan1ooo.creditrequestsystem.entity.Address
import br.com.jan1ooo.creditrequestsystem.entity.Credit
import br.com.jan1ooo.creditrequestsystem.entity.Customer
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditRepositoryTest {
    @Autowired lateinit var creditRepository: CreditRepository
    @Autowired lateinit var testEntityManager: TestEntityManager

    private lateinit var customer: Customer
    private lateinit var credit1: Credit
    private lateinit var credit2: Credit

    @BeforeEach fun setup(){
        customer = testEntityManager.persist(buildCustomer())
        credit1 = testEntityManager.persist(buildCredit(customer = customer))
        credit2 = testEntityManager.persist(buildCredit(customer = customer))

    }

    private fun buildCredit(
            creditValue: BigDecimal = BigDecimal.valueOf(500.0),
            dayFirstInstallment: LocalDate = LocalDate.of(2023, Month.APRIL, 21),
            numberOfInstallment: Int = 5,
            customer: Customer
    ): Credit = Credit (
            creditValue = creditValue,
            dayFirstInstallement = dayFirstInstallment,
            numberOfInstallements =  numberOfInstallment,
            customer = customer
    )

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