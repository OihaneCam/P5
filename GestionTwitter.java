package Practica_5;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class GestionTwitter {
	
	//Creación del mapas
	private static HashMap<String, UsuarioTwitter> mapaId = new HashMap<String, UsuarioTwitter>();
	private static HashMap<String, UsuarioTwitter> mapaNick = new HashMap<String, UsuarioTwitter>();
	private static TreeSet<UsuarioTwitter> treeFriends = new TreeSet<UsuarioTwitter>();
	
	static String usuariosEnMap;
	static String usuariosEnTree;
	
	//Métodos para cargar mapas
	public static void cargarMapaId(String id, UsuarioTwitter u) {
		if(!mapaId.containsKey(id)) {
			mapaId.put(id, u);
		}
		
	}
	
	public static void cargaMapaNick(String screenName, UsuarioTwitter u) {
		if(!mapaNick.containsKey(screenName)) {
			mapaNick.put(screenName, u);
		}
		
	}
	
	//Creación de treeshet
	public static void cargaTree(UsuarioTwitter usuario) {
		if(!treeFriends.contains(usuario)) {
			treeFriends.add(usuario);
		}
		//System.out.println(treeFriends);

		
	}
	
	public static void amigosConAmigosDentro(Map<String, UsuarioTwitter> usuariosPorNick) {
		int usuariosConAmigosDentro = 0;
		
		for(UsuarioTwitter u: usuariosPorNick.values()) {
			int amigosDentro = 0;
			int amigosFuera = 0;
			if(u.getFriends() != null) {
				for(String id: u.getFriends()) {
					if(mapaId.containsKey(id)) {
						amigosDentro++;
					}else {
						amigosFuera++;
					}
				}
			}
			if(amigosDentro > 0) {
				usuariosConAmigosDentro++;
				System.out.println("Usuario " + u.getScreenName() + " tiene " + amigosFuera + " amigos fuera de nuestro sistema y " + amigosDentro + " dentro.");
			}
			u.setFriendsEnSistema(amigosFuera);
		}
		System.out.println("Hay " + usuariosConAmigosDentro + " con algunos amigos dentro de nuestro sistema.");
		
		for(UsuarioTwitter u: mapaNick.values()) {
			if(u.getFriendsEnSistema() > 0) {
				treeFriends.add(u);
			}
		}
		
		for(UsuarioTwitter u: getTree()) {
			System.out.println(u.getScreenName() + " - " + u.getFriendsEnSistema() + " amigos.");
		}
	}
	
	public static TreeSet<UsuarioTwitter> getTree(){
		return treeFriends;
	}

	public static void main(String[] args) {
		try {
			// TODO Configurar el path y ruta del fichero a cargar
			String fileName = "C:\\Users\\Oihane\\eclipse-workspace\\Programacion_III\\src\\Practica_5\\data.csv";
			CSV.processCSV( new File( fileName ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
