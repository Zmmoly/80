package com.zamouli.aiassistant

/**
 * فئة تمثيل طلب المستخدم في زمولي
 */
data class UserRequest(
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val userId: String? = null,
    val context: RequestContext? = null,
    val priority: Priority = Priority.NORMAL,
    val expectedResponseType: ResponseType = ResponseType.TEXT
) {
    
    enum class Priority {
        LOW, NORMAL, HIGH, URGENT
    }
    
    enum class ResponseType {
        TEXT, VOICE, ACTION, MIXED
    }
    
    fun getCleanText(): String {
        return text.trim().replace(Regex("\\s+"), " ")
    }
    
    fun getWordCount(): Int {
        return getCleanText().split(" ").size
    }
    
    fun containsKeywords(keywords: List<String>): Boolean {
        val cleanText = getCleanText().lowercase()
        return keywords.any { cleanText.contains(it.lowercase()) }
    }
}

data class RequestContext(
    val location: String? = null,
    val timeOfDay: String? = null,
    val deviceType: String? = null,
    val previousRequests: List<String> = emptyList(),
    val userMood: EmotionalState? = null,
    val isVoiceInput: Boolean = false
)