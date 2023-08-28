package com.example.demo.controller.errors;

public class EntidadeNaoEncontrada extends RuntimeException{
	
	public EntidadeNaoEncontrada(String msg) {
		super(msg);
	}
	
	public EntidadeNaoEncontrada() {
		// TODO Auto-generated constructor stub
	}

}
