package com.leysoft.sumavector.threads;

import com.leysoft.sumavector.resorces.Recurso;

public class Worker implements Runnable {

	private int inicio;
	private int fin;
	private Recurso recurso;

	public Worker(Recurso recurso) {
		this.recurso = recurso;
	}

	public Worker(int inicio, int fin, Recurso recurso) {
		this.inicio = inicio;
		this.fin = fin;
		this.recurso = recurso;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	@Override
	public void run() {
		System.out.println("suma(" + inicio + ", " + fin + ")... " + Thread.currentThread().getName());
		recurso.sumar(inicio, fin);
	}
}