package com.example.filtercustomadapterdemo;

public class Item {
	
	private String _name;
	private String _description;
	
	public Item(String name, String description){
		this._name=name;
		this._description=description;
	}
	public void SetName(String name){
		this._name=name;		
	}
	public void SetDescription(String description){
		this._description=description;
	}
	public String getName(){
		return this._name;
	}
	public String getDescription(){
		return this._description;
	}

}
