package ar.edu.unicen.seminario.old.Clases1a12.ddl

import android.content.SharedPreferences
import ar.edu.unicen.seminario.old.Clases1a12.di.CounterSharedPreference
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
@ViewModelScoped
class CounterLocalDataSource @Inject constructor (
    @CounterSharedPreference
    private val sharedPreferences: SharedPreferences
) {

    suspend fun getCounter(): Int {
        return  withContext(Dispatchers.IO) {
             sharedPreferences.getInt("counter", 0)
        }


    }
    /*Como Shared preferences esta preparado para manejar datos simples -> si no encuentra un valor con esta clave en un objeto Persona por ejemplo, 2 alternativas:
    1)guardar cada atributo por separado
    2) guardar el objeto como un json y devolverlo como una cadena de string
    *  */

    suspend fun setCounter(value: Int): Boolean {
        /*devuelve un booleano de si el dato se pudo guardar o no, por eso usamos try catch*/
      return  withContext(Dispatchers.IO) {
            try {
               delay(1000)
                val editor = sharedPreferences.edit()
                editor.putInt("counter", value)
                 editor.commit()
                /*val result = editor.commit()
                editor.apply()*/
            }
            catch(e: Exception) {
                e.printStackTrace()
                 false
            }
        }

    }

    suspend fun reset(): Boolean {
        return  withContext(Dispatchers.IO) {
            try {
                val editor= sharedPreferences.edit()
                editor.clear() /*remove cuando unamos un solo sharedPreference para todos los repo*/
                 editor.commit()
            }
            catch (e: Exception) {
                e.printStackTrace()
                 false
            }

        }


    }


}

/*SharedPreference no soporta las corutinas (no tiene metodos suspend), por eso usamos funciones suspends. Para poder
* invocar una funcion suspend, tiene que haber una corutina u otra funcion suspend.
* Utilizar suspend ya deja preparado el dataSource para cuando se migre de SharedPreference a DataStore (nueva tecnologia)
 */