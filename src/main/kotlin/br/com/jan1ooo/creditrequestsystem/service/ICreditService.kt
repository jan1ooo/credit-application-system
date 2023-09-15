package br.com.jan1ooo.creditrequestsystem.service

import br.com.jan1ooo.creditrequestsystem.entity.Credit
import java.util.UUID

interface ICreditService {
    fun save (credit: Credit): Credit
    fun findAllByCustomerId(customerId: Long): List<Credit>
    fun findByCreditCode(customerId: Long, creditCode: UUID): Credit
}