package com.talhakara.notebook

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

import com.google.firebase.database.*



class AnaSayfaFragment : Fragment() {
    var database = FirebaseDatabase.getInstance().reference

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnaSayfaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ana_sayfa, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewAnaSayfa)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Firestore'dan veriyi indir
        val notlarListesi = mutableListOf<AnaSayfaBilgi>()

        val notlarRef = database.child("notlar")
        notlarRef.get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    for (noteSnapshot in snapshot.children) {
                        val baslik = noteSnapshot.child("baslik").getValue(String::class.java)
                        val metin = noteSnapshot.child("metin").getValue(String::class.java)

                        if (baslik != null && metin != null) {
                            val yeniNot = AnaSayfaBilgi(baslik, metin)
                            notlarListesi.add(yeniNot)
                        }
                    }

                    // Firestore'dan verileri aldıktan sonra, adapter ile RecyclerView'a verileri bağlayın.
                    adapter = AnaSayfaAdapter(notlarListesi)
                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(requireContext(), "Veri bulunamadı.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Veri indirme hatası: $it", Toast.LENGTH_SHORT).show()
            }

        return view
    }
}


