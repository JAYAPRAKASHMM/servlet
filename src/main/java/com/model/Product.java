package com.model;

public class Product {
public Product(String name, int rs, int quantity) {
		super();
		this.name = name;
		this.rs = rs;
		this.quantity = quantity;
	}
int id;
String name;
int rs;
int quantity;
public int getId() {
	return id;
}
public Product(int id, String name, int rs) {
	super();
	this.id = id;
	this.name = name;
	this.rs = rs;
}
public Product() {
	super();
}
public Product(String name, int rs) {
	super();
	this.name = name;
	this.rs = rs;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getRs() {
	return rs;
}
public void setRs(int rs) {
	this.rs = rs;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
@Override
public String toString()
{
	return this.id+" "+ this.name +" "+ this.rs;
}
}
