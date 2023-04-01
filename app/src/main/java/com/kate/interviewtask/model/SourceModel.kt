package com.kate.interviewtask.model

import com.squareup.moshi.Json

data class SourceModel(
    val description: String,
    val copyright: String,
    val title: String,
    val url: String,
    val date: String,
    val hdurl: String,
    @Json(name = "apod_site") val apodSite: String,
    @Json(name = "media_type") val mediaType: String,
)