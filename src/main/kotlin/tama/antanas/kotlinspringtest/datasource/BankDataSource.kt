package tama.antanas.kotlinspringtest.datasource

import tama.antanas.kotlinspringtest.model.Bank

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
}