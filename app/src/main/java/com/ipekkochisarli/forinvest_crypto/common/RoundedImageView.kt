package com.ipekkochisarli.forinvest_crypto.common

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.ipekkochisarli.forinvest_crypto.R

class RoundedButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.buttonStyle
) : AppCompatButton(context, attrs, defStyleAttr) {

    private var cornerRadius: Float = 0f
    private var backgroundColor: Int = Color.BLACK
    private var textColor: Int = Color.WHITE
    private var textSizePx: Float = 14f
    private var buttonText: String = ""

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundedButtonView)
        with(typedArray) {
            cornerRadius = getDimension(R.styleable.RoundedButtonView_cornerRadius, 0f)
            backgroundColor = getColor(R.styleable.RoundedButtonView_buttonBackgroundColor, Color.BLACK)
            textColor = getColor(R.styleable.RoundedButtonView_buttonTextColor, Color.WHITE)
            textSizePx = getDimension(R.styleable.RoundedButtonView_buttonTextSize, 14f)
            buttonText = getString(R.styleable.RoundedButtonView_buttonText) ?: ""

            recycle()
        }

        background = GradientDrawable().apply {
            cornerRadius = this@RoundedButtonView.cornerRadius
            setColor(backgroundColor)
        }

        setTextColor(textColor)
        text = buttonText
        textSize = pxToSp(context, textSizePx)
    }

    private fun pxToSp(context: Context, px: Float): Float {
        return px / context.resources.displayMetrics.scaledDensity
    }
}

