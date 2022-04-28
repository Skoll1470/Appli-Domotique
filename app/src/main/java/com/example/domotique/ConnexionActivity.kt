package com.example.domotique

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConnexionActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.connexion)

        val validate = findViewById<Button>(R.id.button)

        validate.setOnClickListener {
            val serverIP = findViewById<EditText>(R.id.serverIP).text.toString()
            val serverPort = findViewById<EditText>(R.id.serverPort).text.toString()

            if(serverIP.isEmpty()  ||  serverPort.isEmpty()) {
                Toast.makeText(this@ConnexionActivity,"Merci de renseigner une IP et un port valides", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent()
                intent.putExtra("serverIP", serverIP)
                intent.putExtra("serverPort", serverPort)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}