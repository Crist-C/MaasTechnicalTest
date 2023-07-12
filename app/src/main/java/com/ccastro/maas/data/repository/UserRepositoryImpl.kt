package com.ccastro.maas.data.repository

import android.util.Log
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.User
import com.ccastro.maas.domain.repository.UserRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val usersCollectionRef: CollectionReference) : UserRepository {

    override suspend fun create(user: User): Response<Boolean> {

        return try {
            usersCollectionRef.document(user.id).set(user).await()
            Response.Success(true)
        }catch (e: Exception){
            Log.e("Error UserResp", ""+e.printStackTrace())
            return Response.Fail(e)
        }

    }

    override fun getUserById(id: String): Flow<User> = callbackFlow{
        val snapshotListener = usersCollectionRef.document(id).addSnapshotListener{
            snapshop, e ->
            val user = snapshop?.toObject(User::class.java) ?: User()
            trySend(user)
        }

        awaitClose {
            snapshotListener.remove()
        }
    }
}