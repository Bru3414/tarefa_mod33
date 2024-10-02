package br.com.bdam.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.bdam.domain.Acessorio;

public class AcessorioDAO implements IAcessorioDAO {
	

	@Override
	public Acessorio cadastrar(Acessorio acessorio) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TarefaMod33");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(acessorio);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return acessorio;
	}

	@Override
	public Acessorio pesquisarPorCod(String codigo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TarefaMod33");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Acessorio> query = builder.createQuery(Acessorio.class);
		
		Root<Acessorio> root = query.from(Acessorio.class);
		
		query.select(root).where(builder.equal(root.get("codigo"), codigo));	
		
		TypedQuery<Acessorio> tpQuery = em.createQuery(query);
		
		Acessorio acessorio;
		try {
			acessorio = tpQuery.getSingleResult();
		} catch (NoResultException e) {
			acessorio = null;
		}
		
		em.close();
		emf.close();
		
		return acessorio;
	}

	@Override
	public Acessorio alterar(Acessorio acessorio) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TarefaMod33");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		acessorio = em.merge(acessorio);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return acessorio;
	}

	@Override
	public void exluir(Acessorio acessorio) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TarefaMod33");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		acessorio = em.merge(acessorio);
		em.remove(acessorio);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}

}
