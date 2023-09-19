package br.com.jan1ooo.creditrequestsystem.service.impl

import br.com.jan1ooo.creditrequestsystem.entity.Customer
import br.com.jan1ooo.creditrequestsystem.repository.CustomerRepository
import br.com.jan1ooo.creditrequestsystem.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository): ICustomerService {
    override fun save(customer: Customer): Customer = customerRepository.save(customer)
    override fun findById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow{throw RuntimeException("Id $id not found")}
    override fun deleteById(id: Long) = this.customerRepository.deleteById(id)
}