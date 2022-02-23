package ru.neosvet.reddit.data

import com.google.gson.annotations.SerializedName

data class ChildData(
    @SerializedName("approved_at_utc") val approved_at_utc: String,
    @SerializedName("subreddit") val subreddit: String,
    @SerializedName("selftext") val selftext: String,
    @SerializedName("author_fullname") val author_fullname: String,
    @SerializedName("saved") val saved: Boolean,
    @SerializedName("mod_reason_title") val mod_reason_title: String,
    @SerializedName("gilded") val gilded: Int,
    @SerializedName("clicked") val clicked: Boolean,
    @SerializedName("title") val title: String,
    @SerializedName("link_flair_richtext") val link_flair_richtext: List<String>,
    @SerializedName("subreddit_name_prefixed") val subreddit_name_prefixed: String,
    @SerializedName("hidden") val hidden: Boolean,
    @SerializedName("pwls") val pwls: Int,
    @SerializedName("link_flair_css_class") val link_flair_css_class: String,
    @SerializedName("downs") val downs: Int,
    @SerializedName("thumbnail_height") val thumbnail_height: String,
    @SerializedName("top_awarded_type") val top_awarded_type: String,
    @SerializedName("hide_score") val hide_score: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("quarantine") val quarantine: Boolean,
    @SerializedName("link_flair_text_color") val link_flair_text_color: String,
    @SerializedName("upvote_ratio") val upvote_ratio: Double,
    @SerializedName("author_flair_background_color") val author_flair_background_color: String,
    @SerializedName("subreddit_type") val subreddit_type: String,
    @SerializedName("ups") val ups: Int,
    @SerializedName("total_awards_received") val total_awards_received: Int,
    @SerializedName("thumbnail_width") val thumbnail_width: String,
    @SerializedName("author_flair_template_id") val author_flair_template_id: String,
    @SerializedName("is_original_content") val is_original_content: Boolean,
    @SerializedName("user_reports") val user_reports: List<String>,
    @SerializedName("is_reddit_media_domain") val is_reddit_media_domain: Boolean,
    @SerializedName("is_meta") val is_meta: Boolean,
    @SerializedName("category") val category: String,
    @SerializedName("link_flair_text") val link_flair_text: String,
    @SerializedName("can_mod_post") val can_mod_post: Boolean,
    @SerializedName("score") val score: Int,
    @SerializedName("approved_by") val approved_by: String,
    @SerializedName("is_created_from_ads_ui") val is_created_from_ads_ui: Boolean,
    @SerializedName("author_premium") val author_premium: Boolean,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("edited") val edited: Boolean,
    @SerializedName("author_flair_css_class") val author_flair_css_class: String,
    @SerializedName("content_categories") val content_categories: String,
    @SerializedName("is_self") val is_self: Boolean,
    @SerializedName("mod_note") val mod_note: String,
    @SerializedName("created") val created: Int,
    @SerializedName("link_flair_type") val link_flair_type: String,
    @SerializedName("wls") val wls: Int,
    @SerializedName("removed_by_category") val removed_by_category: String,
    @SerializedName("banned_by") val banned_by: String,
    @SerializedName("author_flair_type") val author_flair_type: String,
    @SerializedName("domain") val domain: String,
    @SerializedName("allow_live_comments") val allow_live_comments: Boolean,
    @SerializedName("selftext_html") val selftext_html: String,
    @SerializedName("likes") val likes: String,
    @SerializedName("suggested_sort") val suggested_sort: String,
    @SerializedName("banned_at_utc") val banned_at_utc: String,
    @SerializedName("url_overridden_by_dest") val url_overridden_by_dest: String,
    @SerializedName("view_count") val view_count: String,
    @SerializedName("archived") val archived: Boolean,
    @SerializedName("no_follow") val no_follow: Boolean,
    @SerializedName("is_crosspostable") val is_crosspostable: Boolean,
    @SerializedName("pinned") val pinned: Boolean,
    @SerializedName("over_18") val over_18: Boolean,
    @SerializedName("awarders") val awarders: List<String>,
    @SerializedName("media_only") val media_only: Boolean,
    @SerializedName("can_gild") val can_gild: Boolean,
    @SerializedName("spoiler") val spoiler: Boolean,
    @SerializedName("locked") val locked: Boolean,
    @SerializedName("author_flair_text") val author_flair_text: String,
    @SerializedName("treatment_tags") val treatment_tags: List<String>,
    @SerializedName("visited") val visited: Boolean,
    @SerializedName("removed_by") val removed_by: String,
    @SerializedName("num_reports") val num_reports: String,
    @SerializedName("distinguished") val distinguished: String,
    @SerializedName("subreddit_id") val subreddit_id: String,
    @SerializedName("author_is_blocked") val author_is_blocked: Boolean,
    @SerializedName("mod_reason_by") val mod_reason_by: String,
    @SerializedName("removal_reason") val removal_reason: String,
    @SerializedName("link_flair_background_color") val link_flair_background_color: String,
    @SerializedName("id") val id: String,
    @SerializedName("is_robot_indexable") val is_robot_indexable: Boolean,
    @SerializedName("report_reasons") val report_reasons: String,
    @SerializedName("author") val author: String,
    @SerializedName("discussion_type") val discussion_type: String,
    @SerializedName("num_comments") val num_comments: Int,
    @SerializedName("send_replies") val send_replies: Boolean,
    @SerializedName("whitelist_status") val whitelist_status: String,
    @SerializedName("contest_mode") val contest_mode: Boolean,
    @SerializedName("mod_reports") val mod_reports: List<String>,
    @SerializedName("author_patreon_flair") val author_patreon_flair: Boolean,
    @SerializedName("author_flair_text_color") val author_flair_text_color: String,
    @SerializedName("permalink") val permalink: String,
    @SerializedName("parent_whitelist_status") val parent_whitelist_status: String,
    @SerializedName("stickied") val stickied: Boolean,
    @SerializedName("url") val url: String,
    @SerializedName("subreddit_subscribers") val subreddit_subscribers: Int,
    @SerializedName("created_utc") val created_utc: Int,
    @SerializedName("num_crossposts") val num_crossposts: Int,
    @SerializedName("is_video") val is_video: Boolean
)