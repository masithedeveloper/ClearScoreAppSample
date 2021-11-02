package com.clearscore.report.ui.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.clearscore.report.R

class CircleProgressBar(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private var strokeWidth = 4f
    private var progress = 0f
    private var min = 0
    private var max = 100
    private val startAngle = -90
    private var color = Color.YELLOW
    private var rectF: RectF? = null
    private var backgroundPaint: Paint? = null
    var foregroundPaint: Paint? = null
    private fun init(context: Context, attrs: AttributeSet?) {
        rectF = RectF()
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CircleProgressBar,
            0, 0
        )
        try {
            strokeWidth = typedArray.getDimension(
                R.styleable.CircleProgressBar_progressBarThickness,
                strokeWidth
            )
            progress = typedArray.getFloat(R.styleable.CircleProgressBar_progress, progress)
            color = typedArray.getInt(R.styleable.CircleProgressBar_progressbarColor, color)
            min = typedArray.getInt(R.styleable.CircleProgressBar_min, min)
            max = typedArray.getInt(R.styleable.CircleProgressBar_max, max)
        } finally {
            typedArray.recycle()
        }
        backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        backgroundPaint!!.color = Color.WHITE
        backgroundPaint!!.style = Paint.Style.STROKE
        backgroundPaint!!.strokeWidth = strokeWidth
        foregroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        foregroundPaint!!.color = color
        foregroundPaint!!.style = Paint.Style.STROKE
        foregroundPaint!!.strokeWidth = strokeWidth
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val min = Math.min(width, height)
        setMeasuredDimension(min, min)
        rectF!![0 + strokeWidth / 2, 0 + strokeWidth / 2, min - strokeWidth / 2] =
            min - strokeWidth / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawOval(rectF!!, backgroundPaint!!)
        val angle = 360 * progress / max
        canvas.drawArc(rectF!!, startAngle.toFloat(), angle, false, foregroundPaint!!)
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        setColor()
    }

    private fun setColor() {
        foregroundPaint!!.color = if (progress.toInt() <= 25) Color.RED
        else if (progress.toInt() <= 50) Color.YELLOW
        else if (progress.toInt() <= 75) Color.CYAN
        else Color.GREEN
        this.invalidate()
    }

    init {
        init(context, attrs)
    }
}