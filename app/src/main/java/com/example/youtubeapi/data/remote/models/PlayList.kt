package com.example.youtubeapi.data.remote.models

data class PlayList(
    var kind: String,
    var etag: String,
    var items: List<Items>,
    var nextPageToken: String,
    var prevPageToken: String,
    var pageInfo: PageInfo
)

data class PageInfo(
    val resultsPerPage: String,
    val totalResults: String
)

data class Snippet(
    var publishedAt: String,
    var channelId: String,
    var title: String,
    var description: String,
    var thumbnails: Thumbnails,
    var channelTitle: String,
    var tags: List<String>,
    var categoryId: String,
    var liveBroadcastContent: String,
    var localized: Localized,
    var videoOwnerChannelTitle: String,
    var videoOwnerChannelId: String,
    var playlistId: String,
    var position: Int,
    var resourceId: ResourceId
)

data class Status(
    var privacyStatus: String
)

data class ResourceId(
    var kind: String,
    var videoId: String
)

data class Thumbnails(
    var default: Default,
    var medium: Medium,
    var high: High
)

data class Medium(
    var url: String,
    var width: Int,
    var height: Int
)

data class High(
    var url: String,
    var width: Int,
    var height: Int
)

data class Default(
    var url: String,
    var width: Int,
    var height: Int
)

data class Localized(
    var title: String,
    var description: String
)

data class Items(
    var kind: String,
    var etag: String,
    var id: String,
    var snippet: Snippet,
    var contentDetails: ContentDetails,
    var status: Status
)

data class ContentRating(val name: String = "")

data class ContentDetails(
    var duration: String,
    var dimension: String,
    var definition: String,
    var caption: String,
    var licensedContent: Boolean,
    var contentRating: ContentRating,
    var projection: String,
    var itemCount: Int,
    var videoId: String,
    var startAt: String,
    var endAt: String,
    var note: String,
    var videoPublishedAt: String
)



















