package exam.ai

interface ChatbotRequester {

    suspend fun request(question: String): String
}
