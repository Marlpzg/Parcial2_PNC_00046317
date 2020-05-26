package com.uca.capas.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema = "public", name = "cat_libro")
public class Libro {

	@Id
	@GeneratedValue(generator="cat_libro_c_libro_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cat_libro_c_libro_seq", sequenceName = "public.cat_libro_c_libro_seq", allocationSize = 1)
	@Column(name = "c_libro")
	private Integer clibro;
	
	@Column(name = "s_titulo")
	@Size(message = "El campo sobrepasa la cantidad de 500 caracteres", max = 500)
	@NotEmpty(message = "El campo no puede estar vacio")
	private String stitulo;
	
	@Column(name = "s_autor")
	@Size(message = "El campo sobrepasa la cantidad de 150 caracteres", max = 150)
	@NotEmpty(message = "El campo no puede estar vacio")
	private String sautor;
	
	@Column(name = "s_isbn")
	@Size(message = "El campo sobrepasa la cantidad de 10 caracteres", max = 10)
	@NotEmpty(message = "El campo no puede estar vacio")
	private String sisbn;
	
	@Transient
	private Integer ccategoria;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "c_categoria")
	private Categoria categoria;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm aa")
	@Column(name = "f_ingreso")
	private Date fingreso;
	
	@Column(name="b_estado")
    private Boolean estado;
	
	public Libro() {}

	public Integer getClibro() {
		return clibro;
	}

	public void setClibro(Integer clibro) {
		this.clibro = clibro;
	}

	public String getStitulo() {
		return stitulo;
	}

	public void setStitulo(String stitulo) {
		this.stitulo = stitulo;
	}

	public String getSautor() {
		return sautor;
	}

	public void setSautor(String sautor) {
		this.sautor = sautor;
	}

	public Integer getCcategoria() {
		return ccategoria;
	}

	public void setCcategoria(Integer ccategoria) {
		this.ccategoria = ccategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getFingreso() {
		return fingreso;
	}

	public void setFingreso(Date fingreso) {
		this.fingreso = fingreso;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getSisbn() {
		return sisbn;
	}

	public void setSisbn(String sisbn) {
		this.sisbn = sisbn;
	}
	
	public String getFingresoDelegate() {
		if(this.fingreso == null){
			return "";
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
			String shortdate = sdf.format(this.fingreso.getTime());
			return shortdate;
		}
	}
	
}
