package com.example.loginpage

import androidx.appcompat.app.AppCompatActivity

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task

class Homepage : AppCompatActivity() {

    internal lateinit var imageView: ImageView
    internal lateinit var name: TextView
    internal lateinit var email: TextView
    internal lateinit var id: TextView
    internal lateinit var signOut: Button
    internal lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        signOut = findViewById(R.id.signout)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        signOut.setOnClickListener { v ->
            when (v.id) {
                R.id.signout -> signOut()
            }
        }

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email

            name.text = personName
            email.text = personEmail

        }
    }


    private fun signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this) {

                    Toast.makeText(this@Homepage, "Successfully signed out", Toast.LENGTH_LONG).show()

                    finish()
                }
    }
}
