package com.example.mvvm.extension

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.DateTimeFormatter(): String {

    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
    val output: String = formatter.format(parser.parse(this))
    return output
}

fun String.ToDateFormatter(): LocalDate {

    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    return LocalDate.parse(this, dateTimeFormatter)
}



