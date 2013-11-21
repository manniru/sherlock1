package com.zahra.sherlock;

public class Person {

    // private variables
    public int _id;
    public String _name;
    public String _age;
    public String _email;
    
    public String _gender;
    public String _height;
    public String _haircolor;
    public String _comments;

    public Person() {
    }
    
    // mannir constructor
    // constructor
    public Person(int id, String name, String _age, String _email, 
    		String gender, String height, String haircolor, String comments) {
	this._id = id;
	this._name = name;
	this._age = _age;
	this._email = _email;
	
	this._gender = gender;
	this._height = height;
	this._haircolor = haircolor;
	this._comments = comments;

    }
    
    public Person(String name, String _age, String _email, 
    		String gender, String height, String haircolor, String comments) {
	//this._id = id;
	this._name = name;
	this._age = _age;
	this._email = _email;
	
	this._gender = gender;
	this._height = height;
	this._haircolor = haircolor;
	this._comments = comments;

    }
    /**
    // constructor
    public Contact(int id, String name, String _age, String _email) {
	this._id = id;
	this._name = name;
	this._age = _age;
	this._email = _email;

    }
   

    // constructor
    public Contact(String name, String _age, String _email) {
	this._name = name;
	this._age = _age;
	this._email = _email;
    }
*/
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_age() {
		return _age;
	}

	public void set_age(String _age) {
		this._age = _age;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public String get_gender() {
		return _gender;
	}

	public void set_gender(String _gender) {
		this._gender = _gender;
	}

	public String get_height() {
		return _height;
	}

	public void set_height(String _height) {
		this._height = _height;
	}

	public String get_haircolor() {
		return _haircolor;
	}

	public void set_haircolor(String _haircolor) {
		this._haircolor = _haircolor;
	}

	public String get_comments() {
		return _comments;
	}

	public void set_comments(String _comments) {
		this._comments = _comments;
	}

 

}