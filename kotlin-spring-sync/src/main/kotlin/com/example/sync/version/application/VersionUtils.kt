package com.example.sync.version.application

class VersionUtils {
    companion object {
        fun compare(v1: String, v2: String): Int {
            val parts1 = v1.split(".").map { it.toInt() }
            val parts2 = v2.split(".").map { it.toInt() }

            val maxLength = maxOf(parts1.size, parts2.size)
            val padded1 = parts1 + List(maxLength - parts1.size) { 0 }
            val padded2 = parts2 + List(maxLength - parts2.size) { 0 }

            for (i in 0 until maxLength) {
                if (padded1[i] < padded2[i]) return -1
                if (padded1[i] > padded2[i]) return 1
            }
            return 0
        }
    }
}
