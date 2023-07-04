package com.ccastro.maas.di

import com.ccastro.maas.data.datasource.RoomLocalDB
import com.ccastro.maas.data.repository.AuthRepositoryImpl
import com.ccastro.maas.data.repository.UserCardRepositoryImpl
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.repository.AuthRepository
import com.ccastro.maas.domain.repository.UserCardRepository
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import com.ccastro.maas.domain.use_cases.auth.GetCurrentUser
import com.ccastro.maas.domain.use_cases.auth.Login
import com.ccastro.maas.domain.use_cases.auth.Logout
import com.ccastro.maas.domain.use_cases.auth.SingUp
import com.ccastro.maas.domain.use_cases.userCard.ReadCardByField
import com.ccastro.maas.domain.use_cases.userCard.ReadCardById
import com.ccastro.maas.domain.use_cases.userCard.SaveCard
import com.ccastro.maas.domain.use_cases.userCard.UserCardUseCases
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        singUp = SingUp(repository)
    )

    @Provides
    fun provideRoomLocalDB() = RoomLocalDB<UserCard>()

    @Provides
    fun provideUserCardRepository(impl: UserCardRepositoryImpl): UserCardRepository = impl

    @Provides
    fun provideUserCardUseCases(repository: UserCardRepository) = UserCardUseCases(
        saveCard = SaveCard(repository),
        readCardById = ReadCardById(repository),
        readCardByField = ReadCardByField(repository)
    )
}