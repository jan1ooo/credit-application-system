package br.com.jan1ooo.creditrequestsystem.repository

import br.com.jan1ooo.creditrequestsystem.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditInterface: JpaRepository<Credit, Long> {
}