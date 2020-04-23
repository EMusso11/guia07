package utn.frsf.isi.died2020.tp07.modelo;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.time.LocalDate;

import org.junit.Test;

public class CursoTest {

	@Test
	public void testPrecio() {
		
		DecimalFormat df = new DecimalFormat("###.##");
		
		Curso c1 = new Curso();
		c1.setClases(5);
		c1.setPrecioBase(4.0);
		c1.setCertificado(false);
		c1.setNivel(utn.frsf.isi.died2020.tp07.modelo.Curso.Nivel.BASE);
		Boolean esperado = (c1.precio() == 20.0);  
		assertTrue(esperado);
		
		c1.setNivel(utn.frsf.isi.died2020.tp07.modelo.Curso.Nivel.AVANZADO);
		esperado = (c1.precio() == 22.0);  
		assertTrue(esperado);
		
		c1.setNivel(utn.frsf.isi.died2020.tp07.modelo.Curso.Nivel.EXPERTO);
		esperado = (c1.precio() == 24.0);  
		assertTrue(esperado);
		
		c1.setCertificado(true);
		c1.setNivel(utn.frsf.isi.died2020.tp07.modelo.Curso.Nivel.BASE);
		esperado = (c1.precio() == 22.0);  
		assertTrue(esperado);
		
		c1.setNivel(utn.frsf.isi.died2020.tp07.modelo.Curso.Nivel.AVANZADO);
		esperado = (c1.precio() == 24.2); //truncar decimales  
		System.out.println(c1.precio());
		assertTrue(esperado);
		
		c1.setNivel(utn.frsf.isi.died2020.tp07.modelo.Curso.Nivel.EXPERTO);
		esperado = (c1.precio() == 26.4);  
		assertTrue(esperado);
		
	}

}