package utn.frsf.isi.died2020.tp07.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import utn.frsf.isi.died2020.tp07.excepciones.AdquisicionException;
import utn.frsf.isi.died2020.tp07.excepciones.LimiteMaximoGastoException;

public class Usuario {
	
	private Integer id;
	private String correoElectronico;
	private String tarjetaCredito;
	private Double gastos;
	private Double LimiteMaximoGasto;
	
	private List<Adquisicion> adquisiciones;
	
	private Predicate<Usuario> puedeAdquirirLibro;
	private Predicate<Usuario> puedeAdquirirVideo;
	private Predicate<Usuario> puedeAdquirirCurso;
	
	private BiFunction<Usuario, Libro, Double> costoLibro;
	private BiFunction<Usuario, Video, Double> costoVideo;
	private BiFunction<Usuario, Curso, Double> costoCurso;
	
	public Usuario() {}
	
	public long minutos(){
		return this.adquisiciones.stream()
					.filter(a -> a.getMaterial() instanceof Video)
					.map(v -> (Video)v.getMaterial())
					.mapToInt(v -> v.getDuracion())
					.sum();
	}
	
	public void adquirir(Material material) throws AdquisicionException, LimiteMaximoGastoException {
		if(material.puedeAdquirir(this)) {
			this.adquisiciones.add(new Adquisicion(material, LocalDateTime.now(), material.costo(this)));
		} else throw new AdquisicionException("No puede adquirir "+material.getTitulo());
		
		if(material.costo(this)>LimiteMaximoGasto) throw new LimiteMaximoGastoException("Limite gasto superado.");
	}
	
	
	public long librosAdquiridos(){
		return this.adquisiciones.stream().filter(a -> a.getMaterial() instanceof Libro).count();
	}
	
	public long cursosAdquiridos(){
		return this.adquisiciones.stream().filter(a -> a.getMaterial() instanceof Libro).count();
	}
	
	public BiFunction<Usuario, Libro, Double> getCostoLibro() {
		return costoLibro;
	}

	public void setCostoLibro(BiFunction<Usuario, Libro, Double> costoLibro) {
		this.costoLibro = costoLibro;
	}

	public BiFunction<Usuario, Video, Double> getCostoVideo() {
		return costoVideo;
	}

	public void setCostoVideo(BiFunction<Usuario, Video, Double> costoVideo) {
		this.costoVideo = costoVideo;
	}

	public BiFunction<Usuario, Curso, Double> getCostoCurso() {
		return costoCurso;
	}

	public void setCostoCurso(BiFunction<Usuario, Curso, Double> costoCurso) {
		this.costoCurso = costoCurso;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public List<Adquisicion> getAdquisiciones() {
		return adquisiciones;
	}

	public Predicate<Usuario> getPuedeAdquirirLibro() {
		return puedeAdquirirLibro;
	}

	public void setPuedeAdquirirLibro(Predicate<Usuario> puedeAdquirirLibro) {
		this.puedeAdquirirLibro = puedeAdquirirLibro;
	}

	public Predicate<Usuario> getPuedeAdquirirVideo() {
		return puedeAdquirirVideo;
	}

	public void setPuedeAdquirirVideo(Predicate<Usuario> puedeAdquirirVideo) {
		this.puedeAdquirirVideo = puedeAdquirirVideo;
	}

	public Predicate<Usuario> getPuedeAdquirirCurso() {
		return puedeAdquirirCurso;
	}

	public void setPuedeAdquirirCurso(Predicate<Usuario> puedeAdquirirCurso) {
		this.puedeAdquirirCurso = puedeAdquirirCurso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correoElectronico == null) ? 0 : correoElectronico.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (correoElectronico == null) {
			if (other.correoElectronico != null)
				return false;
		} else if (!correoElectronico.equals(other.correoElectronico))
			return false;
		return true;
	}

	
}
