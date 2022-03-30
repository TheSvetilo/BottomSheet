package com.example.bottom

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bottom.databinding.FragmentImageBinding
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class ImageFragment : Fragment() {

    private companion object {
        const val URL = "https://habrastorage.org/r/w1560/getpro/habr/upload_files/d8f/2d7/335/d8f2d7335dde36f918d360c2940f3f0a.jpeg"
    }

    private lateinit var binding: FragmentImageBinding

//    private val textWatcher = object : SimpleTextWatcher() {
//        override fun afterTextChanged(s: Editable?) {
//            super.afterTextChanged(s)
//            val input = s.toString()
//            if (input.endsWith("@g")) {
//                setText("${input}mail.com")
//            }
//        }
//    }
//
//    private fun setText(text: String) {
//        binding.textInput.apply {
//            removeTextChangedListener(textWatcher)
//            setTextCorrect(text)
//            addTextChangedListener(textWatcher)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentImageBinding.inflate(inflater, container, false)

        binding.textInput.listenChanges {
            val valid = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
            binding.textLayout.isErrorEnabled = !valid
            binding.textLayout.error = if (valid) "" else getString(R.string.wrong_email)
            if (valid) Toast
                .makeText(requireContext(), R.string.nice_email, Toast.LENGTH_SHORT)
                .show()
        }

        Picasso.get().load(URL).into(binding.imageView);

//        val netImage = DownloadImage(URL, object : ImageCallback {
//            override fun success(bitmap: Bitmap) {
//                binding.imageView.setImageBitmap(bitmap)
//            }
//            override fun failed() {
//                Snackbar.make(binding.imageView, "Something wrong", Snackbar.LENGTH_SHORT).show()
//            }
//        })
//        netImage.start()

        return binding.root
    }

    fun TextInputEditText.setTextCorrect(text: CharSequence) {
        setText(text)
        setSelection(text.length)
    }

    fun TextInputEditText.listenChanges(block: (text: String) -> Unit) {
        addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                block.invoke(s.toString())
            }
        })
    }

}