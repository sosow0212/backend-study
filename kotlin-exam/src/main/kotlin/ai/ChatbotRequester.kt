package com.ai

interface ChatbotRequester {

    suspend fun request(question: String): String
}
