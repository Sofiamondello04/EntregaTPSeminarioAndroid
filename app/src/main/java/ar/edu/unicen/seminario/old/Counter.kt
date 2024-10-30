package ar.edu.unicen.seminario.old

/*implementa interfaz parcelable- la alternativa es implementar el plugin y funciona solo cuando en
* el constructor recibe solo atributos y SOLO salva los atributos que estan definidos en la clase,
* no los definidos dentro de la implementacion*/
/*@Parcelize
/*esta anotacion implementa automaticamente los metodos que se necesitan para que nuestra clase sea parcelable*/
class Counter(
    private var valor: Int = 0
): Parcelable{
    fun increment () {
        valor++
    }

    override fun toString(): String {
        return valor.toString()
    }

    fun getValue(): Int {
        return valor
    }

    fun add(value: Int) {
        valor += value
    }


}*/