package com.example.domotique

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.connect_fragment.*

class ConnectFragment : Fragment(R.layout.connect_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProvider(requireActivity()).get(Communicator::class.java)

        if (model.ip != null || model.port != null){
            ipAddress.setText(model.ip)
            val port = model.port!!.toInt()
            etPort.setText(port.toString())
        }

        submitAddr.setOnClickListener {
            val ip = ipAddress.text.toString()
            val port = if (etPort.text.toString().isNotEmpty() && etPort.text.toString().toInt() in 1..25000) {
                etPort.text.toString().toInt()
            } else {
                null
            }

            if (port != null) {

                println("[Data Sent] $ip:$port")
                model.registerIP(ip, port)

                val focusView = activity?.currentFocus
                if (focusView != null) {
                    val imm =
                        activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }

                Toast.makeText(context, "Addresse configurée : $ip:$port", Toast.LENGTH_SHORT).show()

            } else {

                Toast.makeText(context, "Merci d'entrer un port compris entre 0 et 25000", Toast.LENGTH_SHORT).show()
            }
        }
    }
}