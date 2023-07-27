package com.talhakara.notebook


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase



class NotEklemeFragment : Fragment() {
    // Firebase Firestore referansını oluşturun
    var database =FirebaseDatabase.getInstance().reference
    // EditText değişkenlerini sınıf düzeyine taşıyın
    private lateinit var editTextBaslik: EditText
    private lateinit var editTextMetin: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment'ın görünümünü oluşturmak için kullanılan yöntem
        // Layout dosyasını inflate ederek fragmentın View nesnesini döndürün
        return inflater.inflate(R.layout.fragment_not_ekleme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // EditText değişkenlerini burada ilgili view'lara bağlayın
        editTextBaslik = view.findViewById(R.id.ekle_editTextBaslik)
        editTextMetin = view.findViewById(R.id.ekle_editTextMetin)

        // Button'u bulup setOnClickListener ile tıklama olayını yönetin
        val ekleButton = view.findViewById<Button>(R.id.eklebutton)
        ekleButton.setOnClickListener {
            ekle()
        }
    }

    private fun ekle() {

        // Butona tıklandığında yapılacak işlemleri burada ele alalım
        val baslik = editTextBaslik.text.toString()
        val metin = editTextMetin.text.toString()
        editTextBaslik.text.clear()
        editTextMetin.text.clear()

        if (baslik.isEmpty() || metin.isEmpty()) {
            Toast.makeText(requireContext(), "Başlık ve metin giriniz", Toast.LENGTH_SHORT).show()
        } else {
            // Firestore'a veriyi ekle
            val yeniNot = AnaSayfaBilgi(baslik, metin)
            val yeniNotRef = database.child("notlar").push() // Veriyi "notlar" düğümü altına kaydedelim ve benzersiz bir anahtar oluşturalım.
            yeniNotRef.setValue(yeniNot)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Veri başarıyla eklendi.", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Veri ekleme hatası: $it", Toast.LENGTH_SHORT).show()
                }
        }



    }
}
