package com.hossein.parsehub.models


import com.google.gson.annotations.SerializedName

data class ResponseItems(
    @SerializedName("docs")
    val docs: List<Doc?>?,
    @SerializedName("hasMore")
    val hasMore: Boolean?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("total")
    val total: Int?
) {
    data class Doc(
        @SerializedName("author")
        val author: Author?,
        @SerializedName("commentCount")
        val commentCount: Int?,
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("hasOwnerAnswer")
        val hasOwnerAnswer: Boolean?,
        @SerializedName("_id")
        val id: String?,
        @SerializedName("isMyPost")
        val isMyPost: Boolean?,
        @SerializedName("isPublished")
        val isPublished: Boolean?,
        @SerializedName("isReported")
        val isReported: Boolean?,
        @SerializedName("isSaved")
        val isSaved: Boolean?,
        @SerializedName("liked")
        val liked: Boolean?,
        @SerializedName("likesCount")
        val likesCount: Int?,
        @SerializedName("manuType")
        val manuType: String?,
        @SerializedName("manufacturer")
        val manufacturer: Manufacturer?,
        @SerializedName("media")
        val media: List<Media?>?,
        @SerializedName("ownerAnswer")
        val ownerAnswer: String?,
        @SerializedName("publishedAt")
        val publishedAt: String?,
        @SerializedName("status")
        val status: String?,
        @SerializedName("text")
        val text: String?,
        @SerializedName("updatedAt")
        val updatedAt: String?
    ) {
        data class Author(
            @SerializedName("avatar")
            val avatar: String?,
            @SerializedName("commentCount")
            val commentCount: Int?,
            @SerializedName("createdAt")
            val createdAt: String?,
            @SerializedName("emailNotificationIsOn")
            val emailNotificationIsOn: Boolean?,
            @SerializedName("getLikedCount")
            val getLikedCount: Int?,
            @SerializedName("_id")
            val id: String?,
            @SerializedName("invitedCount")
            val invitedCount: Int?,
            @SerializedName("isVerified")
            val isVerified: Boolean?,
            @SerializedName("language")
            val language: String?,
            @SerializedName("likedPostsCount")
            val likedPostsCount: Int?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("postCount")
            val postCount: Int?,
            @SerializedName("savedPostsCount")
            val savedPostsCount: Int?,
            @SerializedName("updatedAt")
            val updatedAt: String?,
            @SerializedName("userBolckedCount")
            val userBolckedCount: Int?
        )

        data class Manufacturer(
            @SerializedName("categories")
            val categories: List<String?>?,
            @SerializedName("createdAt")
            val createdAt: String?,
            @SerializedName("description")
            val description: String?,
            @SerializedName("followersCount")
            val followersCount: Int?,
            @SerializedName("_id")
            val id: String?,
            @SerializedName("imageUrl")
            val imageUrl: String?,
            @SerializedName("isPartner")
            val isPartner: Boolean?,
            @SerializedName("isSubscribed")
            val isSubscribed: Boolean?,
            @SerializedName("language")
            val language: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("publicMail")
            val publicMail: String?,
            @SerializedName("telefon")
            val telefon: String?,
            @SerializedName("website")
            val website: String?
        )

        data class Media(
            @SerializedName("_id")
            val id: String?,
            @SerializedName("mediaType")
            val mediaType: String?,
            @SerializedName("thumbnailUrl")
            val thumbnailUrl: String?,
            @SerializedName("url")
            val url: String?
        )
    }
}