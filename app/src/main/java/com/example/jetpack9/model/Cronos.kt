package com.example.jetpack9.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cronos")
data class Cronos(@PrimaryKey(autoGenerate = true)
                  val id:Long = 0,
                  @ColumnInfo(name = "title")val title:String,
                  @ColumnInfo("crono")val crono:Long)
