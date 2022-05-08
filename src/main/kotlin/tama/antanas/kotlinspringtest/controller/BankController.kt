package tama.antanas.kotlinspringtest.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tama.antanas.kotlinspringtest.model.Bank
import tama.antanas.kotlinspringtest.service.BankService

@RestController
@RequestMapping("api/banks")
class BankController(private val service: BankService) {
    @GetMapping
    fun getBanks(): Collection<Bank> = service.getBanks()

    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String) = service.getBank(accountNumber)

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound (e: NoSuchElementException) = ResponseEntity(e.message, HttpStatus.NOT_FOUND)

}