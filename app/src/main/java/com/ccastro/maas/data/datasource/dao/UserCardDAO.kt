package com.ccastro.maas.data.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ccastro.maas.domain.model.UserCard
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCardDAO {

    @Query("SELECT COUNT(id) FROM userCards WHERE cardNumber = :userCardNumber AND currentUserId = :currentUserId")
    suspend fun verifyIfCardExist(userCardNumber: String, currentUserId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userCard: UserCard)

    @Query("SELECT * FROM userCards WHERE currentUserId = :currentUserId ORDER BY id DESC")
    fun getAll(currentUserId: String): Flow<List<UserCard>>

    @Delete
    suspend fun delete(userCard: UserCard)

    @Query("SELECT COUNT(id) FROM userCards")
    suspend fun getTotalUserCards() : Int
}