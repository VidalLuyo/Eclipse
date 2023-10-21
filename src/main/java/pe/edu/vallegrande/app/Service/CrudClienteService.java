package pe.edu.vallegrande.app.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.vallegrande.app.Modelo.Cliente;
import pe.edu.vallegrande.app.Service.spec.CrudServiceSpec;
import pe.edu.vallegrande.app.db.AccesoDB;
import pe.edu.vallegrande.app.Service.spec.RowMapper;


public class CrudClienteService implements CrudServiceSpec<Cliente>, RowMapper<Cliente> {

	private final String SQL_SELECT_ACTIVE = "SELECT * FROM client WHERE active = 'A'";
	private final String SQL_SELECT = "SELECT * FROM client";
	private final String SQL_SELECT_INACTIVE = "SELECT * FROM Cliente_inactive";
	private final String SQL_INSERT = "INSERT INTO Cliente (gender, names, last_name, document_type, document_number, email, cellphone) VALUES (?,?,?,?,?,?,?)";
	private final String SQL_UPDATE = "UPDATE Cliente SET gender=?, names=?, last_name=?, document_type=?, document_number=?, email=?, cellphone=? WHERE identifier=?";
	private final String SQL_DELETE = "UPDATE Cliente SET active='I' WHERE identifier=?";
	private final String SQL_RESTORE = "UPDATE Cliente SET active='A' WHERE identifier=?";
	private final String SQL_ELIMINATE = "DELETE FROM Cliente WHERE identifier=?";

	@Override
	public List<Cliente> getActive() {
		List<Cliente> lista = new ArrayList<>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs;
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_SELECT_ACTIVE);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Cliente bean = mapRow(rs);
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@Override
	public List<Cliente> get() {
		List<Cliente> lista = new ArrayList<>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs;
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_SELECT);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Cliente bean = mapRow(rs);
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Cliente> getInactive() {
		List<Cliente> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_INACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				Cliente bean = mapRow(rs);
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Cliente getForId(String identifier) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Cliente bean = null;
		String sql;
		try {
			cn = AccesoDB.getConnection();
			sql = SQL_SELECT_ACTIVE + " WHERE identifier=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(identifier));
			rs = pstm.executeQuery();
			if(rs.next()) {
				bean = mapRow(rs);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
		return bean;
	}

	@Override
	public List<Cliente> get(Cliente bean) {
		Connection cn = null;
		List<Cliente> lista = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Cliente item;
		String names, last_name, sql;
		names = "%" + UtilService.setStringVacio(bean.getNames()) + "%";
		last_name = "%" + UtilService.setStringVacio(bean.getLast_name()) + "%";
		try {
			cn = AccesoDB.getConnection();
			sql = SQL_SELECT_ACTIVE + " WHERE names LIKE ? AND last_name LIKE ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, names);
			pstm.setString(2, last_name);
			rs = pstm.executeQuery();
			while(rs.next()) {
				item = mapRow(rs);
				lista.add(item);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
		return lista;
	}

	@Override
	public void insert(Cliente bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_INSERT);
			pstm.setString(1, bean.getGender());
			pstm.setString(2, bean.getNames());
			pstm.setString(3, bean.getLast_name());
			pstm.setString(4, bean.getDocument_type());
			pstm.setString(5, bean.getDocument_number());
			pstm.setString(6, bean.getEmail());
			pstm.setString(7, bean.getCellphone());
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("Error, verifique sus datos e intentelo nuevamente.");
			}
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
				cn.close();
			} catch (Exception e2) {
			}
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void update(Cliente bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_UPDATE);
			pstm.setString(1, bean.getGender());
			pstm.setString(2, bean.getNames());
			pstm.setString(3, bean.getLast_name());
			pstm.setString(4, bean.getDocument_type());
			pstm.setString(5, bean.getDocument_number());
			pstm.setString(6, bean.getEmail());
			pstm.setString(7, bean.getCellphone());
			pstm.setInt(8, bean.getIdentifier());
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("Error, verifique sus datos e intentelo nuevamente.");
			}
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
				cn.close();
			} catch (Exception e2) {
			}
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void delete(String identifier) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			// Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_DELETE);
			pstm.setInt(1, Integer.parseInt(identifier));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo eliminar el usuario.");
			}
			cn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public void restore(String identifier) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			// Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_RESTORE);
			pstm.setInt(1, Integer.parseInt(identifier));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo restaurar el usuario.");
			}
			cn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public void eliminate(String identifier) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			// Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_ELIMINATE);
			pstm.setInt(1, Integer.parseInt(identifier));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo eliminar el usuario.");
			}
			cn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public Cliente mapRow(ResultSet rs) throws SQLException {
		Cliente bean = new Cliente();
		bean.setIdentifier(rs.getInt("identifier"));
		bean.setGender(rs.getString("gender"));
		bean.setNames(rs.getString("names"));
		bean.setLast_name(rs.getString("last_name"));
		bean.setDocument_type(rs.getString("document_type"));
		bean.setDocument_number(rs.getString("document_number"));
		bean.setEmail(rs.getString("email"));
		bean.setCellphone(rs.getString("cellphone"));
		bean.setActive(rs.getString("active"));
		return bean;
	}

}