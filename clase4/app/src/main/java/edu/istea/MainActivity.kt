package edu.istea

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var b_uno :Button
    lateinit var b_dos :Button
    lateinit var b_tres :Button
    lateinit var b_cuatro :Button
    lateinit var b_cinco :Button
    lateinit var b_seis :Button
    lateinit var b_siete :Button
    lateinit var b_ocho :Button
    lateinit var b_nueve :Button
    lateinit var b_cero :Button
    lateinit var b_mas :Button
    lateinit var b_menos :Button
    lateinit var b_dividir :Button
    lateinit var b_multiplicar :Button
    lateinit var t_calculo :TextView
    lateinit var b_clean:Button
    lateinit var b_igual:Button
    lateinit var wifiSwitch: Switch
    lateinit var wifiManager: WifiManager




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initization()

        asignoValor()
        cleanTextView()
        calcular()

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                wifiManager.isWifiEnabled = true
                wifiSwitch.text = "WiFi is ON"
            } else {
                wifiManager.isWifiEnabled = false
                wifiSwitch.text = "WiFi is OFF"
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(wifiStateReceiver, intentFilter)
    }
    override fun onStop() {
        super.onStop()
        unregisterReceiver(wifiStateReceiver)

    }


    private fun asignoValor(){
        asignoOperacion(b_uno)
        asignoOperacion(b_dos)
        asignoOperacion(b_tres)
        asignoOperacion(b_cuatro)
        asignoOperacion(b_cinco)
        asignoOperacion(b_seis)
        asignoOperacion(b_siete)
        asignoOperacion(b_ocho)
        asignoOperacion(b_nueve)
        asignoOperacion(b_cero)
        asignoOperacion(b_multiplicar)
        asignoOperacion(b_dividir)
        asignoOperacion(b_mas)
        asignoOperacion(b_menos)



    }


    private fun calcular(){

        b_igual.setOnClickListener(View.OnClickListener {
            try {
                var resultado = ExpressionBuilder(t_calculo.text.toString()).build()
                t_calculo.setText(resultado.evaluate().toString())
            }catch (e:ArithmeticException){
                Toast.makeText(this,"muerto no podes dividir por cero",Toast.LENGTH_LONG).show()
            }

        } )

    }

    private fun asignoOperacion(btn : Button){
        btn.setOnClickListener(View.OnClickListener {
            t_calculo.text = t_calculo.text.toString() + btn.text.toString()
        } )
    }

    private fun initization(){

        b_uno =findViewById(R.id.b_uno)
        b_dos =findViewById(R.id.b_dos)
        b_tres =findViewById(R.id.b_tres)
        b_cuatro =findViewById(R.id.b_cuatro)
        b_cinco =findViewById(R.id.b_cinco)
        b_seis =findViewById(R.id.b_seis)
        b_siete =findViewById(R.id.b_siete)
        b_ocho =findViewById(R.id.b_ocho)
        b_nueve =findViewById(R.id.b_nueve)
        b_cero =findViewById(R.id.b_cero)
        b_mas =findViewById(R.id.b_mas)
        b_menos =findViewById(R.id.b_menos)
        b_dividir =findViewById(R.id.b_dividir)
        b_multiplicar =findViewById(R.id.b_multiplicar)
        t_calculo =findViewById(R.id.t_calculo)
        b_clean = findViewById(R.id.b_clean)
        b_igual = findViewById(R.id.b_igual)
        wifiSwitch = findViewById(R.id.s_wifi)

    }

    private fun cleanTextView(){
       b_clean.setOnClickListener(
               View.OnClickListener {
                   t_calculo.text= ""
               }
       )

    }


    private val wifiStateReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                WifiManager.WIFI_STATE_UNKNOWN)) {
                WifiManager.WIFI_STATE_ENABLED -> {
                    wifiSwitch.isChecked = true
                    wifiSwitch.text = "WiFi is ON"
                    Toast.makeText(this@MainActivity, "Wifi is On", Toast.LENGTH_SHORT).show()
                }
                WifiManager.WIFI_STATE_DISABLED -> {
                    wifiSwitch.isChecked = false
                    wifiSwitch.text = "WiFi is OFF"
                    Toast.makeText(this@MainActivity, "Wifi is Off", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}