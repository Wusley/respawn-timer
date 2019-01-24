package br.com.respawntimer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique=true)
	private String nick;
	
	@Column(unique=true)
	private String email;
	
	@Transient
	private String emailRepeat;
	
	@Column
	private String senha;
	
	@Transient
	private String senhaRepeat;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmailRepeat() {
		return emailRepeat;
	}

	public void setEmailRepeat(String email) {
		this.emailRepeat = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenhaRepeat() {
		return senhaRepeat;
	}
	
	public void setSenhaRepeat(String senha) {
		this.senhaRepeat = senha;
	}
}