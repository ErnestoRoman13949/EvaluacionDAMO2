package edu.uca.roman.proccontjson

import android.content.Context
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import edu.uca.roman.proccontjson.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val conexion = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val status = conexion.activeNetworkInfo

        binding.btnGuardar.setOnClickListener() {

            if (status != null && status.isConnected) {
                ejecutarDatos()
            } else {
                Toast.makeText(this, "Revise su conexion a internet", Toast.LENGTH_LONG).show()
            }
        }
    }
}
    private fun ejecutarDatos() {
        val nombres: String = etNombres?.text.toString()
        val apellidos: String = etApellidos?.text.toString()
        val fechaNac: String = etfechaNac?.text.toString()
        val titulo: String = etTitulo?.text.toString()
        val email: String = etEmail?.text.toString()
        val facultad: String = etFacultad?.text.toString()

        val url = "http://192.168.1.20/ProcContJSON/ingresarcoordinador.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)
                    Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("Nombres", nombres)
                params.put("Apellidos", apellidos)
                params.put("fechaNac", fechaNac)
                params.put("Titulo", titulo)
                params.put("Email", email)
                params.put("Facultad", facultad)
                return params
            }
        }

        VolleySingleton.instance?.addToRequestQueue(stringRequest)
    }






