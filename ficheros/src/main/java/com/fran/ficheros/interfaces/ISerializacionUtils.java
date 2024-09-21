package com.fran.ficheros.interfaces;

import com.fran.ficheros.entidades.Alumno;

public interface ISerializacionUtils {
	
	public boolean serializarObjeto(String rutaCompleta, Alumno alumno);
	
}
