package com.ai

import kotlinx.coroutines.delay

class DeepSeekRequester : ChatbotRequester {

    override suspend fun request(question: String): String {
        delay(3000)
        println("deepSeek")
        return "deepSeek"
    }
}
