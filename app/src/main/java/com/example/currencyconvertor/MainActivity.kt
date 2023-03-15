package com.example.currencyconvertor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        val globalFrag = Global()
        val cryptoFrag = Crypto()
        val fragment1: Button = findViewById(R.id.GlobalCurrency)
        val fragment2: Button = findViewById(R.id.CryptoCurrency)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FLfragment, globalFrag)
            commit()
        }

        val button: Button = findViewById(R.id.CalcButton)
        val dinar: EditText = findViewById(R.id.JOD)
        val resultTV: TextView = findViewById(R.id.ans)
        val printCurr: TextView = findViewById((R.id.CurrencyPrint))
        var flag:String = "USD"
        var curr:Boolean = true
        val options1 = arrayOf("USD", "EURO", "POUND")
        val options2 = arrayOf("Bitcoin", "Ethereum", "USDT")

        val spinnerVal: Spinner = findViewById(R.id.Spin)
        spinnerVal.adapter=
            ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options1)

        spinnerVal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(curr)
                    flag = options1.get(p2)
                else flag = options2.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        fragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.FLfragment, globalFrag)
                commit()
            }
            curr = true
            spinnerVal.adapter=
                ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options1)
        }
        fragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.FLfragment, cryptoFrag)
                commit()
            }
            curr = false
            spinnerVal.adapter=
                ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options2)
        }

        button.setOnClickListener{ view->
            var x: Double = 0.0
            if(dinar.text.toString() != "")
                x = dinar.text.toString().toDouble()
            if(flag=="USD" || flag == "USDT") {
                resultTV.text = usd(x).toString()
                var currencyP:String = "USD"
                if(flag=="USDT") {
                    currencyP = "USDT"
                    findViewById<ImageView>(R.id.CurrImage).setImageResource(R.drawable.usdt)
                }
                else {
                    findViewById<ImageView>(R.id.CurrImage).setImageResource(R.drawable.dollar)
                }
                printCurr.text = currencyP
            }
            else if(flag=="EURO") {
                resultTV.text = euro(x).toString()
                val currencyP:String = "Euro"
                printCurr.text = currencyP
                findViewById<ImageView>(R.id.CurrImage).setImageResource(R.drawable.euro)
            }
            else if(flag=="POUND") {
                resultTV.text = pound(x).toString()
                val currencyP:String = "Pound"
                printCurr.text = currencyP
                findViewById<ImageView>(R.id.CurrImage).setImageResource(R.drawable.pound)
            }
            else if(flag=="Bitcoin") {
                resultTV.text = bit(x).toString()
                val currencyP:String = "Bitcoin"
                printCurr.text = currencyP
                findViewById<ImageView>(R.id.CurrImage).setImageResource(R.drawable.bitcoin)
            }
            else {
                resultTV.text = eth(x).toString()
                val currencyP:String = "Ethereum"
                printCurr.text = currencyP
                findViewById<ImageView>(R.id.CurrImage).setImageResource(R.drawable.ethereum)
            }
        }


    }
}

fun usd(jod:Double):Double{
    return jod * 0.71
}

fun euro(jod:Double):Double{
    return jod * 0.75
}

fun pound(jod:Double):Double{
    return jod * 0.85
}

fun bit(jod:Double):Double{
    return jod * 0.000058
}

fun eth(jod:Double):Double{
    return jod * 0.00085
}