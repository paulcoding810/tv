package com.paulcoding.tv.helper

import android.content.Context
import com.google.gson.Gson
import java.io.File
import java.io.FileOutputStream


fun Context.writeFile(data: String, fileName: String, fileDir: File = filesDir): File {
    val file = File(fileDir, fileName)
    FileOutputStream(file).use { fos ->
        fos.write(data.toByteArray())
    }
    return file
}

fun Context.readFile(fileName: String, fileDir: File = filesDir): String {
    val file = File(fileDir, fileName)

    file.bufferedReader().use { reader ->
        return reader.readText()
    }
}

inline fun <reified T> Context.readJsonFile(fileName: String): Result<T> {
    return runCatching {
        val content = readFile(fileName)
        return@runCatching Gson().fromJson(content, T::class.java)
    }
}