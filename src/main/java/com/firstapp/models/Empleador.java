package com.firstapp.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Empleador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nombre;
	private String rut;
	private String razon_social;
	private String descripcion;
	private String direccion;
	private String email;
	
	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	@OneToMany( mappedBy = "empleador")
	private List<OfertaLaboral> OfertasLaborales;
	
	public Empleador() {
		
	}

	public Empleador(int id, String nombre, String rut, String razon_social, String descripcion, String direccion,
			String email, Usuario usuario) {
		this.id = id;
		this.nombre = nombre;
		this.rut = rut;
		this.razon_social = razon_social;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.email = email;
		this.usuario = usuario;
	}

	public List<OfertaLaboral> getOfertasLaborales() {
		return OfertasLaborales;
	}

	public void setOfertasLaborales(List<OfertaLaboral> ofertasLaborales) {
		OfertasLaborales = ofertasLaborales;
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

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public String toString() {
		return "Empleador [id=" + id + ", nombre=" + nombre + ", rut=" + rut + ", razon_social=" + razon_social
				+ ", descripcion=" + descripcion + ", direccion=" + direccion + ", email=" + email + ", usuario="
				+ usuario + "]";
	}
	
}
