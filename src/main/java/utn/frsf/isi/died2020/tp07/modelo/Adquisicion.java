package utn.frsf.isi.died2020.tp07.modelo;

import java.time.LocalDateTime;

public class Adquisicion {

	private Material material;
	private LocalDateTime fecha;
	private Boolean pagado;
	private Double precio;
	private Boolean debitado;

	public Adquisicion() {}
	
	public Adquisicion(Material material, LocalDateTime fecha, Double precio) {
		super();
		this.material = material;
		this.fecha = fecha;
		this.precio = precio;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Boolean getPagado() {
		return pagado;
	}

	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
}
