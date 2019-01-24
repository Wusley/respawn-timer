package br.com.respawntimer.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import br.com.respawntimer.model.Respawn;
import br.com.respawntimer.model.Usuario;

public class RespawnDAO {
	
	private SessionFactory sessionFactory;
	private Session session;
	
	private GenericDAO<Respawn> dao;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
		
		this.dao = new GenericDAO<Respawn>(sessionFactory, Respawn.class);
	}
	
	public boolean adiciona(final Respawn respawn) {
		dao.adiciona(respawn);
			
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Respawn> listaRespawnTo(Usuario usuario) {
		
		try {
			
			if(!session.isOpen()) {
				
				session = sessionFactory.openSession();
				
			}
			
			Criteria criteria = session.createCriteria(Respawn.class);
						
			criteria.add(Restrictions.like("status", true));
						
			criteria.add(Restrictions.like("usuario", usuario));
								
			List<Respawn> list = criteria.list();
						
			return list;
			
		} catch(HibernateException e) {
			
			System.out.println("Erro: " + e.getMessage());
			
		} finally {
			
			try {
				
				if(session.isOpen()) {
					
					session.close();
					
				}
				
			} catch(Throwable e) {
				
				System.out.println("Erro ao finalizar a busca");
				
			}
			
		}
		
		return null;
		
	}
	
	// busca por id
	public void atualiza(final Respawn respawn) {
		dao.atualiza(respawn);
	}
	
	// busca por id
	public Respawn busca(long id) {
		return dao.busca(id);
	}
}