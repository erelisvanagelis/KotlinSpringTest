package tama.antanas.kotlinspringtest.model

data class Bank(
    val accountNumber: String = "",
    val trust: Double = 0.0,
    val transactionFee: Int = 0
)
