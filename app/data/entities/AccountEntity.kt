
@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    var balance: Double,
    val type: AccountType // CASH, BANK, CREDIT_CARD, UPI
)

enum class AccountType { CASH, BANK, CREDIT_CARD, UPI, SAVINGS, LOAN }
