package utn.frsf.isi.died2020.tp07.modelo;

import static org.junit.Assert.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Test;

public class MaterialTest {

	@Test
	public void testCompareTo() {

		Libro l1 = new Libro();
		l1.setTitulo("abc");
		l1.setFechaPublicacion(LocalDate.of(2019, 12, 10).atStartOfDay());
		l1.setAutor(new Autor("martin"));
		
		Libro l2 = new Libro();
		l2.setTitulo("ABC");
		l2.setFechaPublicacion(LocalDate.of(2020, 12, 10).atStartOfDay());
		l2.setAutor(new Autor("martin"));
		
		Libro l3 = new Libro();
		l3.setTitulo("ZZZ");
		l3.setFechaPublicacion(LocalDate.of(2022, 12, 10).atStartOfDay());
		l3.setAutor(new Autor("martin"));
		
		Integer esperado1 = l1.compareTo(l2);
		assertTrue(esperado1>0);

		Integer esperado2 = l1.compareTo(l3);
		assertTrue(esperado2<0);

		Integer esperado3 = l2.compareTo(l3);
		assertTrue(esperado3<0);

		Integer esperado4 = l2.compareTo(l1);
		assertTrue(esperado4<0);
		
		Integer esperado5 = l3.compareTo(l1);
		assertTrue(esperado5>0);
		
		Integer esperado6 = l3.compareTo(l2);
		assertTrue(esperado6>0);


	}

}
