package utils

import androidx.compose.runtime.snapshots.SnapshotStateList

const val GEMINI_API_KEY = "AIzaSyCsARECQRyGNHcMFuVjPEDVrdpeEZ2swR4"
const val MAX_CHAT_DISPLAY = 4

fun <T> SnapshotStateList<T>.lruAdd(item: T) {
    if (size > MAX_CHAT_DISPLAY) {
        removeRange(0, size - 1)
    }
    add(item)
}