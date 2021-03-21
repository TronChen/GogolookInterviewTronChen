package com.example.gogolookinterviewtronchen.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "search_history")
class History(
    @PrimaryKey(autoGenerate = true)
    var id : Long? = null,
    var inputString: String? = null,
    @ColumnInfo(name = "date")
    var date: Long? = Calendar.getInstance().time.time
) {
}