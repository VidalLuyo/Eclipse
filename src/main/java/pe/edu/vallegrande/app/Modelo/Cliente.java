package pe.edu.vallegrande.app.Modelo;

public class Cliente {

	private Integer identifier;
	private String gender,names, last_name, document_type, document_number, email, cellphone, active;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(Integer identifier, String gender,String names, String last_name, String document_type, String document_number,
			String email, String cellphone, String active) {
		super();
		this.identifier = identifier;
		this.gender = gender;
		this.names = names;
		this.last_name = last_name;
		this.document_type = document_type;
		this.document_number = document_number;
		this.email = email;
		this.cellphone = cellphone;
		this.active = active;
	}

	public Cliente(String gender, String names, String last_name, String document_type, String document_number, String email,
			String cellphone, String active) {
		super();
		this.gender = gender;
		this.names = names;
		this.last_name = last_name;
		this.document_type = document_type;
		this.document_number = document_number;
		this.email = email;
		this.cellphone = cellphone;
		this.active = active;
	}

	public Cliente(Integer identifier,String gender, String names, String last_name, String document_type, String document_number,
			String email, String cellphone) {
		super();
		this.identifier = identifier;
		this.gender = gender;
		this.names = names;
		this.last_name = last_name;
		this.document_type = document_type;
		this.document_number = document_number;
		this.email = email;
		this.cellphone = cellphone;
	}

	public Cliente(String gender,String names, String last_name, String document_type, String document_number, String email,
			String cellphone) {
		super();
		this.gender = gender;
		this.names = names;
		this.last_name = last_name;
		this.document_type = document_type;
		this.document_number = document_number;
		this.email = email;
		this.cellphone = cellphone;
	}

	
	
	public Integer getIdentifier() {
		return identifier;
	}

	public String getGender() {
		return gender;
	}

	public String getNames() {
		return names;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getDocument_type() {
		return document_type;
	}

	public String getDocument_number() {
		return document_number;
	}

	public String getEmail() {
		return email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public String getActive() {
		return active;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		String data = "[" + this.identifier;
		data += ", " + this.gender;
		data += ", " + this.names;
		data += ", " + this.last_name;
		data += ", " + this.document_type;
		data += ", " + this.document_number;
		data += ", " + this.email;
		data += ", " + this.cellphone;
		data += ", " + this.active + "]";
		return data;
	}
}