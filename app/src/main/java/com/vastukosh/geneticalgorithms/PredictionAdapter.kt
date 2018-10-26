package com.vastukosh.geneticalgorithms

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class PredictionAdapter(val context: Context, val prediction: List<Predictions>): RecyclerView.Adapter<PredictionAdapter.Holder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_prediction, p0, false)
        return Holder(view)
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bindAd(prediction[p1], context)
    }

    override fun getItemCount(): Int {
        return prediction.count()
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val singlePrediction = itemView?.findViewById<TextView>(R.id.prediction_text_view)

        fun bindAd(predictions: Predictions, context: Context) {
            singlePrediction?.text = predictions.prediction
        }
    }
}