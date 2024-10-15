package com.example.android_xml_evaluacion_1.models

class CuentaMesa(var numeroMesa: Int) {
	private val _items : MutableList<ItemMesa> = mutableListOf()
	var aceptaPropina : Boolean = true

	fun agregarItem(item : ItemMesa) {
		_items.add(item)
	}

	fun agregarItem(itemMenu : ItemMenu, cantidad : Int) {
		_items.add(ItemMesa(cantidad, itemMenu))
	}

	fun calcularTotalSinPropina() : Int {
		var total = 0
		for (item in _items) {
			total += item.calcularSubtotal()
		}
		return total
	}

	fun calcularPropina() : Int {
		return calcularTotalSinPropina() / 10
	}

	fun calcularTotalConPropina() : Int {
		return calcularTotalSinPropina() + calcularPropina()
	}
}
