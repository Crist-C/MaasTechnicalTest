package com.ccastro.maas.di

import com.ccastro.maas.data.datasource.AuthInterceptor
import com.ccastro.maas.data.datasource.RestDataSource
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
import com.ccastro.maas.domain.use_cases.userCard.AddUserCard
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Retrofit: API CardTullave
    @Singleton
    @Provides
    @Named("provideBaseUrl")
    fun provideBaseUrl() = "https://osgqhdx2wf.execute-api.us-west-2.amazonaws.com"

    @Provides
    @Named("provideToken")
    fun provideToken(): String{
     return "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtYWFzIiwiaXNzIjoicmJzYXMuY28iLCJjb21wYW55IjoiMTAwMiIsImV4cCI6MTY4OTcxODg5NywidXNlciI6ImNyaXN0aWFuLmNhc3RybyIsImlhdCI6MTY4Nzk5MDg5NywiR3J1cG9zIjoiW1wiVW5pdmVyc2FsUmVjaGFyZ2VyXCJdIn0.VzLwke3C5vVEfZYe-NKu8cCdheptl9-XgtjbC5gc9hprfFETXkDA9XKhjJiy4ItqVBu_TutVwpGkhldQpulwqmcX6jcrv-BAXrh8KZ4NSXtk5Zq2avms7707MUcjQDlZSqimutDdkQHSEWMcnGtYeqjr0T8VR4mN-dnUVEjL9-DQCivg3UhadNl11G8dYSO1MEU1WOlrsCx-7Bi1RCs4V3n7LpWRY4_5mv39jheIhdXnfjU287YqZGuNLrLWARNIkQ6T7SKAdr7cRpXdKkeoZwNIPbL5dxTFG9ai0Q_QANACpTNxHXIxCkLwFX9l4gOz1GPHoM-ek7LSFnqZKN2zxg"
    }

    @Provides
    @Named("provideAuthInterceptor")
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor(authToken = provideToken())
    }


    @Singleton
    @Provides
    fun provideRetrofit(@Named("provideBaseUrl") baseUrl: String,
                        @Named("provideAuthInterceptor") authInterceptor: AuthInterceptor): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient()
                    .newBuilder()
                    .addInterceptor(authInterceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    fun restDataSource(retrofit: Retrofit): RestDataSource =
        retrofit.create(RestDataSource::class.java)

    // FIREBASE AUTHENTICATION
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
        readCardByField = ReadCardByField(repository),
        addUserCard = AddUserCard(repository)
    )
}

