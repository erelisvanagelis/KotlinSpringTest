package tama.antanas.kotlinspringtest.datasource.mock

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import tama.antanas.kotlinspringtest.model.Bank

internal class MockBankDataSourceTest{
    private val mockDataSource = MockBankDataSource()
    @Test
    fun `should provide a collection of banks`(){
        val banks = mockDataSource.retrieveBanks()
        assertThat(banks).isNotEmpty
        assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun `should provide some mock data` (){
        val banks = mockDataSource.retrieveBanks()
        assertThat(banks).allMatch {it.accountNumber.isNotBlank()}
        assertThat(banks).anyMatch {it.trust != 0.0 }
        assertThat(banks).anyMatch {it.transactionFee != 0}
    }

    @Test
    fun `should provide default bank` (){
        val bank = mockDataSource.retrieveBank("this bank does not exist")
        assertThat(bank).isEqualTo(Bank())
    }

    @Test
    fun `should provide specified bank` () {
        //given
        val banks = mockDataSource.retrieveBanks()
        val copy = banks.first().copy()

        //when
        val bank = mockDataSource.retrieveBank(copy.accountNumber)

        //then
        assertThat(bank).isEqualTo(copy)
    }
}