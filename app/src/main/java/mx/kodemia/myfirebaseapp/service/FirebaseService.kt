package mx.kodemia.myfirebaseapp.service

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseService {

    suspend fun login(email: String, pass: String): Task<AuthResult> {
        return withContext(Dispatchers.IO) {
            val result = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
            result
        }
    }

    suspend fun register(email: String, pass: String): Task<AuthResult> {
        return withContext(Dispatchers.IO) {
            val result = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
            result
        }
    }

    suspend fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }
}