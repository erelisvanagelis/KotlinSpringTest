package tama.antanas.kotlinspringtest.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Bank(
    @JsonProperty("account_number")
    val accountNumber: String = "",

    @JsonProperty("trust")
    val trust: Double = 0.0,

    @JsonProperty("default_transaction_fee")
    val transactionFee: Int = 0
)
