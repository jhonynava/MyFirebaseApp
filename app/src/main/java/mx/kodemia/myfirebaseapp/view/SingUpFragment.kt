package mx.kodemia.myfirebaseapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.coroutines.launch
import mx.kodemia.myfirebaseapp.R
import mx.kodemia.myfirebaseapp.databinding.FragmentSingUpBinding
import mx.kodemia.myfirebaseapp.service.FirebaseService

class SingUpFragment : Fragment() {

    private lateinit var navController: NavController
    private val firebaseService = FirebaseService()
    private var _binding: FragmentSingUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initComponents()
    }

    private fun initComponents() {
        binding.buttonSingup.setOnClickListener {
            val email = binding.tietEmail.text.toString()
            val pass = binding.tietPass.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                requireActivity().lifecycleScope.launch {
                    val task = firebaseService.register(email, pass)
                    task.addOnCompleteListener {
                        if (it.isSuccessful) {
                            navController.navigate(R.id.action_signUpFragment_to_homeFragment)
                            Toast.makeText(requireContext(), "Creado con exito", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "El correo ya existe", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Datos vacios", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonRegister.setOnClickListener {
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }
}