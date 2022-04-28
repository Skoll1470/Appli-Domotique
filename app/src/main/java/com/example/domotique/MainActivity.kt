package com.example.domotique

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.domotique.databinding.ActivityMainBinding
import com.github.kittinunf.fuel.Fuel
import org.json.*
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result;
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    var serverIP: String? = null
    var serverPort: String? = null

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, ConnexionActivity::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {
                serverIP = it.data?.getStringExtra("serverIP")
                serverPort = it.data?.getStringExtra("serverPort")

                "http://$serverIP:$serverPort/api/request_infos".httpPost().response { _, response, result ->
                    Toast.makeText(this@MainActivity, response.toString(), Toast.LENGTH_LONG).show()
                    when (result) {
                        is Result.Success -> {
                            Toast.makeText(this@MainActivity, response.toString(), Toast.LENGTH_LONG).show()
                        }
                        is Result.Failure -> {
                            Toast.makeText(this@MainActivity, "http://$serverIP:$serverPort/api/request_infos", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                val json = "{\"volume\":-0.4, \"id\":\"Spotify\", \"muted\":false}"

                Fuel.post("http://$serverIP:$serverPort/api/update_volume ").jsonBody(json).response {
                    result ->
                    when (result) {
                        is Result.Success -> {
                            Toast.makeText(this@MainActivity, result.toString(), Toast.LENGTH_LONG).show()
                        }
                        is Result.Failure -> {
                            Toast.makeText(this@MainActivity, "http://$serverIP:$serverPort/api/request_infos", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        getResult.launch(intent)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}