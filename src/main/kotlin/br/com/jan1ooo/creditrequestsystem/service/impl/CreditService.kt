package br.com.jan1ooo.creditrequestsystem.service.impl

import br.com.jan1ooo.creditrequestsystem.entity.Credit
import br.com.jan1ooo.creditrequestsystem.exception.BusinessException
import br.com.jan1ooo.creditrequestsystem.repository.CreditRepository
import br.com.jan1ooo.creditrequestsystem.service.ICreditService
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.*

@Service
class CreditService(
        private val creditRepository: CreditRepository,
        private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> = this.creditRepository.findAllByCustomer(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        var credit: Credit = this.creditRepository.findByCreditCode(creditCode) ?: throw BusinessException("Creditcode  $creditCode not found")
        return if(credit.customer?.id == customerId) credit else throw IllegalArgumentException("Contact admin")
    }
}


