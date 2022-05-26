package com.example.domotique

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.fragment_sound.*
import kotlinx.android.synthetic.main.windows_fragment.*

class WindowsFragment: Fragment() {

    val jason = JsonHandlers()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.windows_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = ViewModelProvider(requireActivity()).get(Communicator::class.java)

        var adapter: WindowAdapter?

        swipeRefreshWindow.setOnRefreshListener {
            val httpAsync = "http://${model.ip}:${model.port}/api/request_windows"
                .httpGet()
                .responseJson { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            println(ex)
                        }
                        is Result.Success -> {
                            val data = result.get()
                            val donnees = data.array()
                            val windows = mutableListOf<Window>()

                            for (i in 0 until donnees.length()) {
                                println("[Données reçues] : ${donnees.getJSONObject(i)} ")
                                val donnee = donnees.getJSONObject(i)
                                windows.add(
                                    Window(
                                        donnee.getString("id"),
                                        donnee.getBoolean("open")
                                    )
                                )
                            }
                            adapter = WindowAdapter(windows)
                            rvWindows.adapter = adapter
                            rvWindows.layoutManager = LinearLayoutManager(activity)
                            swipeRefreshSound.isRefreshing = false
                        }
                    }
                }
            httpAsync.join()
        }

        val httpAsync = "http://${model.ip}:${model.port}/api/request_windows"
            .httpGet()
            .responseJson { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }
                    is Result.Success -> {
                        val data = result.get()
                        val donnees = data.array()
                        val windows = mutableListOf<Window>()

                        for (i in 0 until donnees.length()) {
                            println("[Données reçues] : ${donnees.getJSONObject(i)} ")
                            val donnee = donnees.getJSONObject(i)
                            windows.add(
                                Window(
                                    donnee.getString("id"),
                                    donnee.getBoolean("open")
                                )
                            )
                        }
                        adapter = WindowAdapter(windows)
                        rvWindows.adapter = adapter
                        rvWindows.layoutManager = LinearLayoutManager(activity)

                    }
                }
            }
        httpAsync.join()
    }

}