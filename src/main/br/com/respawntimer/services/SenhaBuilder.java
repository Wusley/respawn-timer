package br.com.respawntimer.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SenhaBuilder {
	
	private String senha;

	public SenhaBuilder(String senha) {
		this.senha = senha;
	}

	public String criptografar() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		
		byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
		 
		StringBuilder hexString = new StringBuilder();
		
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}
		
		this.senha = hexString.toString();
		
		return senha;
	}
	
}