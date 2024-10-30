package ar.edu.unicen.seminario.old.Clases1a12

/*definir pantalla en el manifest, sino Android no la conoce*/

/*class BlueActivity: AppCompatActivity() {


    private lateinit var binding: ActivityBlueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBlueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigationButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            /*Si el destino al cual queremos ir ya forma parte de nuestra navegacion, se apila arriba y no hay modificacion del comportamiento.
            * por eso combinar con el SINGLE_TOP para no reemplazar el MAinActivity por uno nuevo y perder estado*/
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

            intent.putExtra("add_counter", 10)

            startActivity(intent)
        }
    }

}*/