package com.fran.hibernateanotaciones3.utilidades;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class HibernateUtils {
	
	static SessionFactory sessionFactory;
	static Session session;
	
	public static boolean abrirConexion() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		return (session != null);
	}

	public static void cerrarConexion() {
		session.close();
		sessionFactory.close();
	}

	public static void quitarLog() {
		Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
	}
	
	/**
	 * Dado un objeto que le enviamos lo guarda en la base de datos
	 * @param object Objeto enviado
	 * @return True si el guardado es correcto. False si sucede algún fallo
	 */
	public static boolean persist(Object object) {
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.persist(object);
			trans.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return false;
		}		
	}
	
	/**
	 * Dado una lista de Objetos que le enviamos lo guarda en la base de datos
	 * @param objects Lista de Objetos enviados
	 * @return True si el guardado es correcto. False si sucede algún fallo
	 */
	public static boolean persistAll(List<Object> objects) {
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			objects.forEach(e->session.persist(e));
			trans.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return false;
		}		
	}
	
	public static boolean persistAll(Object... objects) {
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			for (int i = 0; i < objects.length; i++) {
				session.persist(objects[i]);
			}
			trans.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return false;
		}		
	}
	
	/**
	 * Dado una clase que le enviamos y un identificador lo busca en la base de datos
	 * @param object Objeto enviado
	 * @return T Objeto obtenido en la consulta
	 */
	public static <T> T getId(Class<T> clase, Object object) {
		return session.get(clase, object);
	}
	
	/**
	 * Dado un objeto que le enviamos lo actualiza de la base de datos
	 * @param object Objeto a actualizar
	 * @return Objeto actualizado
	 */
	public static <T> T merge(T object) {
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			T objeto = session.merge(object);
			trans.commit();
			return objeto;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return null;
		}
	}
	
	public static boolean remove(Object object) {
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.remove(object);
			trans.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return false;
		}
	}
	
	/**
	 * Devuelve una lista con todos los objetos de una clase
	 * @param <T>	Tipo de la clase
	 * @param clase Clase de la que se quieren obtener los objetos
	 * @return List<T> Lista de objetos
	 */
	public static <T> List<T> getAll(Class<T> clase) {
        return session.createQuery("FROM " + clase.getName(), clase).list();
    }
	
    /**
     * Devuelve un objeto de una clase por su id
     * @param <T> Tipo de la clase
     * @param clase	Clase de la que se quiere obtener el objeto
     * @param id	Id del objeto
     * @return	Objeto obtenido
     */
	public static <T> T getById(Class<T> clase, int id) {
		return session.get(clase, id);
	}
	
	/**
	 * Devuelve una lista de objetos de una clase que cumplan una condición
	 * @param <T> Tipo de la clase
	 * @param query Consulta que se quiere realizar
	 * @param clase Clase de la que se quieren obtener los objetos
	 * @return List<T> Lista de objetos
	 */
	public static <T> List<T> getQuery(String query, Class<T> clase) {
		return session.createQuery(query, clase).list();
	}

	/**
	 * Devuelve una lista de objetos de una clase que cumplan una condición
	 * 
	 * @param <T>        Tipo de la clase
	 * @param query      Consulta que se quiere realizar
	 * @param clase      Clase de la que se quieren obtener los objetos
	 * @param parametros Parámetros de la consulta
	 * @return List<T> Lista de objetos
	 */
	public static <T> List<T> nativeQuery(String string, Class<T> clase, HashMap<String, Object> parametros) {
		NativeQuery<T> nativeQuery =  session.createNativeQuery(string, clase);
			if (parametros != null)
				parametros.forEach((k,v)->nativeQuery.setParameter(k, v));
		return nativeQuery.list();		
	}
	
	/** 
	 * Devuelve un objeto de una clase que cumplan una condición
	 * @param <T>
	 * @param string
	 * @param clase
	 * @param parametros
	 * @return
	 */
	public static <T> T nativeQueryById(String string, Class<T> clase, HashMap<String, Object> parametros) {
		NativeQuery<T> nativeQuery =  session.createNativeQuery(string, clase);
			if (parametros != null)
				parametros.forEach((k,v)->nativeQuery.setParameter(k, v));
		return nativeQuery.getSingleResult();		
	}
	
	/**
	 * Devuelve una lista de objetos de una clase que cumplan una condición
	 * @param <T>
	 * @param namedQuery
	 * @param clase
	 * @param parametros
	 * @return
	 */
	public static <T> List<T> namedQuery(String namedQuery, Class<T> clase, HashMap<String, Object> parametros) {
		Query<T> query = session.createNamedQuery(namedQuery,clase);
		if (parametros != null)
			parametros.forEach((k, v) -> query.setParameter(k, v));
		return query.getResultList();
	}
	
	/**
	 * Devuelve un objeto de una clase que cumplan una condición
	 * @param <T>
	 * @param namedQuery
	 * @param clase
	 * @param parametros
	 * @return
	 */
	public static <T> T namedQueryById(String namedQuery, Class<T> clase, HashMap<String, Object> parametros) {
		Query<T> query = session.createNamedQuery(namedQuery,clase);
		if (parametros != null)
			parametros.forEach((k, v) -> query.setParameter(k, v));
		return query.getSingleResult();
	}
	
	

}
