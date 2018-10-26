package com.vastukosh.geneticalgorithms

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun fetchGeneticPrediction(view: View) {

        if(!input_text_field.text.isEmpty()) {

            val listPredictions = arrayListOf<Predictions>()
            var adapter: PredictionAdapter

            val url = "https://genetic-prediction.herokuapp.com/?s=" + input_text_field.text.toString()
            Toast.makeText(this, "Generating your text...", Toast.LENGTH_LONG).show()
            val loginRequest = object: JsonObjectRequest(Method.GET, url, null, Response.Listener { response ->
                try {

                    val predictionArr = response.getJSONArray("predictions")

                    var i = 0
                    while(i < predictionArr.length()) {
                        listPredictions.add(Predictions(predictionArr.get(i).toString(), i+1))
                        i++
                    }

                    adapter = PredictionAdapter(this, listPredictions)
                    genetic_results_recycler_view.adapter = adapter

                    val layoutManager = LinearLayoutManager(this)
                    genetic_results_recycler_view.layoutManager = layoutManager
                    genetic_results_recycler_view.setHasFixedSize(true)


                } catch (e: JSONException) {
                    Log.d("JSON", "EXC: " + e.localizedMessage)
                }
            }, Response.ErrorListener { error ->
                Log.d("ERROR", "Could not login user: $error")
            }) {

                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }
            }
            Volley.newRequestQueue(this).add(loginRequest)

        } else {
            Toast.makeText(this, "Complete the form!", Toast.LENGTH_LONG).show()
        }

    }

}
