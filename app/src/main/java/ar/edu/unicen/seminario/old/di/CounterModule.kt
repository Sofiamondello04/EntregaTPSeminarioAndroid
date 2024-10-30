package ar.edu.unicen.seminario.old.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

/*aca van las inyecciones de dependencias tranversales a la app
* Qualifiers: nombra los metodos para identificar, en caso de tener por ej muchos
* sharedPreference*/

@Module
@InstallIn(ViewModelComponent::class)
class CounterModule {

    @Provides
    @CounterSharedPreference
    @ViewModelScoped /*asegura que para cada view se va a crear una sola instancia de sp*/

    fun provideSharedPreferences(
        @ApplicationContext
        context: Context
    ): SharedPreferences{
        return context.getSharedPreferences("counter", Context.MODE_PRIVATE)
    }






}