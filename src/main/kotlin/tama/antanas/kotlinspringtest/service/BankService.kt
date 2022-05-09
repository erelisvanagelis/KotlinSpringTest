package tama.antanas.kotlinspringtest.service

import org.springframework.stereotype.Service
import tama.antanas.kotlinspringtest.datasource.BankDataSource
import tama.antanas.kotlinspringtest.model.Bank

@Service
class BankService (private val dataSource: BankDataSource) {
    fun getBanks() : Collection<Bank> = dataSource.retrieveBanks()
    fun getBank(id: String) : Bank = dataSource.retrieveBank(id)
    fun addBank(bank: Bank): Bank = dataSource.createBank(bank)
}