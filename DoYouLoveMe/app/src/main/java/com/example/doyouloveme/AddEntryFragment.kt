package com.example.doyouloveme


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddEntryFragment : Fragment() {

    private lateinit var itemNameEditText: EditText
    private lateinit var itemCalEditText: EditText
    private lateinit var itemRecordButton: Button
    private lateinit var textSwitcher: TextSwitcher


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun closeKeyBoard() {
            val currentFocus = activity?.currentFocus
            if (currentFocus != null) {
                val imm =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
        textSwitcher = view.findViewById(R.id.textSwitcher)
        textSwitcher.setFactory {
            TextView(requireContext()).apply {
                textSize = 18f
                gravity = android.view.Gravity.CENTER
            }
        }

        Log.d("API start", "Received quotes: ...")
        val retrofit = Retrofit.Builder()
            .baseUrl("https://love-quote.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val quoteService = retrofit.create(QuoteService::class.java)
        fetchQuote(quoteService)

        // Create an instance of QuoteService
            itemRecordButton.setOnClickListener {
            val name = itemNameEditText.text.toString()
            val cal = itemCalEditText.text.toString()

            if (name.isNotEmpty() && cal.isNotEmpty()) {
                closeKeyBoard()
                val newItem = BitFitItem(name, cal)
                newItem.let { item ->
                    lifecycleScope.launch(Dispatchers.IO) {
                        (activity?.application as BitFitApplication).db.bitfitDao().insert(
                            BitFitEntity(
                                itemName = item.itemName,
                                calories = item.calories
                            )
                        )
                    }
                }

                itemNameEditText.text.clear()
                itemCalEditText.text.clear()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_entry, container, false)
        itemNameEditText = view.findViewById(R.id.ItemNameEditText)
        itemCalEditText = view.findViewById(R.id.ItemCaloriesEditText)
        itemRecordButton = view.findViewById(R.id.RecordItemButton)
        textSwitcher = view.findViewById(R.id.textSwitcher)

        return view
    }
    private fun fetchQuote(quoteService: QuoteService) {
        val call = quoteService.getQuote()

        call.enqueue(object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                if (response.isSuccessful) {
                    val quote = response.body()?.quote
                    Log.d("QuoteService", "Received quote: $quote")
                    textSwitcher.setText(quote)
                } else {
                    Log.d("QuoteService", "API request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                Log.d("QuoteService", "API request failed: ${t.message}")
            }
        })
    }

}