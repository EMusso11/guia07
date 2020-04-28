package utn.frsf.isi.died2020.tp07.servicios;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import utn.frsf.isi.died2020.tp07.modelo.Curso;
import utn.frsf.isi.died2020.tp07.modelo.Libro;
import utn.frsf.isi.died2020.tp07.modelo.Usuario;
import utn.frsf.isi.died2020.tp07.modelo.Video;

public class Registro {
	
	public enum Plan { GRATIS,BASE,PREMIUM };
	private Set<Usuario> usuarios = new LinkedHashSet<>();
	
	public void registrarGratuito(String nombre) {
		Usuario aux = new Usuario();
		aux.setCorreoElectronico(nombre);
		aux.setPuedeAdquirirLibro(u -> u.getAdquisiciones().size()<1);
		aux.setPuedeAdquirirVideo(u -> u.minutos()<30);
		aux.setPuedeAdquirirCurso(u -> false);
		
		aux.setCostoCurso((u, c) -> 0.0);
		aux.setCostoVideo((u, c) -> 0.0);
		aux.setCostoLibro((u, c) -> 0.0);
		this.usuarios.add(aux);
	}
	
	public void registrarBase(String nombre) {
		Usuario aux = new Usuario();
		aux.setCorreoElectronico(nombre);
		aux.setPuedeAdquirirLibro(u -> u.getAdquisiciones().size()<1);
		aux.setPuedeAdquirirVideo(u -> u.minutos()<30);
		aux.setPuedeAdquirirCurso(u -> false);
		
		aux.setCostoCurso((u, c) -> 0.0);
		aux.setCostoVideo((u, c) -> 0.0);
		aux.setCostoLibro((u, c) -> 0.0);
		this.usuarios.add(aux);
	}
	
	public void registrarPremium(String nombre, String tarjeta) {
		Usuario aux = new Usuario();
		aux.setCorreoElectronico(nombre);
		aux.setPuedeAdquirirLibro(u -> true);
		aux.setPuedeAdquirirVideo(u -> true);
		aux.setPuedeAdquirirCurso(u -> true);
		
		aux.setCostoCurso((u, c) -> u.cursosAdquiridos()<2 ? 0.0 : c.precio()*0.8);
		aux.setCostoLibro((u, l) -> u.librosAdquiridos()<25 ? 0.0 : l.costo(u)/50 * 0.10);
		aux.setCostoVideo((u, v) -> u.minutos()<2500 ? 0.0 : v.getDuracion()/60 * 1.10);
		this.usuarios.add(aux);
	}
		
}
