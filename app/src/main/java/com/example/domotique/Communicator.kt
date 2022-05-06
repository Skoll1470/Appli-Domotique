package com.example.domotique

import androidx.lifecycle.ViewModel

class Communicator : ViewModel(){

    var ip: String? = null
    var port: Int? = null

    fun registerIP(ip: String, port: Int?) {
        this.ip = ip
        this.port = port
    }

}