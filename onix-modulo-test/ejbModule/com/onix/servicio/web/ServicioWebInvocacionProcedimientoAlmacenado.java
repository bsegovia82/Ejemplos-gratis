package com.onix.servicio.web;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.onix.servicio.IServicioInvocacionProcedimientoAlmacenado;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@WebService
public class ServicioWebInvocacionProcedimientoAlmacenado {
	@EJB
	private IServicioInvocacionProcedimientoAlmacenado lServicio;

	public 
	@WebResult(name="RESULTADO")
	String invocacionProcedimiento(
			@WebParam(name="P_NOMBRE")
			String pNombre) {
		try {
			return lServicio.invocarProcedimientoAlmacenado(pNombre);
		} catch (Throwable pError) {
			pError.printStackTrace();
			return "Error al realizar la accion";
		}
	}

}
