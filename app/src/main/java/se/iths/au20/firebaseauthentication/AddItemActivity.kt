package se.iths.au20.firebaseauthentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class AddItemActivity : AppCompatActivity() {

    lateinit var nameText :EditText
    lateinit var categoryText: EditText
    lateinit var db : FirebaseFirestore
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        nameText = findViewById(R.id.editTextName)
        categoryText = findViewById(R.id.editTextCategory)

        val button = findViewById<Button>(R.id.addButton)
        button.setOnClickListener {
            saveItem()
        }

    }

    fun saveItem() {
        val item = Item(nameText.text.toString(), false, categoryText.text.toString())

        val user = auth.currentUser
        if( user == null)
            return

        db.collection("users").document("k3oIbZnd6xYmIF8gufWARV6IOVX2").collection("items").add(item)
            .addOnCompleteListener { task ->
                Log.d("!!!", "Add: ${task.exception}")

            }

    }

}