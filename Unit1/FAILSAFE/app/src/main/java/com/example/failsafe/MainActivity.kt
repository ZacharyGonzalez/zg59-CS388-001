package com.example.failsafe


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private lateinit var wordToGuess: String
    private lateinit var previousGuessesTextView: TextView
    private val previousGuesses = StringBuilder() // To store previous guesses

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var counter = 1
        wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        val guessInput: EditText = findViewById(R.id.TextGuess)
        val submitButton: Button = findViewById(R.id.buttonSubmit)
        val reset: Button = findViewById(R.id.reseter)
        val resultText: TextView = findViewById(R.id.result_text)
        previousGuessesTextView = findViewById(R.id.previousGuesses)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fun checkGuess(guess: String): String {
            var result = ""
            for (i in 0..3) {
                result += when {
                    guess[i] == wordToGuess[i] -> "O"
                    guess[i] in wordToGuess -> "+"
                    else -> "X"
                }
            }
            return result
        }

        fun resetGame() {
            counter=1
            resultText.text = "The Word was: $wordToGuess, New match Started"
            wordToGuess = FourLetterWordList.getRandomFourLetterWord()
            previousGuesses.clear() // Clear previous guesses
            previousGuessesTextView.text = "" // Reset text view
        }
        //resultText.text = "Word was: $wordToGuess" //TESTING PURPOSES
        reset.setOnClickListener {
            resetGame()
            Toast.makeText(applicationContext, "Match Reset", Toast.LENGTH_SHORT).show()

        }

        submitButton.setOnClickListener {

            val userGuess = guessInput.text.toString().uppercase()
            if (userGuess.length == 4 && userGuess.all { it.isLetter() }) {
                val result = checkGuess(userGuess)
                resultText.text = "$userGuess $result"
                //update our already guessed list
                previousGuesses.append("Guess #$counter $userGuess: $result\n")
                previousGuessesTextView.text = previousGuesses.toString()
                guessInput.setText("")
                counter++

            } else {
                resultText.text = "please_enter_a_4_letter_word"
            }
            if (counter >= 4 && wordToGuess!=userGuess) {
                Toast.makeText(applicationContext, "You did not get it, Changing the word", Toast.LENGTH_SHORT).show()
                resetGame()
            }
            if (wordToGuess==userGuess){
                Toast.makeText(applicationContext, "You did IT", Toast.LENGTH_SHORT).show()
                resetGame()
            }

        }
    }
}
