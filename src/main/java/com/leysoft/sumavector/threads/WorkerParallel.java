package com.leysoft.sumavector.threads;

public class WorkerParallel extends Thread {

	private int[] array;
	private int inicio;
	private int fin;
	private int suma;
	
	public WorkerParallel(int[] array, int inicio, int fin, String nombre) {
		super(nombre);
		this.array = array;
		this.inicio = inicio;
		this.fin = Math.min(fin, array.length);
		this.suma = 0;
	}

	public int[] getArray() {
		return array;
	}

	public int getSuma() {
		return suma;
	}

	@Override
	public void run() {
		for(int i = this.inicio; i < this.fin; i++) {
			this.suma += this.array[i];
		}
		System.out.println("suma("+ this.inicio +", "+ this.fin +")... " + Thread.currentThread().getName());
	}
}