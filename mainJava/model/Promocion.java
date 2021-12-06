package model;

import java.util.HashMap;
import java.util.Map;

public class Promocion {

	private Integer id;
	private String nombre;
	private String tipoDePromocion;
	private Integer descuento;
	private Map<String, String> errors;
	
	public Promocion(Integer id, String nombre, String tipoDePromocion,  Integer descuento) {
		
		this.id = id;
		this.nombre = nombre;
		this.tipoDePromocion = tipoDePromocion;
		this.descuento = descuento;
		
	}
	
	

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (!tipoDePromocion.equals("AxB") ||
				!tipoDePromocion.equals("porcentual") ||
				!tipoDePromocion.equals("absoluta")) {
			errors.put("Tipo de Promocion", "Debe ser AxB, porcentual o  absoluta ");
		}
		if (descuento <= 0) {
			errors.put("descuento", "Debe ser positivo y entero");
		}
		
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getDescuento() {
		return this.descuento;
	}

	public void setDesceunto(Integer desc) {
		this.descuento = desc;
	}

	public String getTipoDePromocion() {
		return tipoDePromocion;
	}

	public void setTipoDePromocion(String tipoDePromocion) {
		this.tipoDePromocion = tipoDePromocion;
	}

	
	@Override
	public String toString() {
		return "Promocion [id=" + this.id + ", nombre=" + 
	    this.nombre + ", Tipo de promoción=" + this.tipoDePromocion + ", descuento=" + this.descuento  + " ]";
	}

	
	
}
