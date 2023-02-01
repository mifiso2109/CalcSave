package com.example.pr7

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a2 = findViewById<EditText>(R.id.A) // привязываем эелемент textView к переменной
        val b2 = findViewById<EditText>(R.id.B)

        val pref = getPreferences(Context.MODE_PRIVATE) // getPref - используется чтоб обратиться к определенному предпочтению, основа для проведения сохр
        val editor = pref.edit() // параментр edit позволяет вносит изменения

        val A = pref.getInt("a", 0) // переменной даем идентификатор(a) и начальное значение (0)
        val B = pref.getInt("b", 0)
        val res = pref.getString("res", "0")

        a2.setText(A.toString()) // и выводим это все в нашем editText и textView
        b2.setText(B.toString())
        Answer.text = res

        onSave.setOnClickListener {
            editor.putInt("a", a2.text.toString().toInt()) // тут мы уже вствляем значения введенные в editText(a2) и присваеваем выше сказанный идентификатор,
            // тем самым связываем изменения (a)
            editor.putInt("b", b2.text.toString().toInt())
            editor.putString("res", Answer.text.toString())
            editor.apply() // применяем настройки
        }


        onClear.setOnClickListener {
            editor.clear() // очистка(опциональное)
            editor.apply()
            a2.setText("0")
            b2.setText("0")
            Answer.text = "0"
        }

        onCount.setOnClickListener {
            val a: Double = a2.text.toString().toDouble() // конвертируем значение в вещественный тип
            val b: Double = b2.text.toString().toDouble()
            if(a == 0.0 || b == 0.0) Answer.text = "Нельзя вводить 0" // проверка
            else{
                val x = -b/a
                if(a > 0) Answer.text = "${x};+∞"
                if(a < 0) Answer.text = "-∞; ${x}"
            }
        }
    }
}
