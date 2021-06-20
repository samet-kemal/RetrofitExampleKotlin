package com.samet.retrofitexamplekotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samet.retrofitexamplekotlin.R
import com.samet.retrofitexamplekotlin.model.CryptoModel
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(
    private val cryptolist: ArrayList<CryptoModel>,
    private val listener: RowHolder.Listener
) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    private val colors: Array<String> = arrayOf(
        "#549ae7",
        "#8b77ab",
        "#80b697",
        "#b680a3",
        "#b6b480",
        "#d87742",
        "#425bd8",
        "#4fd842"
    )

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        interface Listener {
            fun onItemClick(cryptoModel: CryptoModel)
        }

        fun bind(
            cryptoModel: CryptoModel,
            colors: Array<String>,
            position: Int,
            listener: Listener
        ) {
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.text_name.text = cryptoModel.currency
            itemView.text_price.text = cryptoModel.price
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.bind(cryptolist[position], colors, position, listener)
    }

    override fun getItemCount(): Int {
        return cryptolist.size
    }
}