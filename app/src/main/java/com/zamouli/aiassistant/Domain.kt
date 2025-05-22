package com.zamouli.aiassistant

/**
 * تعداد المجالات المختلفة للمساعد الذكي زمولي
 */
enum class Domain(val arabicName: String, val expertise: List<String>) {
    HEALTH("الصحة", listOf("تتبع اللياقة", "النظام الغذائي", "النوم", "الطب")),
    EDUCATION("التعليم", listOf("التعلم", "الدراسة", "البحث", "المهارات")),
    ENTERTAINMENT("الترفيه", listOf("الألعاب", "الأفلام", "الموسيقى", "القراءة")),
    PRODUCTIVITY("الإنتاجية", listOf("إدارة المهام", "التنظيم", "الجدولة", "العمل")),
    TRAVEL("السفر", listOf("السياحة", "النقل", "الحجوزات", "الطقس")),
    SHOPPING("التسوق", listOf("المنتجات", "المقارنات", "العروض", "التوصيات")),
    SOCIAL("الاجتماعي", listOf("التواصل", "العلاقات", "الأحداث", "المجتمع")),
    FINANCE("المالية", listOf("الميزانية", "الاستثمار", "البنوك", "المدفوعات")),
    GENERAL("عام", listOf("معلومات عامة", "أسئلة متنوعة"));
    
    companion object {
        fun detectFromText(text: String): Domain {
            return when {
                containsHealthKeywords(text) -> HEALTH
                containsEducationKeywords(text) -> EDUCATION
                containsEntertainmentKeywords(text) -> ENTERTAINMENT
                containsProductivityKeywords(text) -> PRODUCTIVITY
                containsTravelKeywords(text) -> TRAVEL
                containsShoppingKeywords(text) -> SHOPPING
                containsSocialKeywords(text) -> SOCIAL
                containsFinanceKeywords(text) -> FINANCE
                else -> GENERAL
            }
        }
        
        private fun containsHealthKeywords(text: String): Boolean {
            val healthKeywords = listOf("صحة", "رياضة", "طعام", "نوم", "طبيب", "دواء", "لياقة")
            return healthKeywords.any { text.contains(it, ignoreCase = true) }
        }
        
        private fun containsEducationKeywords(text: String): Boolean {
            val eduKeywords = listOf("تعلم", "دراسة", "مدرسة", "جامعة", "كتاب", "امتحان", "درس")
            return eduKeywords.any { text.contains(it, ignoreCase = true) }
        }
        
        private fun containsEntertainmentKeywords(text: String): Boolean {
            val entKeywords = listOf("فيلم", "لعبة", "موسيقى", "ترفيه", "مسلسل", "كتاب", "قراءة")
            return entKeywords.any { text.contains(it, ignoreCase = true) }
        }
        
        private fun containsProductivityKeywords(text: String): Boolean {
            val prodKeywords = listOf("عمل", "مهمة", "جدول", "تنظيم", "إنتاجية", "موعد", "اجتماع")
            return prodKeywords.any { text.contains(it, ignoreCase = true) }
        }
        
        private fun containsTravelKeywords(text: String): Boolean {
            val travelKeywords = listOf("سفر", "طيران", "فندق", "سياحة", "رحلة", "طقس", "مطار")
            return travelKeywords.any { text.contains(it, ignoreCase = true) }
        }
        
        private fun containsShoppingKeywords(text: String): Boolean {
            val shopKeywords = listOf("شراء", "منتج", "سعر", "متجر", "تسوق", "عرض", "خصم")
            return shopKeywords.any { text.contains(it, ignoreCase = true) }
        }
        
        private fun containsSocialKeywords(text: String): Boolean {
            val socialKeywords = listOf("أصدقاء", "عائلة", "حفلة", "مناسبة", "تواصل", "علاقة")
            return socialKeywords.any { text.contains(it, ignoreCase = true) }
        }
        
        private fun containsFinanceKeywords(text: String): Boolean {
            val finKeywords = listOf("مال", "بنك", "راتب", "ميزانية", "استثمار", "ادخار", "فاتورة")
            return finKeywords.any { text.contains(it, ignoreCase = true) }
        }
    }
}