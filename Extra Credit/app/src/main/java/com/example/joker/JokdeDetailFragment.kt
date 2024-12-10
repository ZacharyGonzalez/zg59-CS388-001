package com.example.joker

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class JokeDetailFragment : Fragment() {
    private var jokeText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            jokeText = it.getString(ARG_JOKE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_joke_detail, container, false)

        val jokeTextView = view.findViewById<TextView>(R.id.jokeTextView)
        jokeTextView.text = jokeText

        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        val captureButton = view.findViewById<Button>(R.id.SaveImage)
        captureButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
                // Capture the joke as an image
                captureJokeAsImage(jokeText ?: "")
            } else {
                // Request permission
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_CODE_WRITE_EXTERNAL_STORAGE)
            }
        }

        return view
    }

    private fun captureJokeAsImage(jokeText: String) {
        // Create a Bitmap
        val bitmap = Bitmap.createBitmap(800, 600, Bitmap.Config.ARGB_8888) // You can adjust dimensions
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE) // Background color
        val textPaint = Paint().apply {
            color = Color.BLACK
            textSize = 40f
            isAntiAlias = true
        }

        // Draw the joke text on the canvas
        canvas.drawText(jokeText, 50f, 300f, textPaint)

        // Save the bitmap as an image file
        saveImageToGallery(bitmap)
    }

    private fun saveImageToGallery(bitmap: Bitmap) {
        // Create a directory for saving images
        val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        if (!storageDir.exists()) {
            storageDir.mkdirs() // Create directory if it doesn't exist
        }

        val imageFile = File(storageDir, "joke_${System.currentTimeMillis()}.png")

        try {
            FileOutputStream(imageFile).use { out ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out) // Save the image
                Toast.makeText(context, "Joke saved as image: ${imageFile.absolutePath}", Toast.LENGTH_LONG).show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Error saving image: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can perform the action that requires permission here
                captureJokeAsImage(jokeText ?: "")
            } else {
                Toast.makeText(context, "Permission denied to write to external storage", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val ARG_JOKE = "joke"
        private const val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1

        fun newInstance(joke: String) =
            JokeDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_JOKE, joke)
                }
            }
    }
}
