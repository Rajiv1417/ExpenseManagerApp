
@Composable
fun ImportScreen(viewModel: ImportViewModel) {
    Column {
        Button(onClick = { viewModel.pickFile() }) {
            Text("Choose File")
        }

        if (viewModel.mappingRequired) {
            Column {
                Text("Map your columns:")
                ColumnMappingInput(
                    headers = viewModel.fileHeaders,
                    onMap = { col, field -> viewModel.mapColumn(col, field) }
                )
                Button(onClick = { viewModel.import() }) {
                    Text("Import")
                }
            }
        }
    }
}
