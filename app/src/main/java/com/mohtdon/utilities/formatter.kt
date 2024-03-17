package com.mohtdon.utilities

import java.util.concurrent.TimeUnit


fun convertLongDurationToTime(getDurationInMillis: Long): String {
    val convertMinutes = String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(getDurationInMillis) -
            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(getDurationInMillis))
    )
    val convertSeconds = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(getDurationInMillis) -
            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(getDurationInMillis))
    )
    return "$convertMinutes:$convertSeconds"
}


