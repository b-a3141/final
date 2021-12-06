package services;

import java.util.List;
import model.Promocion;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;

public class PromocionnService {

	public List<Promocion> list() {
		return DAOFactory.getPromocionDAO().findAll();
	}

	public Promocion create(String nombre, String tipo_de_Promocion, Integer descuento) {

		Promocion promocion = new Promocion(-1, nombre, tipo_de_Promocion, descuento);

		if (promocion.isValid()) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			promocionDAO.insert(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}

	public Promocion update(Integer id, String nombre, String tipo_de_Promocion, Integer descuento) {

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);

		promocion.setNombre(nombre);
		promocion.setTipoDePromocion(tipo_de_Promocion);
		promocion.setDesceunto(descuento);
	

		if (promocion.isValid()) {
			promocionDAO.update(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}

	public void delete(Integer id) {
		Promocion promocion = new Promocion(id, null, null, null);

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.delete(promocion);
	}

	public Promocion find(Integer id) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		return promocionDAO.find(id);
	}

}
