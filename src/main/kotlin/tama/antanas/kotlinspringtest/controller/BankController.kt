package tama.antanas.kotlinspringtest.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tama.antanas.kotlinspringtest.model.Bank
import tama.antanas.kotlinspringtest.service.BankService

@RestController
@RequestMapping("api/banks")
class BankController(private val service: BankService) {
    @GetMapping
    fun getBanks(): Collection<Bank> = service.getBanks()

}