package utn.frsf.isi.died2020.tp07.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Video  extends Material {
	
	private Integer duracion;
		
	public Video() {
		super();
	}

	public Video(String titulo, LocalDateTime fechaPublicacion, Integer calificacion, Autor autor,
			List<Tema> temas,Integer duracion) {
		super( titulo, fechaPublicacion, calificacion, autor, temas);
		this.duracion = duracion;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	@Override
	public Double rating() {
		double precioPromedio = this.adquisiciones.stream()
				.filter(a -> a.getMaterial() instanceof Libro)
				.mapToDouble(l -> l.getPrecio())
				.sum();
		return 0.45*calificacion + 0.35*adquisiciones.size()*duracion + 0.15*precioPromedio;	
	}
	
	@Override
	public Double costo(Usuario usuario) {
		return usuario.getCostoVideo().apply(usuario, this);
	}

	@Override
	public Boolean puedeAdquirir(Usuario usuario) {
		return usuario.getPuedeAdquirirVideo().test(usuario);
	}

}
