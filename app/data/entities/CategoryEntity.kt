
@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val type: CategoryType // INCOME, EXPENSE
)

enum class CategoryType { INCOME, EXPENSE }
