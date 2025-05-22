package com.zamouli.aiassistant

/**
 * فئة تمثيل الحالة العاطفية للمستخدم
 * تم إنشاؤها لحل الأخطاء البرمجية
 */
enum class EmotionalState(val arabicName: String, val intensity: Float) {
    HAPPY("سعيد", 0.8f),
    SAD("حزين", 0.6f),
    ANGRY("غاضب", 0.9f),
    EXCITED("متحمس", 0.9f),
    CALM("هادئ", 0.5f),
    ANXIOUS("قلق", 0.7f),
    CONFUSED("مرتبك", 0.6f),
    CONFIDENT("واثق", 0.8f),
    NEUTRAL("محايد", 0.3f);
    
    companion object {
        fun fromText(text: String): EmotionalState {
            return when {
                text.contains("سعيد") || text.contains("فرح") -> HAPPY
                text.contains("حزين") || text.contains("مكتئب") -> SAD
                text.contains("غاضب") || text.contains("زعلان") -> ANGRY
                text.contains("متحمس") || text.contains("منشرح") -> EXCITED
                text.contains("هادئ") || text.contains("مسترخي") -> CALM
                text.contains("قلق") || text.contains("متوتر") -> ANXIOUS
                text.contains("مرتبك") || text.contains("محتار") -> CONFUSED
                text.contains("واثق") || text.contains("متأكد") -> CONFIDENT
                else -> NEUTRAL
            }
        }
    }
}