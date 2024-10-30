package ar.edu.unicen.seminario.old.Clases1a12

/*definir pantalla en el manifest, sino Android no la conoce*/
/*class RedActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigationButton.setOnClickListener {
            val intent = Intent(this, BlueActivity::class.java)
            startActivity(intent)
        }

       /* val parameter: Int = intent.getIntExtra("counter", 0)

        binding.parameterText.text =parameter.toString()*/

        val parameter: Counter? = intent.getParcelableExtra<Counter>("counter")
        val parameterText: Int = parameter?.getValue() ?: 0
        binding.parameterText.text =parameter.toString()



   }
}*/