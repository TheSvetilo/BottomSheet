package com.example.bottom

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.bottom.databinding.FragmentAgreementBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

class AgreementFragment : Fragment() {

    private lateinit var binding: FragmentAgreementBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAgreementBinding.inflate(inflater, container, false)

        binding.showBottomSheet.setOnClickListener {
            val dialog =  BottomSheetDialog(requireActivity() as MainActivity)
            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
            val btnClose = view.findViewById<Button>(R.id.dialogButtonClose)

            btnClose.setOnClickListener {
                dialog.dismiss()
            }

            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }

        val fullText = getString(R.string.agreement_full_text)
        val agreement = getString(R.string.confidential_info)
        val policy = getString(R.string.privacy_policy)

        val spannableString = SpannableString(fullText)

        val agreementClickable = MyClickableSpan {
            Snackbar.make(it, "Agreement here", Snackbar.LENGTH_SHORT).show()
        }

        val policyClickable = MyClickableSpan {
            Snackbar.make(it, "Policy here", Snackbar.LENGTH_SHORT).show()
        }

        spannableString.setSpan(
            agreementClickable,
            fullText.indexOf(agreement),
            fullText.indexOf(agreement) + agreement.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            policyClickable,
            fullText.indexOf(policy),
            fullText.indexOf(policy) + policy.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.agreementText.run {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }

        binding.openImageFragmentButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ImageFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.openLoginPage.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment())
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }
}