package com.leysoft.sumavector.resorces;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recurso {
	
	private int[] array;
	private int suma;
	private double promedio;
	private Lock lock;
	
	public Recurso(int[] array) {
		this.array = array;
		this.suma = 0;
		this.promedio = 0.0;
		this.lock = new ReentrantLock();
	}

	public int getSuma() {
		return suma;
	}

	public void setSuma(int suma) {
		this.suma = suma;
	}

	public double getPromedio() {
		return promedio;
	}

	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	public void sumar(int inicio, int fin) {
		int suma = 0;
		for(int i = inicio; i < fin; i++) {
			suma += array[i];
		}
		lock.lock();
		try {
			this.suma += suma;
		} finally {
			lock.unlock();
		}
	}
}