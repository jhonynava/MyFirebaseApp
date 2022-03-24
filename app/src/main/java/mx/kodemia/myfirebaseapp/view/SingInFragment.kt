package mx.kodemia.myfirebaseapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import mx.kodemia.myfirebaseapp.R
import mx.kodemia.myfirebaseapp.databinding.FragmentSingInBinding
import mx.kodemia.myfirebaseapp.service.FirebaseService

class SingInFragment : Fragment() {

    private lateinit var navController: NavController
    private val firebaseService = FirebaseService()
    private var _binding: FragmentSingInBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingInBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        binding.loginButton.setOnClickListener {
            val email = binding.tietEmail.text.toString()
            val pass = binding.tietPass.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                requireActivity().lifecycleScope.launch {
                    val task = firebaseService.login(email, pass)
                    task.addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(requireContext(), "Si existe", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "No existe", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Datos vacios", Toast.LENGTH_SHORT).show()
            }
        }

        binding.singUpButton.setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }
}