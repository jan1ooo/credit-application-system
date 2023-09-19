package br.com.jan1ooo.creditrequestsystem.controller

import br.com.jan1ooo.creditrequestsystem.dto.CustomerDTO
import br.com.jan1ooo.creditrequestsystem.dto.CustomerUpdateDto
import br.com.jan1ooo.creditrequestsystem.dto.CustomerView
import br.com.jan1ooo.creditrequestsystem.entity.Customer
import br.com.jan1ooo.creditrequestsystem.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerResource(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDTO): ResponseEntity<String>{
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Customer ${savedCustomer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id);
        return ResponseEntity.ok().body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = this.customerService.deleteById(id)

    @PatchMapping
    fun updateMapping(@RequestParam(value = "customerId") id: Long,
                      @RequestBody @Valid customerUpdateDto: CustomerUpdateDto): CustomerView{
        val customer: Customer = this.customerService.findById(id)
        val customerToUpdate: Customer = customerUpdateDto.toEntity(customer)
        val customerUpdated: Customer = this.customerService.save(customerToUpdate)
        return CustomerView(customerUpdated)
    }


}