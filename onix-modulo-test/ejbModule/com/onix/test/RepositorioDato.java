package com.onix.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class RepositorioDato {

	@PersistenceContext(unitName = "TEST")
	private EntityManager lAdministradorEntidad;

	private String lResultado = "";
	private static String lProcedimiento = "{call test(?, ?)}";

	public String insertarDato(final String pNombre) throws Throwable {

		final

		Session lSession = lAdministradorEntidad.unwrap(Session.class);

		lSession.doWork(new Work() {

			public void execute(Connection arg0) throws SQLException {
				CallableStatement lSentencia = null;
				try {
					arg0.setAutoCommit(false);
					lSentencia = arg0.prepareCall(lProcedimiento);
					lSentencia.setString(1, pNombre);
					lSentencia.registerOutParameter(2, java.sql.Types.VARCHAR);

					lSentencia.execute();

					lResultado = lSentencia.getString(2);
					arg0.rollback();
				} finally {
					if (lSentencia != null)
						lSentencia.close();
				}

			}
		});

		return lResultado;
	}

}
