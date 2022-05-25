package com.example.domotique

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private var ip: String? = null
    private var port: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel: Communicator = ViewModelProvider(this).get(Communicator::class.java)


        val connectFragment = ConnectFragment()
        val soundFragment = SoundFragment()
        val windowFragment = WindowsFragment()
        val doorFragment = DoorFragment()
        val lightbulbsFragment = LightbulbsFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, connectFragment)
            commit()
        }

        navView.setNavigationItemSelectedListener {
            ip = viewModel.ip
            port = viewModel.port
            when(it.itemId){
                R.id.miDoors -> {
                    if(ip != null && port != null) {

                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.fragment, doorFragment)
                            commit()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Please enter a valid IP and port", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.miSound -> {
                    if(ip != null && port != null) {

                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.fragment, soundFragment)
                            commit()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Please enter a valid IP and port", Toast.LENGTH_LONG).show()
                    }
                }

                R.id.miWindows -> {
                    if(ip != null && port != null) {

                        //Toast.makeText(applicationContext, "Clicked Windows", Toast.LENGTH_LONG).show()
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.fragment, windowFragment)
                            commit()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Please enter a valid IP and port", Toast.LENGTH_LONG).show()
                    }
                }
                R.id.miLightbulbs -> {
                    if(ip != null && port != null) {

                        //Toast.makeText(applicationContext, "Clicked Windows", Toast.LENGTH_LONG).show()
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.fragment, lightbulbsFragment)
                            commit()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Please enter a valid IP and port", Toast.LENGTH_LONG).show()
                    }
                }
            }
            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}