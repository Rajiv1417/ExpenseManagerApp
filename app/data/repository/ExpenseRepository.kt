
class ExpenseRepository @Inject constructor(
    private val transactionDao: TransactionDao,
    private val accountDao: AccountDao,
    private val categoryDao: CategoryDao
) {
    suspend fun insertTransaction(transaction: TransactionEntity) {
        transactionDao.insert(transaction)
        updateAccountBalance(transaction.accountId, transaction.amount, transaction.type)
    }

    private suspend fun updateAccountBalance(accountId: String, amount: Double, type: TransactionType) {
        val account = accountDao.getAccountById(accountId) ?: return
        val newBalance = when (type) {
            TransactionType.EXPENSE -> account.balance - amount
            TransactionType.INCOME -> account.balance + amount
            TransactionType.TRANSFER -> account.balance - amount // handled separately
        }
        accountDao.update(account.copy(balance = newBalance))
    }

    fun getTransactionsStream(): Flow<List<TransactionEntity>> = transactionDao.getAll()
}
