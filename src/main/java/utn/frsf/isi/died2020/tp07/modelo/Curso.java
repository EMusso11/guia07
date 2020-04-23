package utn.frsf.isi.died2020.tp07.modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Curso extends Material{
	
	public enum Nivel {BASE,AVANZADO,EXPERTO};
	
	private Double precioBase;
	private Boolean certificado;
	private Nivel nivel;
	private Integer clases;
	
	public Curso() {
		super();
	}

	public Curso(String titulo, LocalDateTime fechaPublicacion, Integer calificacion, Autor autor,
			List<Tema> temas,Double precioBase, Boolean certificado, Nivel nivel, Integer clases) {
		super(titulo, fechaPublicacion, calificacion, autor, temas);
		this.precioBase = precioBase;
		this.certificado = certificado;
		this.nivel = nivel;
		this.clases = clases;
	}

	public Double precio() {
		Double cost = precioBase * clases;
		
		if(clases>10)
			cost*=1.1;
		if(certificado)
			cost*=1.1;
		switch(nivel){
		case AVANZADO : cost*=1.1;
			break;
		case EXPERTO : cost*= 1.2;
			break;
		default:
		}
		
		return cost;
	}

	public Double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}

	public Boolean getCertificado() {
		return certificado;
	}

	public void setCertificado(Boolean certificado) {
		this.certificado = certificado;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Integer getClases() {
		return clases;
	}

	public void setClases(Integer clases) {
		this.clases = clases;
	}

	@Override
	public Double rating() {
		List<Adquisicion> list = new ArrayList<Adquisicion>();
		list = adquisiciones.stream()
							.filter(elem -> elem.getPagado() == true)
							.sorted((elem1, elem2) -> elem1.getPrecio().compareTo(elem2.getPrecio()))
							.collect(Collectors.toList());
		
		Double precioMaximoDeAdquisicion = list.get(0).getPrecio();		
		
		return 0.45*calificacion + 0.35*adquisiciones.size()
			+ 0.15*list.size()*clases + 0.05*precioMaximoDeAdquisicion;
	}
	
	@Override
	public Double costo(Usuario usuario) {
		return 0.0;
	}
		
	

	@Override
	public Boolean puedeAdquirir(Usuario usuario) {
		return false;
	}
}
