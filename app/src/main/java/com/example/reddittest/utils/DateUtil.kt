package com.example.reddittest.utils

import java.util.concurrent.TimeUnit

object DateUtil {
    /* date from reddit returns without 3 last numbers
    so i multiply it by 1000. There may be a problem with date accuracy because i use system time
    and to lazy to create adjusted method D
     */
    fun dateInHoursAgo(created: Long) = TimeUnit.HOURS.convert(System.currentTimeMillis() - created*1000, TimeUnit.MILLISECONDS).toInt()
}