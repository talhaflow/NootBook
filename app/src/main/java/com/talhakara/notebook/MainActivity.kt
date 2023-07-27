package com.talhakara.notebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)




        // Load AnaSayfaFragment when the MainActivity is first created
        if (savedInstanceState == null) {
            val anaSayfaFragment = AnaSayfaFragment() // Replace AnaSayfaFragment with the actual fragment class name
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, anaSayfaFragment)
                .commit()
        }
    }

    fun showMenu(view: View) {
        val fragmentTag = "NotEklemeFragment" // Fragment'ın etiketi (tag) olarak kullanılacak

        val currentFragment = supportFragmentManager.findFragmentByTag(fragmentTag)

        if (currentFragment == null) {
            // Eğer NotEklemeFragment henüz eklenmemişse, fragmentı ekle
            val notEklemeFragment = NotEklemeFragment() // NotEklemeFragment'ı oluşturun (varsayılan isimle değiştirin)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, notEklemeFragment, fragmentTag) // Fragmentı fragment_container'a ekleyin (id'sini uygun şekilde değiştirin)
                .commit()

            // FloatingActionButton'ı gizle
            view.visibility = View.INVISIBLE
        } else {
            // Eğer NotEklemeFragment zaten eklenmişse, fragmentı kaldır
            supportFragmentManager.beginTransaction()
                .remove(currentFragment)
                .commit()

            // FloatingActionButton'ı tekrar görünür yap
            view.visibility = View.VISIBLE
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val notEklemeFragment = NotEklemeFragment() // Replace NotEklemeFragment with the actual fragment class name
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, notEklemeFragment)
                    .commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

