package com.example.android_xml_evaluacion_1.models

data class ItemMesa(val cantidad : Int, val itemMenu : ItemMenu) {
		fun calcularSubtotal() : Int {
				return cantidad * itemMenu.precio.toInt()
		}
}
