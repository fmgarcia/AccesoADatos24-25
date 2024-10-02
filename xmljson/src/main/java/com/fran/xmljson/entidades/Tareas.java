package com.fran.xmljson.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tareas {

	private long userId;
	private long id;
	private String title;
	private boolean completed;
}
