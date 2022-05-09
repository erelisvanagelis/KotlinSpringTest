package tama.antanas.kotlinspringtest.datasource.network

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository
import tama.antanas.kotlinspringtest.datasource.BankDataSource
import tama.antanas.kotlinspringtest.model.Bank

@Repository
@Primary
class NetworkBankDataSource: BankDataSource {
    override fun retrieveBanks(): Collection<Bank> {
        TODO("Not yet implemented")
    }

    override fun retrieveBank(id: String): Bank {
        TODO("Not yet implemented")
    }

    override fun createBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun updateBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun removeBank(accountNumber: String) {
        TODO("Not yet implemented")
    }
}