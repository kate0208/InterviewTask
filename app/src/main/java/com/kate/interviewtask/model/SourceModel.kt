package com.kate.interviewtask.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "source")
data class SourceModel(
    val description: String,
    val copyright: String,
    val title: String,
    @PrimaryKey
    val url: String,
    val date: String,
    val hdurl: String,
    @Json(name = "apod_site") val apodSite: String,
    @Json(name = "media_type") val mediaType: String
)