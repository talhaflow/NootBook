package com.talhakara.notebook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NotOkuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragmentin görsel düzeni için layout dosyasını şişirerek kullanın
        return inflater.inflate(R.layout.fragment_not_oku, container, false)
    }

    // Diğer yaşam döngüsü metotları ve özelleştirilmiş metotları ekleyebilirsiniz
}
