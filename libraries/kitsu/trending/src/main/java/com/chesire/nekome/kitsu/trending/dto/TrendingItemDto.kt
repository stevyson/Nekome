package com.chesire.nekome.kitsu.trending.dto

import com.chesire.nekome.core.flags.SeriesStatus
import com.chesire.nekome.core.flags.SeriesType
import com.chesire.nekome.core.flags.Subtype
import com.chesire.nekome.core.models.ImageModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * DTO from the Kitsu trending endpoint.
 */
@JsonClass(generateAdapter = true)
data class TrendingItemDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "type")
    val type: SeriesType,
    @Json(name = "attributes")
    val attributes: Attributes
) {
    /**
     * Attributes of the trending item.
     */
    @JsonClass(generateAdapter = true)
    data class Attributes(
        @Json(name = "slug")
        val slug: String,
        @Json(name = "synopsis")
        val synopsis: String,
        @Json(name = "canonicalTitle")
        val canonicalTitle: String,
        @Json(name = "startDate")
        val startDate: String?,
        @Json(name = "endDate")
        val endDate: String?,
        @Json(name = "subtype")
        val subtype: Subtype,
        @Json(name = "status")
        val status: SeriesStatus,
        @Json(name = "posterImage")
        val posterImage: ImageModel?,
        @Json(name = "coverImage")
        val coverImage: ImageModel?,
        @Json(name = "chapterCount")
        val chapterCount: Int?,
        @Json(name = "episodeCount")
        val episodeCount: Int?,
        @Json(name = "nsfw")
        val nsfw: Boolean = false
    )
}