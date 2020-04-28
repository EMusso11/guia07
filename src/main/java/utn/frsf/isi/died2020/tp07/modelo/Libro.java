package utn.frsf.isi.died2020.tp07.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Libro extends Material{

	private String isbn;
	private Integer paginas;
	
	public Libro() {
		super();
	}

	public Libro(String titulo, LocalDateTime fechaPublicacion, Integer calificacion, Autor autor,
			List<Tema> temas,String isbn, Integer paginas) {
		super(titulo, fechaPublicacion, calificacion, autor, temas);
		this.isbn = isbn;
		this.paginas = paginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}
	
	@Override
	public Double costo(Usuario usuario) {
		return usuario.getCostoLibro().apply(usuario, this);
	}

	@Override
	public Boolean puedeAdquirir(Usuario usuario) {
		return usuario.getPuedeAdquirirLibro().test(usuario);
	}

	@Override
	public Double rating() {
		List<Adquisicion> list = new ArrayList<Adquisicion>();
		list = adquisiciones.stream()
							.filter(l -> l.getPagado() == true)
							.collect(Collectors.toList());
		double precioPromedio = this.adquisiciones.stream()
										.filter(a -> a.getMaterial() instanceof Libro)
										.mapToDouble(l -> l.getPrecio())
										.sum();
		return 0.50*calificacion + 0.35*list.size() + 0.15*precioPromedio*paginas;
	}
	
}
