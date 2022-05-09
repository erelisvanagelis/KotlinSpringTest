package tama.antanas.kotlinspringtest.datasource.mock

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository
import tama.antanas.kotlinspringtest.datasource.BankDataSource
import tama.antanas.kotlinspringtest.model.Bank

@Repository
@Primary
class MockBankDataSource : BankDataSource {
    val banks = mutableListOf<Bank>(
        Bank("000", 42.0, 9),
        Bank("123", 0.0, 9),
        Bank("456", 42.0, 0),
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(id: String): Bank = banks.firstOrNull() { it.accountNumber == id }
        ?: throw NoSuchElementException("No bank with such an accountNumber: $id exists")

    override fun createBank(bank: Bank): Bank {
        if (banks.any { it.accountNumber == bank.accountNumber }) {
            throw IllegalArgumentException("Bank with such an accountNumber: ${bank.accountNumber}  already exists")
        }

        banks.add(bank)

        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber }
            ?: throw NoSuchElementException("No bank with such an accountNumber: ${bank.accountNumber} exists")

        banks.remove(currentBank)
        banks.add(bank)
        return bank
    }

    override fun removeBank(accountNumber: String): Unit {
        val currentBank = banks.firstOrNull { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("No bank with such an accountNumber: ${accountNumber} exists")

        banks.remove(currentBank)
    }
}