package tama.antanas.kotlinspringtest.datasource.network

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import tama.antanas.kotlinspringtest.datasource.BankDataSource
import tama.antanas.kotlinspringtest.datasource.network.dto.BankList
import tama.antanas.kotlinspringtest.model.Bank
import java.io.IOException

@Repository
class NetworkBankDataSource (
    @Autowired private val restTemplate: RestTemplate
        ): BankDataSource {
    override fun retrieveBanks(): Collection<Bank> {
        val response = restTemplate.getForEntity<BankList>("grybas ne url :( https://www.youtube.com/watch?v=Ykq9LAzNxTE")

        return response.body?.result ?: throw IOException ("Could not fetch data from the web")
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