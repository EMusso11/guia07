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
		
}
