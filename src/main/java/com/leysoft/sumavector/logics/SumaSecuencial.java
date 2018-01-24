package com.leysoft.sumavector.logics;

public class SumaSecuencial {
	
	private int[] array;

	public SumaSecuencial(int[] array) {
		this.array = array;
	}
	
	public int suma() {
		int suma = 0;
		for(int i = 0; i < array.length; i++) {
			suma += array[i];
		}
		return suma;
	}
}