package com.example.gogolookinterviewtronchen.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*

@Entity(tableName = "search_history", primaryKeys = ["date"])
class History(
    var inputString: String? = null,
    @ColumnInfo(name = "date")
    var date: Long? = Calendar.getInstance().time.time
) {
}