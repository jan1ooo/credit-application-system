package br.com.jan1ooo.creditrequestsystem.repository

import br.com.jan1ooo.creditrequestsystem.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Long> {
}