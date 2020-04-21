package utn.frsf.isi.died2020.tp07.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utn.frsf.isi.died2020.tp07.modelo.Autor;
import utn.frsf.isi.died2020.tp07.modelo.Curso;
import utn.frsf.isi.died2020.tp07.modelo.Libro;
import utn.frsf.isi.died2020.tp07.modelo.Material;
import utn.frsf.isi.died2020.tp07.modelo.Tema;
import utn.frsf.isi.died2020.tp07.modelo.Video;

public class Catalogo {
	
	public enum CriterioOrdenamiento { TITULO,FECHA_PUBLICACION,CALIFICACION,COSTO,CALIFICACION_COSTO};
	
	private List<Material> catalogo;
	private Set<Autor> autores;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public Catalogo() {
		this.catalogo = new ArrayList<Material>();
		this.autores = new LinkedHashSet<Autor>();
	}

	public Stream<Material> getCatalogo() {
		return catalogo.stream();
	}
	
	public Stream<Autor> getAutores() {
		return autores.stream();
	}
	
	private Optional<Autor> buscarAutor(Predicate<Autor> p) {
		return this.autores.stream().filter( p).findFirst();
	}
	
	private Optional<Material> buscarMaterial(Predicate<Material> p) {
		return this.catalogo.stream().filter(p).findFirst();
	}

	private List<Material> buscarListaMaterial(Predicate<Material> p) {
		return this.catalogo.stream()
				.filter(p)
				.sorted()
				.collect(Collectors.toList());
	}

	private List<Material> buscarListaMaterial(Predicate<Material> p,Integer n) {
		return this.catalogo
					.stream()
					.filter(p)
					.sorted()
					.limit(n)
					.collect(Collectors.toList());
	}

	private List<Material> buscarListaMaterial(Predicate<Material> p,Comparator<Material> orden) {
		return this.catalogo
					.stream()
					.filter(p)
					.sorted(orden)
					.collect(Collectors.toList());
	}
	
	private List<Material> buscarListaMaterial(Predicate<Material> p,Integer n,Comparator<Material> orden) {
		return this.catalogo
					.stream()
					.filter(p)
					.limit(n)
					.sorted(orden)
					.collect(Collectors.toList());
	}
	

	private Autor obtenerAutor(String nombre) {
		Optional<Autor> autorOpt =this.buscarAutor(a -> a.getNombre().equalsIgnoreCase(nombre));
		Autor autor = null;
		if(autorOpt.isEmpty()) {
			autor = new Autor(nombre);
		} else {
			autor = autorOpt.get();
		}
        return autor;
	}
	
	public void agregarLibro(String titulo, String nombreAutor, Integer calificacion,String fechaPublicacion,Tema[] temas,String isbn, Integer paginas) {
		List<Tema> temasLibro = Arrays.asList(temas);
        Libro l = new Libro( titulo, LocalDate.parse(fechaPublicacion, formatter).atTime(LocalTime.now()), calificacion, this.obtenerAutor(nombreAutor), temasLibro, isbn, paginas);
        this.catalogo.add(l);
	}

	public void agregarVideo(String titulo, String nombreAutor, Integer calificacion,String fechaPublicacion,Tema[] temas,Integer duracion) {
		List<Tema> temasLibro = Arrays.asList(temas);
		Video v = new Video( titulo, LocalDate.parse(fechaPublicacion, formatter).atTime(LocalTime.now()), calificacion, this.obtenerAutor(nombreAutor), temasLibro, duracion);
        this.catalogo.add(v);
	}

	public void agregarCurso(String titulo, String nombreAutor, Integer calificacion,String fechaPublicacion,Tema[] temas,Double precio,Boolean certificado,Curso.Nivel nivel,Integer clases) {
		List<Tema> temasLibro = Arrays.asList(temas);
		Curso c = new Curso(titulo, LocalDate.parse(fechaPublicacion, formatter).atTime(LocalTime.now()), calificacion, this.obtenerAutor(nombreAutor), temasLibro, precio, certificado, nivel, clases);
        this.catalogo.add(c);
	}
	
	public List<Material> buscarPorTemas(Tema tema) {
		return this.buscarListaMaterial(p-> p.getTemas().indexOf(tema)>=0);
	}
	
	public void imprimirCatalogo() {
		this.catalogo.stream().sorted().forEach(System.out::println);
	}

	public void imprimirAutores() {
		this.autores.stream().forEach(System.out::println);
	}

	
	public Comparator<Material> imprimir(CriterioOrdenamiento criterio) {
		return null;
	}

}