
@Composable
fun AddTransactionScreen(viewModel: AddTransactionViewModel) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Add Transaction") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Tabs: Expense, Income, Transfer
            TabRow(selectedTabIndex = state.type.ordinal) {
                TransactionType.values().forEachIndexed { index, type ->
                    Tab(text = { Text(type.name) }, selected = state.type.ordinal == index) {
                        viewModel.selectType(type)
                    }
                }
            }

            OutlinedTextField(
                value = state.amount,
                onValueChange = { viewModel.updateAmount(it) },
                label = { Text("Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            AccountDropdown(accounts = state.accounts, onSelected = { viewModel.selectAccount(it) })
            CategoryDropdown(categories = state.categories, onSelected = { viewModel.selectCategory(it) })

            DateTimePicker(dateTime = state.dateTime, onDateChange = { viewModel.updateDateTime(it) })

            OutlinedTextField(
                value = state.notes,
                onValueChange = { viewModel.updateNotes(it) },
                label = { Text("Notes") },
                modifier = Modifier.fillMaxWidth()
            )

            // Optional Fields
            if (state.type != TransactionType.TRANSFER) {
                OutlinedTextField(
                    value = state.payee ?: "",
                    onValueChange = { viewModel.updatePayee(it) },
                    label = { Text("Payee") }
                )
            }

            MultiLabelInput(labels = state.labels, onLabelsChanged = { viewModel.updateLabels(it) })

            PaymentTypeDropdown(
                selected = state.paymentType,
                onSelected = { viewModel.updatePaymentType(it) }
            )

            PaymentStatusToggle(status = state.status) { viewModel.updateStatus(it) }

            Button(onClick = { viewModel.saveTransaction() }) {
                Text("Save")
            }
        }
    }
}
