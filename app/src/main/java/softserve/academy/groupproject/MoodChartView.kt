package softserve.academy.groupproject

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.roundToInt

class MoodChartView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var moodData: List<Pair<Long, Int>> = emptyList()

    private val axisPaint = Paint().apply {
        color = Color.LTGRAY
        strokeWidth = 2f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    private val linePaint = Paint().apply {
        color = Color.parseColor("#3F51B5") // Indigo
        strokeWidth = 6f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    private val circlePaint = Paint().apply {
        color = Color.parseColor("#FF9800") // Orange
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val labelPaint = Paint().apply {
        color = Color.DKGRAY
        textSize = 28f
        isAntiAlias = true
    }

    fun setMoodData(data: List<Pair<Long, Int>>) {
        moodData = data.sortedBy { it.first }
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (moodData.size < 2) {
            canvas.drawText("Недостатньо даних для графіка", 50f, height / 2f, labelPaint)
            return
        }

        val padding = 100f
        val usableWidth = width - 2 * padding
        val usableHeight = height - 2 * padding
        val maxMood = 100
        val minMood = 0

        val moodSteps = listOf(0, 25, 50, 75, 100)
        for (i in moodSteps) {
            val fraction = (maxMood - i).toFloat() / (maxMood - minMood)
            val y = padding + fraction * usableHeight
            canvas.drawLine(padding, y, width - padding, y, axisPaint)
            canvas.drawText("$i%", padding - 60f, y + 10f, labelPaint)
        }

        // Підготовка точок
        val minTime = moodData.first().first
        val maxTime = moodData.last().first
        val timeRange = (maxTime - minTime).coerceAtLeast(1L)

        val points = moodData.map { (timestamp, mood) ->
            val x = padding + ((timestamp - minTime).toFloat() / timeRange) * usableWidth
            val y = padding + ((maxMood - mood).toFloat() / (maxMood - minMood)) * usableHeight
            PointF(x.coerceIn(padding, width - padding), y.coerceIn(padding, height - padding))
        }

        // Малювання лінії графіка
        val path = Path()
        points.forEachIndexed { index, point ->
            if (index == 0) path.moveTo(point.x, point.y)
            else path.lineTo(point.x, point.y)
        }
        canvas.drawPath(path, linePaint)

        // Малювання точок
        points.forEach { point ->
            canvas.drawCircle(point.x, point.y, 10f, circlePaint)
        }

        // Малювання підписів часу (макс. 5 підписів)
        val dateFormat = java.text.SimpleDateFormat("dd.MM", java.util.Locale.getDefault())

        // Перша дата (мінімальна) — ліворуч внизу (біля осі Y)
        val currentDateLabel = dateFormat.format(java.util.Date(moodData.first().first))
        canvas.drawText(currentDateLabel, padding - 30f, height - padding / 3, labelPaint)

        // Поточна дата (максимальна) — праворуч внизу
        val endDateLabel  = dateFormat.format(java.util.Date(moodData.last().first))
        canvas.drawText(endDateLabel, width - padding - 40f, height - padding / 3, labelPaint)
    }
}
