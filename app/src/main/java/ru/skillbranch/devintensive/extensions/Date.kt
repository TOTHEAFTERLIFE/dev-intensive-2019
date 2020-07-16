package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time
    time +=when(units){
        TimeUnits.SECOND-> value * SECOND
        TimeUnits.MINUTE-> value * MINUTE
        TimeUnits.HOUR-> value * HOUR
        TimeUnits.DAY-> value * DAY
        else -> throw IllegalStateException("invalid unit")
    }
    this.time = time
    return this
}


fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = date.time - this.time
    fun text(format: Long, s: String, s1: String, s2: String) : String {
        return when {
            ((diff/format) % 10 == 1L)||(-diff/format) % 10 == 1L -> s
            ((diff/format in 2..4)||(((diff/format) % 10 in 2..4)&&(diff>20)))||
                    ((-diff/format in 2..4)||(((-diff/format) % 10 in 2..4)&&(-diff>20))) -> s1
            else -> s2
        }
    }
    if (diff < 0) {
        diff == -diff
        return when (-diff) {
            in 0..1 * SECOND -> "сейчас"
            in 1 * SECOND..45 * SECOND -> "через несколько секунд"
            in 45 * SECOND..75 * SECOND -> "через минуту"
            in 75 * SECOND..45 * MINUTE -> "через ${-diff / MINUTE} ${text(MINUTE, "минуту", "минуты", "минут")}"
            in 45 * MINUTE..75 * MINUTE -> "через час"
            in 75 * MINUTE..22 * HOUR -> "через ${-diff/HOUR} ${text(HOUR, "час", "часа", "часов")}"
            in 22 * HOUR..26 * HOUR -> "через день"
            in 26 * HOUR..360 * DAY -> "через ${-diff/DAY} ${text(DAY,"день", "дня", "дней")}"
            else -> "более чем через год"
        }
    } else {
        return when (diff) {
            in 0..1 * SECOND -> "только что"
            in 1 * SECOND..45 * SECOND -> "несколько секунд назад"
            in 45 * SECOND..75 * SECOND -> "минуту назад"
            in 75 * SECOND..45 * MINUTE -> "${diff / MINUTE} ${text(MINUTE, "минуту", "минуты", "минут")} назад"
            in 45 * MINUTE..75 * MINUTE -> "час назад"
            in 75 * MINUTE..22 * HOUR -> "${diff / HOUR} ${text(HOUR, "час", "часа", "часов")} назад"
            in 22 * HOUR..26 * HOUR -> "день назад"
            in 26 * HOUR..360 * DAY -> "${diff / DAY} ${text(DAY,"день", "дня", "дней")} назад"
            else -> "более года назад"
        }
    }
}

enum class TimeUnits(val s: String, val s1: String, val s2: String) {
    SECOND("секунду", "секунды", "секунд"),
    MINUTE("минуту", "минуты", "минут"),
    HOUR("час", "часа", "часов"),
    DAY("день", "дня", "дней");

    fun plural(value: Int, s: String, s1: String, s2: String) : String {
        return when {
            value % 10 == 1 -> s
            value in 2..4||((value % 10 in 2..4)&&(value>20)) -> s1
            else -> s2
        }
    }
}
//updated2