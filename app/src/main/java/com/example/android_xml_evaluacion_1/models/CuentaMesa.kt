package com.example.android_xml_evaluacion_1.models

class CuentaMesa(val numeroMesa: Int) {
	private val _items : MutableList<ItemMesa> = mutableListOf()
	var aceptaPropina : Boolean = true
	var total : Int = 0

	fun agregarItem(item : ItemMesa) {
		_items.add(item)
	}

	fun agregarItem(itemMenu : ItemMenu, cantidad : Int) {
		_items.add(ItemMesa(cantidad, itemMenu))
	}

	fun calcularTotalSinPropina() : Int {
		for (item in _items) {
			total += item.calcularSubtotal()
		}
		return total
	}

	fun calcularPropina() : Int {
		return total / 10
	}

	fun calcularTotalConPropina() : Int {
		return total + calcularPropina()
	}
}
