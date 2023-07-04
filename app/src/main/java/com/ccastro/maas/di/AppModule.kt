package com.ccastro.maas.di

import com.ccastro.maas.data.repository.AuthRepositoryImpl
import com.ccastro.maas.domain.repository.AuthRepository
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import com.ccastro.maas.domain.use_cases.auth.GetCurrentUser
import com.ccastro.maas.domain.use_cases.auth.Login
import com.ccastro.maas.domain.use_cases.auth.Logout
import com.ccastro.maas.domain.use_cases.auth.SingUp
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

}