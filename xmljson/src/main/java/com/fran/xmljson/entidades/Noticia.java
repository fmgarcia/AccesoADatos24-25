package com.fran.xmljson.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Noticia {
	
	private String titulo;
	private String guid;
	private String imagen;

}
