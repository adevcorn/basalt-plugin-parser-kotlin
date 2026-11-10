// Fixture excerpt adapted from add-helper's
// android/app/src/main/java/com/adevcorn/addhelper/AddHelperViewModel.kt.
package com.adevcorn.addhelper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UiState(
    val loading: Boolean = false,
    val items: List<String> = emptyList(),
    val error: String? = null,
)

class AddHelperViewModel(
    private val repository: AddHelperRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    fun refresh() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val result = repository.loadItems()
            _state.value = result.fold(
                onSuccess = { UiState(items = it) },
                onFailure = { UiState(error = it.message) },
            )
        }
    }

    @Composable
    fun StatusBadge(text: String) {
        Text(text = text)
    }
}

interface ItemRepository {
    suspend fun loadItems(): Result<List<String>>
}

object Defaults {
    const val PAGE_SIZE = 25
}
