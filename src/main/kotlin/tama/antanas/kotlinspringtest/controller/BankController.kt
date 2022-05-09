package tama.antanas.kotlinspringtest.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postBank(@RequestBody bank: Bank) = service.addBank(bank)

    @PatchMapping
    fun patchBank(@RequestBody bank: Bank) = service.patchBank(bank)

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBank(@PathVariable accountNumber: String) = service.deleteBank(accountNumber)

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound (e: NoSuchElementException) = ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument (e: IllegalArgumentException) = ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
}