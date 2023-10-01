package br.com.jan1ooo.creditrequestsystem.controller

import br.com.jan1ooo.creditrequestsystem.dto.CustomerDTO
import br.com.jan1ooo.creditrequestsystem.repository.CustomerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class CustomerResourceTest {

    @Autowired private lateinit var customerRepository: CustomerRepository
    @Autowired private lateinit var mockMvc: MockMvc
    @Autowired private lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/customers"
    }

    @BeforeEach fun setup() = customerRepository.deleteAll()
    @AfterEach fun tearDown() = customerRepository.deleteAll()

    @Test
    fun `should create a customer and return 201 status`(){
        //given
        val customerDTO: CustomerDTO = builderCustomerDto()
        val valueAsString = objectMapper.writeValueAsString(customerDTO)
        //when
        mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Jânio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Silva"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("92169796401"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("janio@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value("02940400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Castro silva"))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not save a customer with same CPF and return 409 status`(){
        //given
        customerRepository.save(builderCustomerDto().toEntity())
        val customerDTO: CustomerDTO = builderCustomerDto()
        val valueAsString = objectMapper.writeValueAsString(customerDTO)
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString))
                .andExpect(MockMvcResultMatchers.status().isConflict)
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not save a customer with firstName empty and return 400 status`(){
        //given
        val customerDTO: CustomerDTO = builderCustomerDto(firstName = "")
        val valueAsString = objectMapper.writeValueAsString(customerDTO)
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andDo(MockMvcResultHandlers.print())

    }

    private fun builderCustomerDto(
            firstName: String = "Jânio",
            lastName: String = "Silva",
            cpf: String = "92169796401",
            email: String = "janio@gmail.com",
            income: BigDecimal = BigDecimal.valueOf(3000.0),
            password: String = "janio123",
            zipCode: String = "02940400",
            street: String = "Castro silva"
    ) = CustomerDTO(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            income = income,
            password = password,
            zipCode = zipCode,
            street = street
    )

}