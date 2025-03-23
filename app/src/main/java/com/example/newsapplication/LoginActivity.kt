package com.example.newsapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapplication.viewmodel.AuthViewModel


class LoginActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var checkBoxSavePassword: CheckBox
    private lateinit var loginButton: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registerButton = findViewById<Button>(R.id.buttonRegister)
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        checkBoxSavePassword = findViewById(R.id.checkBoxSavePassword)
        loginButton = findViewById(R.id.buttonLogin)

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        // Загрузка сохраненных данных
        loadSavedData()

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Если галочка установлена, сохраняем данные
            if (checkBoxSavePassword.isChecked) {
                saveUserData(email, password)
            } else {
                clearUserData()
            }

            if (email.isNotEmpty() && password.isNotEmpty()) {
                authViewModel.login(email, password)
            } else {
                Toast.makeText(this, "Введите email и пароль", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        authViewModel.authResponse.observe(this) { response ->
            if (response.isSuccessful && response.body()?.status == "success") {
                Toast.makeText(this, "Вход выполнен успешно", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, response.body()?.message ?: "Ошибка входа", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun loadSavedData() {
        val savedUsername = sharedPreferences.getString("email", "")
        val savedPassword = sharedPreferences.getString("password", "")
        emailEditText.setText(savedUsername)
        passwordEditText.setText(savedPassword)
        checkBoxSavePassword.isChecked = savedUsername?.isNotEmpty() == true
    }

    private fun saveUserData(username: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("email", username)
        editor.putString("password", password)
        editor.apply()
    }

    private fun clearUserData() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
