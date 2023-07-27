package com.talhakara.notebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnaSayfaAdapter(private val dataList: List<AnaSayfaBilgi>) :
    RecyclerView.Adapter<AnaSayfaAdapter.AnaSayfaViewHolder>() {

    inner class AnaSayfaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val baslikTextView: TextView = itemView.findViewById(R.id.rowBaslikTextView)
        val metinTextView: TextView = itemView.findViewById(R.id.rowMetinTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnaSayfaViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.ana_sayfa_row, parent, false)
        return AnaSayfaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnaSayfaViewHolder, position: Int) {
        val data = dataList[position]
        holder.baslikTextView.text = data.baslik
        holder.metinTextView.text = data.metin
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
