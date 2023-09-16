package br.com.jan1ooo.creditrequestsystem.controller

import br.com.jan1ooo.creditrequestsystem.dto.CustomerDTO
import br.com.jan1ooo.creditrequestsystem.dto.CustomerView
import br.com.jan1ooo.creditrequestsystem.entity.Customer
import br.com.jan1ooo.creditrequestsystem.service.impl.CustomerService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerResource(
    private val customerService: CustomerService
) {


    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDTO): String{
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return "Customer ${savedCustomer.email} saved!"
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): CustomerView {
        val customer: Customer = this.customerService.findById(id);
        return CustomerView(customer)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = this.customerService.delete(id)


}