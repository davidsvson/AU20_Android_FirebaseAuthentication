package se.iths.au20.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    lateinit var textEmail : EditText
    lateinit var textPassword : EditText
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        textEmail = findViewById(R.id.editTextEmail)
        textPassword = findViewById(R.id.editTextPassword)

        val createButton = findViewById<Button>(R.id.createButton)
        createButton.setOnClickListener {
            createUser()
        }

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            loginUser()
        }

        if(auth.currentUser != null) {
            Log.d("!!!", "Current user: ${auth.currentUser?.email}")
        }


    }

    fun goToAddActivity() {
        val intent = Intent(this, AddItemActivity::class.java)
        startActivity(intent)
    }

    fun loginUser() {
        val email = textEmail.text.toString()
        val password = textPassword.text.toString()

        if ( email.isEmpty() || password.isEmpty())
            return

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("!!!", "Login success ${auth.currentUser?.email}")
                    goToAddActivity()
                } else {
                    Log.d("!!!", "USer not loged in ${task.exception}")
                }

            }
    }


    fun createUser() {
        val email = textEmail.text.toString()
        val password = textPassword.text.toString()

        if ( email.isEmpty() || password.isEmpty())
            return

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
               if (task.isSuccessful) {
                   Log.d("!!!", "Success")
                   goToAddActivity()
               } else {
                   Log.d("!!!", "User not created ${task.exception}")
               }
        }
    }

}