package com.example.bottom

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bottom.databinding.FragmentButtonsBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentButtonsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentButtonsBinding.inflate(inflater, container, false)

        val loginButton = binding.signInButton
        loginButton.isEnabled = false
        loginButton.setOnClickListener {
            if (Patterns.EMAIL_ADDRESS.matcher(binding.textInputView.text.toString()).matches()) {
                binding.linearLayout.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                hideKeyboard(binding.textInputView)
                Snackbar.make(loginButton, "Successful", Snackbar.LENGTH_SHORT).show()
            } else {
                binding.textLayoutView.isErrorEnabled = true
                binding.textLayoutView.error = getString(R.string.wrong_email)
            }
        }

        binding.agreementCheckbox.setOnCheckedChangeListener { _, isChecked ->
            loginButton.isEnabled = isChecked
        }

        return binding.root
    }

    private fun Fragment.hideKeyboard(view: View) {
        val imm = requireActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}