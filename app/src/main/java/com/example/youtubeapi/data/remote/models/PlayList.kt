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
    var resourceId: ResourceId,
    val defaultAudioLanguage: String,
    val defaultLanguage: String,
)

data class Status(
    var privacyStatus: String,
    val embeddable: String,
    val failureReason: String,
    val license: String,
    val madeForKids: String,
    val publicStatsViewable: String,
    val publishAt: String,
    val rejectionReason: String,
    val selfDeclaredMadeForKids: String,
    val uploadStatus: String
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

data class ContentRating(
    val name: String = "",
    val acbRating: String,
    val agcomRating: String,
    val anatelRating: String,
    val bbfcRating: String,
    val bfvcRating: String,
    val bmukkRating: String,
    val catvRating: String,
    val catvfrRating: String,
    val cbfcRating: String,
    val cccRating: String,
    val cceRating: String,
    val chfilmRating: String,
    val chvrsRating: String,
    val cicfRating: String,
    val cnaRating: String,
    val cncRating: String,
    val csaRating: String,
    val cscfRating: String,
    val czfilmRating: String,
    val djctqRating: String,
    val djctqRatingReasons: List<Any>,
    val ecbmctRating: String,
    val eefilmRating: String,
    val egfilmRating: String,
    val eirinRating: String,
    val fcbmRating: String,
    val fcoRating: String,
    val fmocRating: String,
    val fpbRating: String,
    val fpbRatingReasons: List<Any>,
    val fskRating: String,
    val grfilmRating: String,
    val icaaRating: String,
    val ifcoRating: String,
    val ilfilmRating: String,
    val incaaRating: String,
    val kfcbRating: String,
    val kijkwijzerRating: String,
    val kmrbRating: String,
    val lsfRating: String,
    val mccaaRating: String,
    val mccypRating: String,
    val mcstRating: String,
    val mdaRating: String,
    val medietilsynetRating: String,
    val mekuRating: String,
    val mibacRating: String,
    val mocRating: String,
    val moctwRating: String,
    val mpaaRating: String,
    val mpaatRating: String,
    val mtrcbRating: String,
    val nbcRating: String,
    val nbcplRating: String,
    val nfrcRating: String,
    val nfvcbRating: String,
    val nkclvRating: String,
    val oflcRating: String,
    val pefilmRating: String,
    val rcnofRating: String,
    val resorteviolenciaRating: String,
    val rtcRating: String,
    val rteRating: String,
    val russiaRating: String,
    val skfilmRating: String,
    val smaisRating: String,
    val smsaRating: String,
    val tvpgRating: String,
    val ytRating: String
)

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
    var videoPublishedAt: String,
    val hasCustomThumbnail: String,
    val regionRestriction: RegionRestriction
)



















