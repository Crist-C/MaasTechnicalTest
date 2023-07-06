package com.ccastro.maas.data.Mapper

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ccastro.maas.domain.model.UserCard
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCardDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userCard: UserCard)

    @Query("SELECT * FROM userCards ORDER BY id DESC")
    fun getAll(): Flow<List<UserCard>>

    @Delete
    suspend fun delete(userCard: UserCard)

    @Query("SELECT COUNT(id) FROM userCards")
    suspend fun getTotalUserCards() : Int
}