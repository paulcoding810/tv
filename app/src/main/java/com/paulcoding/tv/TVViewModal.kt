package com.paulcoding.tv

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class TVViewModal : ViewModel() {
    private var _stateFlow = MutableStateFlow(UiState())
    val stateFlow = _stateFlow

    data class UiState(
        val m3u8: String = "",
        val isLoading: Boolean = false,
        val videoUrl: String? = "https://vtvgo-vods.vtvdigital.vn/qb0zwJ4-FuPeaJtjhkheww/1736105298/vod/20250105/0501_vn_vui_khoe.mp4/index.m3u8"
    )

    fun getUrl() {

    }
}

class VTVApi {
    suspend fun getTVUrl(): String {
        return ""
    }
}