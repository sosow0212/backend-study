package com.layered.global.util

inline fun throwWhen(
    condition: Boolean,
    supplier: () -> RuntimeException,
) {
    if (condition) throw supplier()
}
