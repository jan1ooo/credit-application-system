package br.com.jan1ooo.creditrequestsystem.controller

import br.com.jan1ooo.creditrequestsystem.dto.CreditDto
import br.com.jan1ooo.creditrequestsystem.dto.CreditView
import br.com.jan1ooo.creditrequestsystem.dto.CreditViewList
import br.com.jan1ooo.creditrequestsystem.entity.Credit
import br.com.jan1ooo.creditrequestsystem.service.impl.CreditService
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/credits")
class CreditResource(
        private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String>{
        val credit: Credit =this.creditService.save(creditDto.toEntity())
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>>{
        val creditViewList = this.creditService.findAllByCustomer(customerId).stream().map { credit: Credit -> CreditViewList(credit)}.toList()
        return ResponseEntity.ok().body(creditViewList)
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long,
                         @PathVariable creditCode: UUID): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.ok().body(CreditView(credit))
    }

}