package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Promocion;
import persistence.PromocionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class PromocionDAOImpl implements PromocionDAO {

	

	private Promocion toPromocion(ResultSet promocionRegister) throws SQLException {
		return new Promocion(promocionRegister.getInt(1), promocionRegister.getString(2),
				promocionRegister.getString(3), promocionRegister.getInt(4));
	}
	
	
	@Override
	public List<Promocion> findAll() {
		try {
			String sql = "SELECT * FROM promociones  ORDER BY cost DESC, duration";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promociones = new LinkedList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromocion(resultados));
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Promocion find(Integer id) {
		try {
			String sql = "SELECT * FROM promociones WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;
			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	@Override
	public int insert(Promocion promocion) {
		try {
			String sql = "INSERT INTO promociones (nombre, tipo_de_Promocion, descuento)"
					+ " VALUES (?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, promocion.getNombre());
			statement.setString(i++, promocion.getTipoDePromocion());
			statement.setInt(i++, promocion.getDescuento());
		
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	
	
	@Override
	public int update(Promocion promocion) {
		try {
			String sql = "UPDATE promociones SET nombre = ?, tipo_de_Promocion = ?, descuento = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, promocion.getNombre());
			statement.setString(i++, promocion.getTipoDePromocion());
			statement.setInt(i++, promocion.getDescuento());
		
			
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	@Override
	public int delete(Promocion promocion) {
		try {
			String sql = "DELETE FROM promociones WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}


	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM promociones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	

	

}
