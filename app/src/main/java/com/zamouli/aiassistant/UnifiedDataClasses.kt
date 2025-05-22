package com.zamouli.aiassistant

/**
 * ملف موحد لجميع فئات البيانات لتجنب الاستدعاءات المكررة (Redeclarations)
 * يحل جميع أخطاء التكرار في مشروع زمولي
 */

// ===== فئات تحليل الصور =====
data class ImageAnalysisResult(
    val detectedObjects: List<DetectedObject>,
    val confidence: Float,
    val processingTime: Long
)

data class DetectedObject(
    val label: String,
    val confidence: Float,
    val boundingBox: Rect
)

data class Rect(
    val left: Float,
    val top: Float,
    val right: Float,
    val bottom: Float
)

// ===== فئات الموقع والمكان =====
data class Coordinates(
    val latitude: Double,
    val longitude: Double,
    val altitude: Double = 0.0,
    val accuracy: Float = 0f
)

data class Place(
    val name: String,
    val address: String,
    val coordinates: Coordinates,
    val type: String,
    val rating: Float = 0f
)

// ===== فئات الطقس =====
data class WeatherInfo(
    val temperature: Float,
    val humidity: Float,
    val pressure: Float,
    val description: String,
    val windSpeed: Float
)

data class WeatherForecast(
    val date: Long,
    val maxTemp: Float,
    val minTemp: Float,
    val condition: String,
    val precipitationChance: Float
)

// ===== فئات السفر =====
data class RouteInfo(
    val startLocation: Coordinates,
    val endLocation: Coordinates,
    val distance: Float,
    val estimatedTime: Long,
    val routeType: String // walking, driving, transit
)

data class TravelPlan(
    val destination: Place,
    val startDate: Long,
    val endDate: Long,
    val budget: Float,
    val activities: List<String>
)

// ===== فئات الصحة واللياقة =====
data class ActivityData(
    val steps: Int,
    val calories: Int,
    val distance: Float,
    val activeMinutes: Int,
    val exerciseType: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

data class SleepData(
    val bedTime: Long,
    val wakeTime: Long,
    val duration: Float, // بالساعات
    val quality: String, // excellent, good, fair, poor
    val deepSleepMinutes: Int = 0,
    val lightSleepMinutes: Int = 0
)

data class VitalStats(
    val heartRate: Int,
    val bloodPressure: String, // "120/80"
    val bodyTemperature: Float = 37f,
    val oxygenSaturation: Int = 98
)

data class NutritionData(
    val calories: Int,
    val protein: Float,
    val carbohydrates: Float,
    val fat: Float,
    val fiber: Float = 0f,
    val sugar: Float = 0f
)

// ===== فئات البحث والمعرفة =====
data class SearchResult(
    val title: String,
    val description: String,
    val url: String,
    val relevanceScore: Float,
    val source: String,
    val timestamp: Long = System.currentTimeMillis()
)

data class Insight(
    val content: String,
    val category: String,
    val confidence: Float,
    val source: String,
    val timestamp: Long,
    val relatedTopics: List<String> = emptyList()
)

// ===== فئات التفضيلات والإعدادات =====
data class UserPreferences(
    val language: String = "ar",
    val theme: String = "light",
    val fontSize: String = "medium",
    val voiceEnabled: Boolean = true,
    val notificationsEnabled: Boolean = true,
    val privacyLevel: String = "medium"
)

data class AccessibilityPreferences(
    val fontSize: String = "medium", // small, medium, large, extra_large
    val highContrast: Boolean = false,
    val voiceNavigation: Boolean = false,
    val screenReader: Boolean = false,
    val colorBlindSupport: Boolean = false
)

// ===== فئات الذكاء الاصطناعي =====
data class AIResponse(
    val text: String,
    val confidence: Float,
    val processingTime: Long,
    val sources: List<String>,
    val suggestedActions: List<String> = emptyList()
)

data class ConversationContext(
    val userId: String,
    val sessionId: String,
    val previousMessages: List<String>,
    val currentTopic: String,
    val emotionalState: EmotionalState,
    val timestamp: Long = System.currentTimeMillis()
)

// ===== فئات الأتمتة =====
data class AutomationRule(
    val id: String,
    val name: String,
    val trigger: String,
    val action: String,
    val conditions: List<String>,
    val isEnabled: Boolean = true
)

data class DeviceAction(
    val type: String, // open_app, send_message, set_alarm, etc.
    val target: String,
    val parameters: Map<String, Any>,
    val timestamp: Long = System.currentTimeMillis()
)

// ===== فئات التعلم والتطوير =====
data class LearningData(
    val subject: String,
    val progress: Float, // 0.0 to 1.0
    val timeSpent: Long, // milliseconds
    val skillLevel: String, // beginner, intermediate, advanced
    val lastActivity: Long
)

data class SkillAssessment(
    val skillName: String,
    val currentLevel: Float,
    val targetLevel: Float,
    val recommendedActions: List<String>
)

// ===== فئات الأمان والخصوصية =====
data class SecuritySettings(
    val biometricEnabled: Boolean = false,
    val dataEncryption: Boolean = true,
    val locationTracking: Boolean = false,
    val dataRetentionDays: Int = 30
)

data class PrivacyLevel(
    val dataSharing: String, // none, minimal, moderate, full
    val analyticsEnabled: Boolean = false,
    val personalizedAds: Boolean = false
)

// ===== فئات الاستجابة والتفاعل =====
data class InteractionLog(
    val userId: String,
    val action: String,
    val context: String,
    val success: Boolean,
    val timestamp: Long,
    val metadata: Map<String, Any> = emptyMap()
)

data class FeedbackData(
    val rating: Int, // 1-5
    val comment: String,
    val category: String,
    val timestamp: Long,
    val resolved: Boolean = false
)

// ===== فئات التحليل والإحصائيات =====
data class UsageStatistics(
    val dailyInteractions: Int,
    val mostUsedFeatures: List<String>,
    val averageSessionDuration: Long,
    val satisfactionScore: Float
)

data class PerformanceMetrics(
    val responseTime: Long,
    val accuracy: Float,
    val memoryUsage: Long,
    val batteryImpact: Float
)

// ===== تعدادات مساعدة =====
enum class NotificationPriority {
    LOW, NORMAL, HIGH, URGENT
}

enum class DataSyncStatus {
    SYNCED, PENDING, FAILED, OFFLINE
}

enum class ResponseType {
    TEXT, VOICE, ACTION, VISUAL, MIXED
}

// ===== كلاسات مساعدة =====
data class TimeRange(
    val startTime: Long,
    val endTime: Long
) {
    fun duration(): Long = endTime - startTime
    fun contains(timestamp: Long): Boolean = timestamp in startTime..endTime
}

data class LocationRange(
    val center: Coordinates,
    val radiusMeters: Float
) {
    fun contains(location: Coordinates): Boolean {
        // حساب المسافة وتحديد ما إذا كانت النقطة داخل النطاق
        return true // مبسط للمثال
    }
}

// ===== فئات الأحداث =====
data class CalendarEvent(
    val title: String,
    val description: String,
    val startTime: Long,
    val endTime: Long,
    val location: String,
    val attendees: List<String> = emptyList(),
    val priority: NotificationPriority = NotificationPriority.NORMAL
)

data class Reminder(
    val id: String,
    val title: String,
    val description: String,
    val targetTime: Long,
    val isRecurring: Boolean = false,
    val recurrencePattern: String = ""
)