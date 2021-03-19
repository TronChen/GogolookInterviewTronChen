package com.example.gogolookinterviewtronchen.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*

@Entity(tableName = "search_history", primaryKeys = ["product_id", "product_selected_color_code", "product_selected_size"])
class History(
    val inputString: String? = null,
    @ColumnInfo(name = "date")
    val date: Date? = null
) {
}