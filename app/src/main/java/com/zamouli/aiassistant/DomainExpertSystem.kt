package com.zamouli.aiassistant

/**
 * نظام الخبرة المتخصص للمجالات المختلفة في زمولي
 */
class DomainExpertSystem {
    
    private val expertiseMap = mapOf(
        Domain.HEALTH to HealthExpert(),
        Domain.EDUCATION to EducationExpert(),
        Domain.ENTERTAINMENT to EntertainmentExpert(),
        Domain.PRODUCTIVITY to ProductivityExpert(),
        Domain.TRAVEL to TravelExpert(),
        Domain.SHOPPING to ShoppingExpert(),
        Domain.SOCIAL to SocialExpert(),
        Domain.FINANCE to FinanceExpert()
    )
    
    suspend fun processRequest(userRequest: UserRequest): ExpertResponse {
        val domain = Domain.detectFromText(userRequest.text)
        val expert = expertiseMap[domain] ?: GeneralExpert()
        
        return expert.processRequest(userRequest)
    }
    
    fun getExpertForDomain(domain: Domain): DomainExpert {
        return expertiseMap[domain] ?: GeneralExpert()
    }
}

abstract class DomainExpert {
    abstract suspend fun processRequest(userRequest: UserRequest): ExpertResponse
    abstract fun getCapabilities(): List<String>
    abstract fun getConfidenceLevel(request: String): Float
}

class HealthExpert : DomainExpert() {
    override suspend fun processRequest(userRequest: UserRequest): ExpertResponse {
        return ExpertResponse(
            domain = Domain.HEALTH,
            response = "يمكنني مساعدتك في أمور الصحة واللياقة",
            confidence = 0.9f,
            suggestions = listOf("تتبع اللياقة", "نصائح غذائية", "جدول التمارين")
        )
    }
    
    override fun getCapabilities(): List<String> {
        return listOf("تتبع اللياقة", "النظام الغذائي", "جودة النوم", "النصائح الطبية")
    }
    
    override fun getConfidenceLevel(request: String): Float {
        return if (Domain.detectFromText(request) == Domain.HEALTH) 0.9f else 0.1f
    }
}

class EducationExpert : DomainExpert() {
    override suspend fun processRequest(userRequest: UserRequest): ExpertResponse {
        return ExpertResponse(
            domain = Domain.EDUCATION,
            response = "يمكنني مساعدتك في التعلم والدراسة",
            confidence = 0.9f,
            suggestions = listOf("جدول الدراسة", "تقنيات التعلم", "مصادر التعليم")
        )
    }
    
    override fun getCapabilities(): List<String> {
        return listOf("التعلم التفاعلي", "إدارة الدراسة", "البحث الأكاديمي")
    }
    
    override fun getConfidenceLevel(request: String): Float {
        return if (Domain.detectFromText(request) == Domain.EDUCATION) 0.9f else 0.1f
    }
}

class GeneralExpert : DomainExpert() {
    override suspend fun processRequest(userRequest: UserRequest): ExpertResponse {
        return ExpertResponse(
            domain = Domain.GENERAL,
            response = "يمكنني مساعدتك في هذا الموضوع",
            confidence = 0.5f,
            suggestions = listOf("معلومات عامة", "نصائح مفيدة")
        )
    }
    
    override fun getCapabilities(): List<String> {
        return listOf("الإجابة على الأسئلة العامة", "تقديم المعلومات")
    }
    
    override fun getConfidenceLevel(request: String): Float {
        return 0.5f
    }
}

// باقي الخبراء
class EntertainmentExpert : DomainExpert() {
    override suspend fun processRequest(userRequest: UserRequest): ExpertResponse {
        return ExpertResponse(Domain.ENTERTAINMENT, "مساعدة في الترفيه", 0.8f, listOf())
    }
    override fun getCapabilities() = listOf("الألعاب", "الأفلام", "الموسيقى")
    override fun getConfidenceLevel(request: String) = if (Domain.detectFromText(request) == Domain.ENTERTAINMENT) 0.8f else 0.1f
}

class ProductivityExpert : DomainExpert() {
    override suspend fun processRequest(userRequest: UserRequest): ExpertResponse {
        return ExpertResponse(Domain.PRODUCTIVITY, "مساعدة في الإنتاجية", 0.9f, listOf())
    }
    override fun getCapabilities() = listOf("إدارة المهام", "التنظيم", "الجدولة")
    override fun getConfidenceLevel(request: String) = if (Domain.detectFromText(request) == Domain.PRODUCTIVITY) 0.9f else 0.1f
}

class TravelExpert : DomainExpert() {
    override suspend fun processRequest(userRequest: UserRequest): ExpertResponse {
        return ExpertResponse(Domain.TRAVEL, "مساعدة في السفر", 0.8f, listOf())
    }
    override fun getCapabilities() = listOf("التخطيط للرحلات", "حجز الفنادق", "معلومات الطقس")
    override fun getConfidenceLevel(request: String) = if (Domain.detectFromText(request) == Domain.TRAVEL) 0.8f else 0.1f
}

class ShoppingExpert : DomainExpert() {
    override suspend fun processRequest(userRequest: UserRequest): ExpertResponse {
        return ExpertResponse(Domain.SHOPPING, "مساعدة في التسوق", 0.8f, listOf())
    }
    override fun getCapabilities() = listOf("مقارنة المنتجات", "البحث عن العروض")
    override fun getConfidenceLevel(request: String) = if (Domain.detectFromText(request) == Domain.SHOPPING) 0.8f else 0.1f
}

class SocialExpert : DomainExpert() {
    override suspend fun processRequest(userRequest: UserRequest): ExpertResponse {
        return ExpertResponse(Domain.SOCIAL, "مساعدة اجتماعية", 0.7f, listOf())
    }
    override fun getCapabilities() = listOf("تنظيم الأحداث", "نصائح التواصل")
    override fun getConfidenceLevel(request: String) = if (Domain.detectFromText(request) == Domain.SOCIAL) 0.7f else 0.1f
}

class FinanceExpert : DomainExpert() {
    override suspend fun processRequest(userRequest: UserRequest): ExpertResponse {
        return ExpertResponse(Domain.FINANCE, "مساعدة مالية", 0.8f, listOf())
    }
    override fun getCapabilities() = listOf("إدارة الميزانية", "نصائح الادخار")
    override fun getConfidenceLevel(request: String) = if (Domain.detectFromText(request) == Domain.FINANCE) 0.8f else 0.1f
}

data class ExpertResponse(
    val domain: Domain,
    val response: String,
    val confidence: Float,
    val suggestions: List<String>
)