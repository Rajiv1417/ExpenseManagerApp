
class AddTransactionUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    suspend operator fun invoke(transaction: TransactionEntity) {
        repository.insertTransaction(transaction)
    }
}
