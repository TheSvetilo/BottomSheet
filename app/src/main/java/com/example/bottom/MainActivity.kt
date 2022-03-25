package com.example.bottom

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var btnShowBottomSheet: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShowBottomSheet = findViewById(R.id.showBottomSheet)

        btnShowBottomSheet.setOnClickListener {
            val dialog =  BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
            val btnClose = view.findViewById<Button>(R.id.dialogButtonClose)

            btnClose.setOnClickListener {
                dialog.dismiss()
            }

            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }

        val agreementTextView = findViewById<TextView>(R.id.agreementText)
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

        agreementTextView.run {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }
}