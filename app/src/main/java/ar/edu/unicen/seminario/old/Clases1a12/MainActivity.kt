package ar.edu.unicen.seminario.old.Clases1a12

/*class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var counter: Counter = Counter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clickMeButton.setOnClickListener {
            counter.increment()
            binding.helloWorldTextView.text = counter.toString()
        }

        /*enviar como parametro el contador*/
        binding.navigateButton.setOnClickListener {
            val intent = Intent(this, RedActivity::class.java)
            intent.putExtra("counter", counter)
            startActivity(intent)

        }
    }
        /*se ejecuta cada vez que alguien le manda un nuevo mensaje al activity distinto del de creacion*/
        override fun onNewIntent(intent: Intent?) {
            super.onNewIntent(intent)
            val toAdd:Int? = intent?.getIntExtra("add_counter", 0)
            if (toAdd !=null) {
                counter.add(toAdd)
                binding.helloWorldTextView.text = counter.toString()
            }

        }

       /* Lo oculto para que no tarde tanto en buildear*/

        /*enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/



    /*Como cambiar un color o un texto de un layout desde el codigo: accediente al context de mi activity
    *
    * ejemplo:
    * val color: int = this.getColor(R.color.black)
    * val texto: String = this.getString(R.string.hello_world)
    **/

    /*Cuando el ciclo de vida termina, el context tambien. El context a veces se pasa por parametro a objetos
    *El riesgo aca es que el contexto viva cuando el activity ya murió.
     */

    /*La aplicacion tambien tiene un context, cuyo ciclo de vida es mas duradera que el context del activity
    *y por ello es menos riesgoso usarlo
     */





    /*loggear los estados sirven para guardarnos estados
    * Dato Parcelable: un dato se puede comunicar de un proceso a otro, implementar esa interfaz
    * en caso de querer guardar objetos mas complejos.
    * Es la forma de evitar perder el progreso mediante una instancia de tipo Bundle*/

    /*Metodo que vamos a usar cuando queremos guardar variables mas primarias*/

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("Main Activity", "onSaveInstanceState")
      /*  outState.putInt("counter", counter) CUANDO QUIERO GUARDAR UNA VARIABLE SIMPLE*/
        outState.putParcelable("counter", counter)
    }


   /*Solo se invoca si el onSave guardó datos*/
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("Main Activity", "onRestoreInstanceState")
        /*counter = savedInstanceState.getInt("counter")*/ /*cuando no uso parcelable*/
       val savedCounter: Counter? = savedInstanceState.getParcelable<Counter>("counter")
       if(savedCounter!=null) {
           counter = savedCounter
           binding.helloWorldTextView.text =counter.toString()
       }


    }



    override fun onStart() {
        super.onStart()
        Log.i("Main Activity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Main Activity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Main Activity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Main Activity", "onStart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Main Activity", "onDestroy")
    }
}*/