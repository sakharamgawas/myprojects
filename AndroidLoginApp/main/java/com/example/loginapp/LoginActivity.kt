package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login3.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    val Password_Pattern = Pattern.compile(
        "^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#%^&+=])" +
                "(?=\\S+$)" +
                ".{5,}" +
                "$"
    )

    //    val Password_Pattern = Pattern.compile("hellman")
    private lateinit var etusername: EditText
    private lateinit var etpassword: EditText
    private lateinit var etbutton: Button


    private fun validateUsername(): Boolean {
        val username = etusername.text.toString().trim()
        if (username.isEmpty()) {
            etusername.setError("Please enter username")
            return false
        } else {
            etusername.setError(null)
            return true
        }
    }

    private fun validatePassword(): Boolean {
        val password = etpassword.text.toString().trim()
        if (password.isEmpty()) {
            etpassword.setError("Please enter password")
            return false;
        } else if (!Password_Pattern.matcher(password).matches()) {
            etpassword.setError("Password not meeting the criteria")
            return false;
        } else {
            etpassword.setError(null)
            return true
        }
    }


    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login3)
        etusername = findViewById(R.id.username)
        etpassword = findViewById(R.id.password)
        etbutton = findViewById(R.id.loginbtn)
        etbutton.isEnabled = false

        etusername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val username = etusername.text.toString().trim()
                val password = etpassword.text.toString().trim()
                if (!username.isEmpty() && !password.isEmpty()) {
                    etbutton.isEnabled = true
                } else {
                    etbutton.isEnabled = false
                }
            }


        })

        etpassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = etpassword.text.toString().trim()
                val username = etusername.text.toString().trim()
                if (!username.isEmpty() && !password.isEmpty()) {
                    etbutton.isEnabled = true
                } else {
                    etbutton.isEnabled = false
                }
            }

        })


        loginbtn.setOnClickListener()
        {

            validatePassword()
            validateUsername()
            if (!validatePassword() || !validateUsername()) {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login successfull", Toast.LENGTH_SHORT).show()
                handler.postDelayed({
                    startActivity(Intent(this, HomeActivity::class.java).putExtra("username",etusername.text.toString().trim()))
                }, 1500L)
            }

        }


    }


}
