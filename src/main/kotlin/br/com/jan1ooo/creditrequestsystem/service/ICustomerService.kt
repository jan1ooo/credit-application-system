package br.com.jan1ooo.creditrequestsystem.service

import br.com.jan1ooo.creditrequestsystem.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun deleteById(id: Long)
}