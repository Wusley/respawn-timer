package br.com.respawntimer.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class GenericDAO<T> {
	
	private final SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
    private final Class<T> classe;

	public GenericDAO(SessionFactory sessionFactory, final Class<T> classe) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
        
        this.classe = classe;
	}
	
	public void adiciona(final T t) {
		try {
			
			getSession();

			transaction = session.beginTransaction();
			session.save(t);
			transaction.commit();
			
		} catch(HibernateException e) {
			
			System.out.println("Erro: " + e.getMessage());
			transaction.rollback();
			
		} finally {
			
			try {
				
				if(session.isOpen()) {
					
					session.close();
					
				}
				
			} catch(Throwable e) {
				
				System.out.println("Erro ao finalizar insercao");
				
			}
			
		}

	}

	public void atualiza(final T t) {
		try {
			
			getSession();

			transaction = session.beginTransaction();
			session.update(t);
			transaction.commit();
			
		} catch(HibernateException e) {
			
			System.out.println("Erro: " + e.getMessage());
			transaction.rollback();
			
		} finally {
			
			try {
				
				if(session.isOpen()) {
					
					session.close();
					
				}
				
			} catch(Throwable e) {
				
				System.out.println("Erro ao finalizar update");
				
			}
			
		}
	}
	
	// selecionar por id
	@SuppressWarnings("unchecked")
	public T busca(long id) {	
		try {
			
			getSession();
			
			Criteria criteria = session.createCriteria(classe);
			criteria.add(Restrictions.eq("id",id));
			
			T t = (T) criteria.uniqueResult();
			
			return t;
			
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
		
	public void getSession() {
		
		if(!session.isOpen()) {
			
			this.session = sessionFactory.openSession();
			
		}
		
	}
}