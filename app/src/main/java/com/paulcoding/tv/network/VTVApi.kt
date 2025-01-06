package com.paulcoding.tv.network

import com.google.gson.Gson
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.headers


class VTVApi {
    private fun getFormattedDate(): String {
        val date = java.util.Calendar.getInstance()
        val year = date.get(java.util.Calendar.YEAR)
        val month = (date.get(java.util.Calendar.MONTH) + 1).toString().padStart(2, '0')
        val day = date.get(java.util.Calendar.DAY_OF_MONTH).toString().padStart(2, '0')
        return "$year/$month/$day"
    }

    private fun parseEpg(text: String): String {
        val regex = Regex("data-id=\"(\\d+).*\\n.*\\n..*05:30")
        val matchResult = regex.find(text)
        return if (matchResult != null && matchResult.groupValues.size > 1) {
            matchResult.groupValues[1]
        } else {
            throw Exception("INVALID: $text")
        }
    }

    private suspend fun loadEpgDetail(epgId: String): String {
        return ktorClient.use { client ->
            val epgDetailString: String = client.get(
                "https://vtvgo.vn/ajax-get-epg-detail?epg_id=$epgId"
            ) {

                header(
                    HttpHeaders.UserAgent,
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/114.0"
                )
                header(HttpHeaders.Accept, "text/html, */*; q=0.01")
                header(HttpHeaders.AcceptLanguage, "en-US,en;q=0.5")
                header("X-Requested-With", "XMLHttpRequest")
                header("Alt-Used", "vtvgo.vn")
                header("Sec-Fetch-Dest", "empty")
                header("Sec-Fetch-Mode", "cors")
                header("Sec-Fetch-Site", "same-origin")
                header(HttpHeaders.Pragma, "no-cache")
                header(HttpHeaders.CacheControl, "no-cache")
                method = HttpMethod.Get
            }
                .body()
            return Gson().fromJson(epgDetailString, EpgDetail::class.java).data
        }
    }

    suspend fun getTVUrl(): Result<String> = runCatching {
        val formattedDate = getFormattedDate()
        val url = "https://vtvgo.vn/ajax-get-list-epg?selected_date_epg=$formattedDate&channel_id=1"

        ktorClient.use { client ->
            val epgListResponse: String = client.get(url) {
                header(
                    HttpHeaders.UserAgent,
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/114.0"
                )
                header(HttpHeaders.Accept, "text/html, */*; q=0.01")
                header(HttpHeaders.AcceptLanguage, "en-US,en;q=0.5")
                header("X-Requested-With", "XMLHttpRequest")
                header("Alt-Used", "vtvgo.vn")
                header("Sec-Fetch-Dest", "empty")
                header("Sec-Fetch-Mode", "cors")
                header("Sec-Fetch-Site", "same-origin")
                header(HttpHeaders.Pragma, "no-cache")
                header(HttpHeaders.CacheControl, "no-cache")
            }.body()
            val epgId = parseEpg(epgListResponse)
            return@runCatching loadEpgDetail(epgId)
        }
    }

    private fun getVtvHeaders(): Headers {
        return headers {
            append(
                HttpHeaders.UserAgent,
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/114.0"
            )
            append(HttpHeaders.Accept, "text/html, */*; q=0.01")
            append(HttpHeaders.AcceptLanguage, "en-US,en;q=0.5")
            append("X-Requested-With", "XMLHttpRequest")
            append("Alt-Used", "vtvgo.vn")
            append("Sec-Fetch-Dest", "empty")
            append("Sec-Fetch-Mode", "cors")
            append("Sec-Fetch-Site", "same-origin")
            append(HttpHeaders.Pragma, "no-cache")
            append(HttpHeaders.CacheControl, "no-cache")
        }
    }
}

data class EpgDetail(
    val data: String,
    val desc: String,
    val dfpTags: String,
    val dfpTime: String,
    val epgId: Int,
    val image: String,
    val state: Int,
    val title: String
)