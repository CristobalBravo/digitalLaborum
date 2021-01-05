package com.firstapp.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Colaborador{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	private String nombre;

	private String apellido;

	private String run;

	private String numero_telefono;

	private String correo;
	
	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	@ManyToMany
	@JoinTable(
			name="colaborador_has_profesion",
			joinColumns = @JoinColumn(name = "colaborador_id"),
			inverseJoinColumns = @JoinColumn(name = "profesion_id"))
	private List<Profesion> profesiones;
	
	public Colaborador() {
		
	}

	

	public Colaborador(int id, String nombre, String apellido, String run, String numero_telefono, String correo,
			Usuario usuario, List<Profesion> profesiones) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.run = run;
		this.numero_telefono = numero_telefono;
		this.correo = correo;
		this.usuario = usuario;
		this.profesiones = profesiones;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getRun() {
		return run;
	}

	public void setRun(String run) {
		this.run = run;
	}

	public String getNumero_telefono() {
		return numero_telefono;
	}

	public void setNumero_telefono(String numero_telefono) {
		this.numero_telefono = numero_telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Profesion> getProfesiones() {
		return profesiones;
	}
	public void setProfesiones(List<Profesion> profesiones) {
		this.profesiones = profesiones;
	}



	@Override
	public String toString() {
		return "Colaborador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", run=" + run
				+ ", numero_telefono=" + numero_telefono + ", correo=" + correo + ", usuario=" + usuario + "]";
	}
	
	
	
	
}
