package com.zamouli.aiassistant

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentHashMap

/**
 * مدير الذاكرة للمساعد الذكي زمولي
 * يدير حفظ واسترجاع المعلومات والذكريات
 */
class MemoryManager(private val context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences("zamouli_memory", Context.MODE_PRIVATE)
    private val shortTermMemory = ConcurrentHashMap<String, Any>()
    private val conversationHistory = mutableListOf<ConversationEntry>()
    
    companion object {
        @Volatile
        private var INSTANCE: MemoryManager? = null
        
        fun getInstance(context: Context): MemoryManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: MemoryManager(context).also { INSTANCE = it }
            }
        }
    }
    
    // حفظ في الذاكرة قصيرة المدى
    fun storeShortTerm(key: String, value: Any) {
        shortTermMemory[key] = value
    }
    
    // استرجاع من الذاكرة قصيرة المدى
    fun getShortTerm(key: String): Any? {
        return shortTermMemory[key]
    }
    
    // حفظ في الذاكرة طويلة المدى
    fun storeLongTerm(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }
    
    // استرجاع من الذاكرة طويلة المدى
    fun getLongTerm(key: String): String? {
        return prefs.getString(key, null)
    }
    
    // إضافة محادثة للتاريخ
    fun addConversation(userInput: String, aiResponse: String) {
        val entry = ConversationEntry(
            timestamp = System.currentTimeMillis(),
            userInput = userInput,
            aiResponse = aiResponse,
            emotionalState = EmotionalState.fromText(userInput)
        )
        conversationHistory.add(entry)
        
        // الحفاظ على آخر 100 محادثة فقط
        if (conversationHistory.size > 100) {
            conversationHistory.removeAt(0)
        }
    }
    
    // استرجاع تاريخ المحادثات
    fun getConversationHistory(): List<ConversationEntry> {
        return conversationHistory.toList()
    }
    
    // البحث في الذكريات
    suspend fun searchMemories(query: String): List<MemoryResult> = withContext(Dispatchers.IO) {
        val results = mutableListOf<MemoryResult>()
        
        conversationHistory.forEach { conversation ->
            if (conversation.userInput.contains(query, ignoreCase = true) ||
                conversation.aiResponse.contains(query, ignoreCase = true)) {
                results.add(MemoryResult(
                    type = "conversation",
                    content = conversation.userInput,
                    timestamp = conversation.timestamp,
                    relevance = calculateRelevance(query, conversation.userInput)
                ))
            }
        }
        
        results.sortedByDescending { it.relevance }
    }
    
    private fun calculateRelevance(query: String, content: String): Float {
        val queryWords = query.split(" ")
        val contentWords = content.split(" ")
        var matches = 0
        
        queryWords.forEach { queryWord ->
            contentWords.forEach { contentWord ->
                if (contentWord.contains(queryWord, ignoreCase = true)) {
                    matches++
                }
            }
        }
        
        return matches.toFloat() / queryWords.size.toFloat()
    }
    
    // مسح الذاكرة
    fun clearMemory() {
        shortTermMemory.clear()
        conversationHistory.clear()
        prefs.edit().clear().apply()
    }
    
    // وظائف إضافية مطلوبة في الكود
    fun extractConsistentDailyPatterns(): List<DailyPattern> {
        // تحليل الأنماط اليومية
        return emptyList()
    }
    
    fun extractWeeklyPatterns(): List<WeeklyPattern> {
        // تحليل الأنماط الأسبوعية
        return emptyList()
    }
    
    fun extractSeasonalPreferences(): Map<String, Any> {
        // تحليل التفضيلات الموسمية
        return emptyMap()
    }
    
    fun getHealthSummary(): HealthSummary {
        return HealthSummary()
    }
    
    fun getExplicitPreferences(): UserPreferences {
        return UserPreferences()
    }
    
    fun getUserDecisions(): List<Decision> {
        return emptyList()
    }
    
    fun getVitalStats(): VitalStats {
        return VitalStats()
    }
    
    fun getActivityData(): ActivityData {
        return ActivityData()
    }
    
    fun getSleepData(): SleepData {
        return SleepData()
    }
    
    fun getNutritionData(): NutritionData {
        return NutritionData()
    }
}

data class ConversationEntry(
    val timestamp: Long,
    val userInput: String,
    val aiResponse: String,
    val emotionalState: EmotionalState
)

data class MemoryResult(
    val type: String,
    val content: String,
    val timestamp: Long,
    val relevance: Float
)

// فئات إضافية مطلوبة
data class DailyPattern(
    val timeOfDay: String,
    val activity: String,
    val frequency: Float
)

data class WeeklyPattern(
    val dayOfWeek: String,
    val activities: List<String>
)

data class HealthSummary(
    val overallHealth: String = "جيد",
    val lastCheckup: Long = System.currentTimeMillis()
)

data class UserPreferences(
    val language: String = "ar",
    val theme: String = "light",
    val notifications: Boolean = true
)

data class Decision(
    val context: String,
    val choice: String,
    val timestamp: Long
)

data class VitalStats(
    val heartRate: Int = 75,
    val bloodPressure: String = "120/80"
)

data class ActivityData(
    val steps: Int = 0,
    val calories: Int = 0,
    val distance: Float = 0f
)

data class SleepData(
    val duration: Float = 8f,
    val quality: String = "جيد"
)

data class NutritionData(
    val calories: Int = 0,
    val protein: Float = 0f,
    val carbs: Float = 0f,
    val fat: Float = 0f
)