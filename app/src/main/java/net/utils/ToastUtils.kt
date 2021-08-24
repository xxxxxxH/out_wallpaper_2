package net.utils

import android.content.Context
import android.widget.Toast

open class ToastUtils(
    val context: Context,
    val content: String
) {
    fun showToast() {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }
}