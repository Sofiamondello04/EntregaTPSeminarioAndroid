/*package ar.edu.unicen.seminario.BoredActivity.dl

import ar.edu.unicen.seminario.BuildConfig
import ar.edu.unicen.seminario.BoredActivity.ddl.data.BoredApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class BoredModule {

    Dejo comentado todo para que no genere conflicto con la clase UserModule

   @Provides

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            /*dejo la url harcodeada para seguir con la clase de user*/
            .baseUrl("https:// bored. api. lewagon. com/ api/")
            /*.baseUrl(BuildConfig.BASE_URL)*/
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun provideBoredApi(
        retrofit: Retrofit
    ): BoredApi {
        return retrofit.create(BoredApi::class.java)
    }


}*/