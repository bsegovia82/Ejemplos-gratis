package com.onix.servicio;

import javax.ejb.Remote;

@Remote
public interface IServicioInvocacionProcedimientoAlmacenado {

	public 
	String invocarProcedimientoAlmacenado(String pNombre)
			throws Throwable;

}