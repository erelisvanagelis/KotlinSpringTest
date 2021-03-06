package tama.antanas.kotlinspringtest.datasource

import tama.antanas.kotlinspringtest.model.Bank

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
    fun retrieveBank(id: String) : Bank
    fun createBank(bank: Bank): Bank
    fun updateBank(bank: Bank): Bank
    fun removeBank(accountNumber: String) : Unit
}