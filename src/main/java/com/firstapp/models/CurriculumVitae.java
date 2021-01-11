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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CurriculumVitae {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "grado_academico_id", nullable = false)
	private NivelEducacional nivelEducacional;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "jornada_laboral_id", nullable = false)
	private JornadaLaboral jornadaLaboral;
	
	@ManyToMany
	@JoinTable(
			name="idioma_has_curriculum_vitae",
			joinColumns = @JoinColumn(name = "curriculum_vitae_id"),
			inverseJoinColumns = @JoinColumn(name = "idioma_id"))
	private List<Idioma> idiomas;
	
	@ManyToMany
	@JoinTable(
			name="lenguajes_programacion_has_curriculum_vitae",
			joinColumns = @JoinColumn(name = "curriculum_vitae_id"),
			inverseJoinColumns = @JoinColumn(name = "lenguajes_programacion_id"))
	private List<LenguajeProgramacion> lenguajesProgramacion;
	
	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "colaborador_id", nullable = false)
	private Colaborador colaborador;

	public CurriculumVitae() {
		
	}

	public CurriculumVitae(int id, NivelEducacional nivelEducacional, JornadaLaboral jornadaLaboral,
			List<Idioma> idiomas, List<LenguajeProgramacion> lenguajesProgramacion, Colaborador colaborador) {
		this.id = id;
		this.nivelEducacional = nivelEducacional;
		this.jornadaLaboral = jornadaLaboral;
		this.idiomas = idiomas;
		this.lenguajesProgramacion = lenguajesProgramacion;
		this.colaborador = colaborador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NivelEducacional getNivelEducacional() {
		return nivelEducacional;
	}

	public void setNivelEducacional(NivelEducacional nivelEducacional) {
		this.nivelEducacional = nivelEducacional;
	}

	public JornadaLaboral getJornadaLaboral() {
		return jornadaLaboral;
	}

	public void setJornadaLaboral(JornadaLaboral jornadaLaboral) {
		this.jornadaLaboral = jornadaLaboral;
	}

	public List<Idioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}

	public List<LenguajeProgramacion> getLenguajesProgramacion() {
		return lenguajesProgramacion;
	}

	public void setLenguajesProgramacion(List<LenguajeProgramacion> lenguajesProgramacion) {
		this.lenguajesProgramacion = lenguajesProgramacion;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
}
