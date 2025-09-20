
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideTransactionDao(db: AppDatabase) = db.transactionDao()

    @Provides
    @Singleton
    fun provideAccountDao(db: AppDatabase) = db.accountDao()

    @Provides
    @Singleton
    fun provideCategoryDao(db: AppDatabase) = db.categoryDao()

    @Provides
    @Singleton
    fun provideExpenseRepository(
        transactionDao: TransactionDao,
        accountDao: AccountDao,
        categoryDao: CategoryDao
    ) = ExpenseRepository(transactionDao, accountDao, categoryDao)
}
