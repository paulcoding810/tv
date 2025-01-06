package com.paulcoding.tv

import androidx.lifecycle.ViewModel
import com.paulcoding.tv.network.VTVApi
import kotlinx.coroutines.flow.MutableStateFlow

class TVViewModal : ViewModel() {
    private val vtvApi = VTVApi()

    private var _stateFlow = MutableStateFlow(UiState())
    val stateFlow = _stateFlow

    data class UiState(
        val isLoading: Boolean = false,
        val videoUrl: String? = null,
        val error: Throwable? = null,
    )

    private fun setError(th: Throwable) {
        _stateFlow.value = _stateFlow.value.copy(error = th)
    }

    suspend fun getUrl() {
        _stateFlow.value = _stateFlow.value.copy(isLoading = true)

        vtvApi.getTVUrl()
            .onSuccess {
                _stateFlow.value = _stateFlow.value.copy(videoUrl = it)
            }
            .onFailure {
                setError(it)
            }
        _stateFlow.value = _stateFlow.value.copy(isLoading = false)
    }
}
