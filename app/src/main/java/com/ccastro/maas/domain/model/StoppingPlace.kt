package com.ccastro.maas.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stations")
data class StoppingPlace (

    @PrimaryKey(autoGenerate = true) val idDB: Int = 0,
    @ColumnInfo(name = "id") val id : String = "xxxxxx",
    @ColumnInfo(name = "code") val code: String = "",
    @ColumnInfo(name = "name") val name : String = "------",
    @ColumnInfo(name = "lat") val lat: Double = 4.722557,
    @ColumnInfo(name = "lon") val lon: Double = -74.131103,
    @ColumnInfo(name = "dist") val dist: String = "-",
    @ColumnInfo(name = "address") val addres: String = "",

    )