package com.example.appteca

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        // Viaja el id, no el objeto: el destino busca el dato en la fuente compartida
        val appId = intent.getIntExtra("appId", -1)
        val app = Catalogo.apps.find { it.id == appId }
        if (app == null) { finish(); return }   // plan B: sin dato válido, se cierra

        findViewById<TextView>(R.id.tvDetNombre).text = app.nombre
        findViewById<TextView>(R.id.tvDetCategoria).text = app.categoria
        findViewById<TextView>(R.id.tvDetDescripcion).text = app.descripcion

        val btn = findViewById<Button>(R.id.btnFavorito)
        fun pintar() {
            btn.text = if (app.esFavorita) "★ Quitar de favoritas" else "☆ Marcar favorita"
        }
        pintar()
        btn.setOnClickListener { app.esFavorita = !app.esFavorita; pintar() }
    }
}