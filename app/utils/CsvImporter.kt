
class CsvImporter @Inject constructor(
    private val context: Context,
    private val repository: ExpenseRepository
) {
    suspend fun import(uri: Uri, columnMap: Map<String, String>) {
        context.contentResolver.openInputStream(uri)?.use { input ->
            CSVReader(InputStreamReader(input)).use { reader ->
                val lines = reader.readAll()
                val headers = lines.first()
                val dataRows = lines.drop(1)

                dataRows.forEach { row ->
                    val transaction = mapRowToTransaction(headers, row, columnMap)
                    repository.insertTransaction(transaction)
                }
            }
        }
    }
}
