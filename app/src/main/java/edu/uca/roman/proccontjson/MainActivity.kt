package edu.uca.roman.proccontjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import edu.uca.roman.proccontjson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val nombres = findViewById(R.id.etNombres) as EditText
        val apellidos = findViewById(R.id.etApellidos) as EditText
        val fechaNac = findViewById(R.id.etfechaNac) as EditText
        val titulo = findViewById(R.id.etTitulo) as EditText
        val email = findViewById(R.id.etEmail) as EditText
        val facultad = findViewById(R.id.etFacultad) as EditText
        //val btnGuardar = findViewById(R.id.btnGuardar) as Button

        binding.btnGuardar.setOnClickListener{

            var url: String = "http://192.168.1.20:80/ingresarcoordinador.php"; "nombres=" +
                    nombres.text.toString() + "&apellidos=" + apellidos.text.toString() +
                    "&fechaNac=" + fechaNac.text.toString() + "&titulo=" + titulo.text.toString() +
                    "&email=" + email.text.toString() + "&facultad=" + facultad.text.toString()

            var rq: RequestQueue = Volley.newRequestQueue(this)

            var jos = JsonObjectRequest(Request.Method.POST,url, null, { response ->
                Toast.makeText(this, "Coordinador Añadido", Toast.LENGTH_LONG).show()
            }, { error ->
            })
            rq.add(jos)

        }

    }

}







