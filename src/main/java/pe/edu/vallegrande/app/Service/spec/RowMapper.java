package pe.edu.vallegrande.app.Service.spec;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

	T mapRow(ResultSet rs) throws SQLException;
	
}