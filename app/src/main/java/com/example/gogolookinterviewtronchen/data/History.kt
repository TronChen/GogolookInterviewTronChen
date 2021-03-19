package com.example.gogolookinterviewtronchen.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*

@Entity(tableName = "search_history", primaryKeys = ["date"])
class History(
    val inputString: String? = null,
    @ColumnInfo(name = "date")
    val date: Long? = Calendar.getInstance().time.time
) {
}