package com.onix.servicio;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

import com.onix.test.RepositorioDato;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicioInvocacionProcedimientoAlmacenado implements IServicioInvocacionProcedimientoAlmacenado {
	@EJB
	private RepositorioDato lRepositorioDato;

	@Resource
	private UserTransaction lAdministradoTransaccion;

	/* (non-Javadoc)
	 * @see com.onix.servicio.IServicioInvocacionProcedimientoAlmacenado#invocarProcedimientoAlmacenado(java.lang.String)
	 */
	public String invocarProcedimientoAlmacenado(String pNombre) throws Throwable {
		String resultado = "";
		lAdministradoTransaccion.begin();
		resultado = lRepositorioDato.insertarDato(pNombre);
		lAdministradoTransaccion.commit();
		return resultado;

	}

}
