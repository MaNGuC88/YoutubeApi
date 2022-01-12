package com.example.youtubeapi.data.remote.models

data class Video(
    val etag: String,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo,
    val prevPageToken: String,
    val items: List<VideoItems>,
)

data class VideoItems(
    val contentDetails: ContentDetails,
    val etag: String,
    val fileDetails: FileDetails,
    val id: String,
    val kind: String,
    val liveStreamingDetails: LiveStreamingDetails,
    val localizations: Localizations,
    val player: Player,
    val processingDetails: ProcessingDetails,
    val recordingDetails: RecordingDetails,
    val snippet: Snippet,
    val statistics: Statistics,
    val status: Status,
    val suggestions: Suggestions,
    val topicDetails: TopicDetails,
)

data class VideoStream(
    val aspectRatio: String,
    val bitrateBps: String,
    val codec: String,
    val frameRateFps: String,
    val heightPixels: String,
    val rotation: String,
    val vendor: String,
    val widthPixels: String,
)

data class TopicDetails(
    val relevantTopicIds: List<String>,
    val topicCategories: List<String>,
    val topicIds: List<String>,
)

data class TagSuggestion(
    val categoryRestricts: List<String>,
    val tag: String,
)

data class Suggestions(
    val editorSuggestions: List<String>,
    val processingErrors: List<String>,
    val processingHints: List<String>,
    val processingWarnings: List<String>,
    val tagSuggestions: List<TagSuggestion>,
)

data class Statistics(
    val commentCount: String,
    val dislikeCount: String,
    val favoriteCount: String,
    val likeCount: String,
    val viewCount: String,
)

data class RegionRestriction(
    val allowed: List<String>,
    val blocked: List<String>,
)

data class RecordingDetails(
    val recordingDate: String,
)

data class ProcessingProgress(
    val partsProcessed: String,
    val partsTotal: String,
    val timeLeftMs: String,
)

data class ProcessingDetails(
    val editorSuggestionsAvailability: String,
    val fileDetailsAvailability: String,
    val processingFailureReason: String,
    val processingIssuesAvailability: String,
    val processingProgress: ProcessingProgress,
    val processingStatus: String,
    val tagSuggestionsAvailability: String,
    val thumbnailsAvailability: String,
)

data class Player(
    val embedHeight: String,
    val embedHtml: String,
    val embedWidth: String,
)

data class LiveStreamingDetails(
    val activeLiveChatId: String,
    val actualEndTime: String,
    val actualStartTime: String,
    val concurrentViewers: String,
    val scheduledEndTime: String,
    val scheduledStartTime: String,
)

data class FileDetails(
    val audioStreams: List<AudioStream>,
    val bitrateBps: String,
    val container: String,
    val creationTime: String,
    val durationMs: String,
    val fileName: String,
    val fileSize: String,
    val fileType: String,
    val videoStreams: List<VideoStream>,
)

data class AudioStream(
    val bitrateBps: String,
    val channelCount: String,
    val codec: String,
    val vendor: String,
)

data class Localizations(
    var title: String,
    var description: String,
)