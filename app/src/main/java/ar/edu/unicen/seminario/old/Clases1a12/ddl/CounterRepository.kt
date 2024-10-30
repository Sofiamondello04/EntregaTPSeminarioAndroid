package ar.edu.unicen.seminario.old.Clases1a12.ddl

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
@ViewModelScoped
class CounterRepository @Inject constructor (
    private val counterLocalDataSource: CounterLocalDataSource
){

    suspend fun getCounter(): Int {
        return counterLocalDataSource.getCounter()
    }

    suspend fun setCounter(value:Int): Boolean {
        return counterLocalDataSource.setCounter(value)
    }

    suspend fun reset(): Boolean {
        return counterLocalDataSource.reset()
    }
}