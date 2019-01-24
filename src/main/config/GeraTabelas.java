package config;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.com.respawntimer.model.Respawn;
import br.com.respawntimer.model.Usuario;

public class GeraTabelas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.addAnnotatedClass(Usuario.class);
		cfg.addAnnotatedClass(Respawn.class);
		
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}
}