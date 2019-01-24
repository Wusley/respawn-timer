package br.com.respawntimer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_respawn")
public class Respawn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "usuario_pk")
	private Usuario usuario;

	@Column
	private String nome;
	
	@Column
	private Long dataMorte;
	
	@Transient
	private Long dias;
	
	@Column
	private Long tempoNascimento;
	
	@Column(nullable = false, columnDefinition = "tinyint default 2")
	private int sucesso;
	
	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean status;
	
	public Respawn() {
		this.dias = (long) 0;
		this.tempoNascimento = (long) 0;
		this.sucesso = 2;
		this.status = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}
	//
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getDataMorte() {
		return dataMorte;
	}

	public void setDataMorte(Long dataMorteInMillis) throws NumberFormatException {
		
		this.dataMorte = dataMorteInMillis;
		
	}
	
	public Long getDias() {
		return dias;
	}

	public void setDias(Long dias) throws NumberFormatException {
		
		if(dias > 0 || dias <= 9999) {
			
			this.dias = dias * 86400000;
			
		}
		
	}

	public Long getTempoNascimento() {
		
		return tempoNascimento;
		
	}

	public void setTempoNascimento(Long tempoNascimentoInMillis) throws NumberFormatException {
	   
		this.tempoNascimento = tempoNascimentoInMillis;
				
	}
	
	public int getSucesso() {
		
		return sucesso;
		
	}

	public void setSucesso(int sucesso) {
		
		this.sucesso = sucesso;
		
	}

	public boolean isStatus() {
		
		return status;
		
	}

	public void setStatus(boolean status) {
		
		this.status = status;
		
	}
}