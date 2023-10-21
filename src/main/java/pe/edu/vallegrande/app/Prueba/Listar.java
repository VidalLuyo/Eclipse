package pe.edu.vallegrande.app.Prueba;

import java.util.List;

import pe.edu.vallegrande.app.Modelo.Cliente;
import pe.edu.vallegrande.app.Service.CrudClienteService;


public class Listar {

	public static void main(String[] args) {
		try {
			CrudClienteService userService = new CrudClienteService();
			List<Cliente> lista = userService.get();
			for (Cliente user : lista) {
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}