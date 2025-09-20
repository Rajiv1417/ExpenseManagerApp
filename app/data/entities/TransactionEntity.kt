
@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val type: TransactionType, // EXPENSE, INCOME, TRANSFER
    val amount: Double,
    val accountId: String,
    val categoryId: String?,
    val dateTime: Long, // timestamp in milliseconds
    val notes: String?,
    val payee: String?,
    val labels: List<String> = emptyList(),
    val paymentType: PaymentType,
    val status: PaymentStatus
)

enum class TransactionType { EXPENSE, INCOME, TRANSFER }
enum class PaymentType { CASH, CARD, UPI, BANK_TRANSFER, NET_BANKING, OTHER }
enum class PaymentStatus { CLEARED, PENDING }
