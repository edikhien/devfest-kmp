package utils

import android.content.Context
import android.speech.tts.TextToSpeech
import android.view.inputmethod.InputMethodManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import java.util.Locale

var tts: TextToSpeech? = null

@Composable
actual fun HideKeyboard() {
    val context = LocalContext.current

    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(LocalView.current.windowToken, 0)
}

@Composable
actual fun TextToSpeech(content: String) {
    val context = LocalContext.current

    val listener = TextToSpeech.OnInitListener { status ->
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale("id", "ID"))
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)  return@OnInitListener

            tts?.speak(content, TextToSpeech.QUEUE_FLUSH, null, "")
            tts = null
        }
    }

    tts = TextToSpeech(context, listener)
}
