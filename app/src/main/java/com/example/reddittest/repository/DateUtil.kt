package com.example.reddittest.repository

import java.util.concurrent.TimeUnit

object DateUtil {
                                                                                                // created date returns without 3 last numbers
    fun dateInHoursAgo(created: Long) = TimeUnit.HOURS.convert(System.currentTimeMillis() - created*1000, TimeUnit.MILLISECONDS).toInt()
}