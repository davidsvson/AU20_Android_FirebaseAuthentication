package se.iths.au20.firebaseauthentication

import com.google.firebase.firestore.DocumentId

data class Item(@DocumentId var documentId : String,
                var name: String? = null,
                var done: Boolean = false,
                var category: String? = null)
