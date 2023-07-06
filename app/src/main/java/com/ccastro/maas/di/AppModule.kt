package com.ccastro.maas.di

import android.content.Context
import androidx.room.Room
import com.ccastro.maas.data.Mapper.UserCardDAO
import com.ccastro.maas.data.datasource.AuthInterceptor
import com.ccastro.maas.data.datasource.LocalDataSource
import com.ccastro.maas.data.datasource.RestDataSource
import com.ccastro.maas.data.datasource.RestTripDataSource
import com.ccastro.maas.data.repository.AuthRepositoryImpl
import com.ccastro.maas.data.repository.TripPlanerRepositoryImp
import com.ccastro.maas.data.repository.UserCardRepositoryImpl
import com.ccastro.maas.domain.repository.AuthRepository
import com.ccastro.maas.domain.repository.TripPlanerRepository
import com.ccastro.maas.domain.repository.UserCardRepository
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import com.ccastro.maas.domain.use_cases.auth.GetCurrentUser
import com.ccastro.maas.domain.use_cases.auth.Login
import com.ccastro.maas.domain.use_cases.auth.Logout
import com.ccastro.maas.domain.use_cases.auth.SingUp
import com.ccastro.maas.domain.use_cases.stopPlaces.GetNearStopPlaces
import com.ccastro.maas.domain.use_cases.stopPlaces.StopPlacesUseCases
import com.ccastro.maas.domain.use_cases.userCard.SaveCard
import com.ccastro.maas.domain.use_cases.userCard.UserCardUseCases
import com.ccastro.maas.domain.use_cases.userCard.AddUserCard
import com.ccastro.maas.domain.use_cases.userCard.DeleteCard
import com.ccastro.maas.domain.use_cases.userCard.GetAllCards
import com.ccastro.maas.domain.use_cases.userCard.TotalUserCards
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //  RETROFIT: API CARD_TULLAVE DEPENDENCIES
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
    @Named("tullave")
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

    // Instancia de retrofit
    @Provides
    fun restDataSource(@Named("tullave") retrofit: Retrofit): RestDataSource =
        retrofit.create(RestDataSource::class.java)


    //  RETROFIT: API TIPPLANNER DEPENDENCIES
    @Singleton
    @Provides
    @Named("provideBaseUrlTripPlaner")
    fun provideBaseUrlTripPlaner() = "http://sisuotp.tullaveplus.gov.co/otp/routers/default/index/stops"


    @Singleton
    @Provides
    @Named("RetrofitTripPlaner")
    fun provideRetrofitTripPlaner(@Named("provideBaseUrlTripPlaner") baseUrl: String): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient()
                    .newBuilder()
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Instancia de retrofit
    @Provides
    @Named("restDataSourceTrip")
    fun restDataSourceTrip(@Named("RetrofitTripPlaner") retrofit: Retrofit): RestTripDataSource =
        retrofit.create(RestTripDataSource::class.java)



    // FIREBASE AUTHENTICATION DEPENDENCIES
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


    //  ROOM: LOCAL DATA SOURCE DEPENDENCIES

    @Singleton
    @Provides
    fun provideLocalDB(@ApplicationContext context: Context) : LocalDataSource {
        return Room.databaseBuilder(context, LocalDataSource::class.java, "mass_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun userCardDao(db: LocalDataSource): UserCardDAO = db.userCardDao()


    //  APLICATION DEPENDENCIES

    @Provides
    fun provideUserCardRepository(impl: UserCardRepositoryImpl): UserCardRepository = impl

    @Provides
    fun provideUserCardUseCases(repository: UserCardRepository) = UserCardUseCases(
        addUserCard = AddUserCard(repository),
        saveCard = SaveCard(repository),
        getAllCard = GetAllCards(repository),
        deleteCard = DeleteCard(repository),
        totalUserCards = TotalUserCards(repository)
    )

    @Provides
    fun provideTripRepository(impl: TripPlanerRepositoryImp): TripPlanerRepository = impl

    @Provides
    fun provideStopPlacesUseCases(repository: TripPlanerRepository) = StopPlacesUseCases(
        getNearStopPlaces = GetNearStopPlaces(repository)
    )
}

