
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? = value?.let { Instant.ofEpochMilli(it) }

    @TypeConverter
    fun toTimestamp(instant: Instant?): Long? = instant?.toEpochMilli()

    @TypeConverter
    fun fromLabels(value: List<String>): String = value.joinToString(",")

    @TypeConverter
    fun toLabels(value: String): List<String> = value.split(",").filter { it.isNotBlank() }

    // Add for enums
    @TypeConverter
    fun transactionTypeToString(type: TransactionType) = type.name

    @TypeConverter
    fun stringToTransactionType(name: String) = TransactionType.valueOf(name)
}
