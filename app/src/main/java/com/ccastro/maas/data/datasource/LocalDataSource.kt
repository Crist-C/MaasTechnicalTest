package com.ccastro.maas.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ccastro.maas.data.datasource.dao.UserCardDAO
import com.ccastro.maas.domain.model.UserCard

@Database(entities = [UserCard::class], version = 1)
abstract class LocalDataSource : RoomDatabase() {

    abstract fun userCardDao(): UserCardDAO
}