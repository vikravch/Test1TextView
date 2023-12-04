package com.vikravch.myapplicationnavigation.ui.extension

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.util.Log
import android.widget.TextView
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.LineBackgroundSpan

fun TextView.setTextWithSpan(
    text: String,
    lineSpacing: Int = 20,
    backgroundColor: Int = Color.WHITE,
) {
    val textContent = if(text.length > 150) text.substring(0, 150) else text

    val textSize =  this.textSize
    val lineSpacingMultiplier = 1 + lineSpacing / textSize
    val padding = this.paddingLeft
    val builder = SpannableStringBuilder(textContent)
    val backgroundSpan =
        SpacingBackground(backgroundColor, padding, this.textSize)
    builder.setSpan(backgroundSpan, 0, textContent.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    this.setShadowLayer(padding.toFloat(), 0f, 0f, 0)
    this.setLineSpacing(0f, lineSpacingMultiplier)
    this.setText(builder, TextView.BufferType.SPANNABLE)
}

class SpacingBackground(
    private val backgroundColor: Int,
    private val paddingSize: Int,
    private val textSize: Float,
): LineBackgroundSpan {
    private val rect: RectF = RectF()

    override fun drawBackground(
        c: Canvas,
        p: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        currentLineNumber: Int
    ) {
        val textWidth = Math.round(p.measureText(text, start, end))
        val paintColor = p.color
        rect[(left - paddingSize / 2).toFloat(), (top - paddingSize / 2).toFloat(), (left + textWidth + paddingSize / 2).toFloat()] =
            top + textSize + paddingSize
        p.color = backgroundColor
        c.drawRoundRect(rect, 0f, 0f, p)
        p.color = paintColor
    }
}