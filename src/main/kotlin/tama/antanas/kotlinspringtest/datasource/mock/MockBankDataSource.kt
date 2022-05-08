package tama.antanas.kotlinspringtest.datasource.mock

import org.springframework.stereotype.Repository
import tama.antanas.kotlinspringtest.datasource.BankDataSource
import tama.antanas.kotlinspringtest.model.Bank

@Repository
class MockBankDataSource : BankDataSource {
    val banks = listOf<Bank>(
        Bank("000", 42.0, 9),
        Bank("123", 0.0, 9),
        Bank("456", 42.0, 0),
    )
    override fun retrieveBanks(): Collection<Bank> = banks
}