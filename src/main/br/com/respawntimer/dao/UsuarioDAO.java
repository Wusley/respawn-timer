package br.com.respawntimer.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import br.com.respawntimer.model.Usuario;

public class UsuarioDAO {
	
	private SessionFactory sessionFactory;
	private Session session;
	
	private GenericDAO<Usuario> dao;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
		
		this.dao = new GenericDAO<Usuario>(sessionFactory, Usuario.class);
	}
	
	public boolean adiciona(final Usuario usuario) {
		
		if(!existeNick(usuario.getNick()) && !existeEmail(usuario.getEmail())) {

			dao.adiciona(usuario);
			
			return true;
			
		} 

		return false;
		
	}
	
	// busca por id
	public Usuario busca(long id) {
		return dao.busca(id);
	}
	
	// recebendo usuario valido pelo e-mail
	public Usuario existeUsuario(String email) {
		Usuario usuario = null;
		
		try {
			
			if(!session.isOpen()) {
				this.session = sessionFactory.openSession();
			}
			
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.eq("email", email));
			
			usuario = (Usuario) criteria.uniqueResult();
			
			return usuario;
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		}
		
		return usuario;
	}
	
	// verificando nick ja existente
	public boolean existeNick(String nick) {		
		try {
			if(!session.isOpen()) {
				this.session = sessionFactory.openSession();
			}
			
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.eq("nick", nick));
			
			if((Usuario) criteria.uniqueResult() != null) {
				
				return true;	
				
			} else {
				
				return false;
				
			}
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		}
		
		return false;
		
	}
	
	// verificando email ja existente
	public boolean existeEmail(String email) {		
		try {
			if(!session.isOpen()) {
				this.session = sessionFactory.openSession();
			}
			
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.eq("email", email));
			
			if((Usuario) criteria.uniqueResult() != null) {
				
				return true;	
				
			} else {
				
				return false;
				
			}
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		}
		
		return false;
		
	}
}