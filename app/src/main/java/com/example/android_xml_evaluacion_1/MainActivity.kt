package com.example.android_xml_evaluacion_1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_xml_evaluacion_1.models.CuentaMesa
import com.example.android_xml_evaluacion_1.models.ItemMenu
import com.example.android_xml_evaluacion_1.models.ItemMesa
import java.text.NumberFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {

	private var tvTitleItem1: TextView? = null
	private var tvTitleItem2: TextView? = null
	private var etItem1Cantidad: EditText? = null
	private var etItem2Cantidad: EditText? = null
	private var tvValueItem1: TextView? = null
	private var tvValueItem2: TextView? = null
	private var tvComidaValue: TextView? = null
	private var tvPropinaValue: TextView? = null
	private var tvTotalValue: TextView? = null
	private var swPropina: SwitchCompat? = null

	val formatter = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
	val PRECIO_ITEM1 = 12000
	val PRECIO_ITEM2 = 10000

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(
				systemBars.left, systemBars.top, systemBars.right, systemBars.bottom
			)
			insets
		}
		tvTitleItem1 = findViewById(R.id.tvTitleItem1)
		tvTitleItem2 = findViewById(R.id.tvTitleItem2)
		etItem1Cantidad = findViewById(R.id.etCantidadItem1)
		etItem2Cantidad = findViewById(R.id.etCantidadItem2)
		tvValueItem1 = findViewById(R.id.tvValueItem1)
		tvValueItem2 = findViewById(R.id.tvValueItem2)
		tvComidaValue = findViewById(R.id.tvComidaValue)
		tvPropinaValue = findViewById(R.id.tvPropinaValue)
		tvTotalValue = findViewById(R.id.tvTotalValue)
		swPropina = findViewById(R.id.swPropina)

		tvValueItem1?.setText(formatter.format(PRECIO_ITEM1))
		tvValueItem2?.setText(formatter.format(PRECIO_ITEM2))

		swPropina?.setOnCheckedChangeListener { buttonView, isChecked ->
			procesarCuenta()
		}

		etItem1Cantidad?.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(
				s: CharSequence?, start: Int, count: Int, after: Int
			) {
			}

			override fun onTextChanged(
				s: CharSequence?, start: Int, before: Int, count: Int
			) {
			}

			override fun afterTextChanged(s: Editable?) {
				procesarCuenta()
			}
		})

		etItem2Cantidad?.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(
				s: CharSequence?, start: Int, count: Int, after: Int
			) {
			}

			override fun onTextChanged(
				s: CharSequence?, start: Int, before: Int, count: Int
			) {
			}

			override fun afterTextChanged(s: Editable?) {
				procesarCuenta()
			}
		})
		procesarCuenta()
	}

	fun procesarCuenta() {
		val cuenta = CuentaMesa(1)
		cuenta.aceptaPropina = swPropina?.isChecked ?: true
		cuenta.agregarItem(
			ItemMesa(
				etItem1Cantidad?.text.toString().toInt(),
				ItemMenu(tvTitleItem1?.text.toString(), PRECIO_ITEM1.toString())
			)
		)
		cuenta.agregarItem(
			ItemMesa(
				etItem2Cantidad?.text.toString().toInt(),
				ItemMenu(tvTitleItem2?.text.toString(), PRECIO_ITEM2.toString())
			)
		)
		val total = cuenta.calcularTotalSinPropina()
		val propina = cuenta.calcularPropina()
		val totalConPropina =
			if (cuenta.aceptaPropina) cuenta.calcularTotalConPropina()
			else total

		tvComidaValue?.setText(formatter.format(total))
		tvPropinaValue?.setText(formatter.format(propina))
		tvTotalValue?.setText(formatter.format(totalConPropina))
	}
}
