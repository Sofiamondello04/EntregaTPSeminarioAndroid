package ar.edu.unicen.seminario.di

import ar.edu.unicen.seminario.BuildConfig
import ar.edu.unicen.seminario.ddl.ApiKeyInterceptor
import ar.edu.unicen.seminario.ddl.LanguageInterceptor

import ar.edu.unicen.seminario.ddl.data.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class MovieModule {

    // agregado recien
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(LanguageInterceptor())
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }


    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun provideUserApi(
        retrofit: Retrofit
    ): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }


}