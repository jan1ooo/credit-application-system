package br.com.jan1ooo.creditrequestsystem.entity

import br.com.jan1ooo.creditrequestsystem.enumerated.Status
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "credit")
data class Credit(
        @Column(nullable = false, unique = true) val creditCode: UUID = UUID.randomUUID(),
        @Column(nullable = false) val creditValue: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false) val dayFirstInstallElement: LocalDate,
        @Column(nullable = false) val numberOfInstallElement: Int = 0,
        @Enumerated val status: Status = Status.IN_PROGRESS,
        @ManyToOne() val customer: Customer? = null,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)
