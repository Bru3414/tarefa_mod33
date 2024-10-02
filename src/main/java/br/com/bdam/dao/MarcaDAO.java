package br.com.bdam.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.bdam.domain.Marca;

public class MarcaDAO implements IMarcaDAO {

	@Override
	public Marca cadastrar(Marca marca) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TarefaMod33");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(marca);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return marca;
	}

	@Override
	public Marca pesquisarPorCod(String codigo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TarefaMod33");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Marca> query = builder.createQuery(Marca.class);
		
		Root<Marca> root = query.from(Marca.class);
	
		query.select(root).where(builder.equal(root.get("codigo"), codigo));
		
		TypedQuery<Marca> tpQuery = em.createQuery(query);
		
		Marca marca;
		try {
			 marca = tpQuery.getSingleResult();
		} catch (NoResultException e) {
			marca = null;
		}
		
		em.close();
		emf.close();
		
		return marca;
	}

	@Override
	public void exluir(Marca marca) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TarefaMod33");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		marca = em.merge(marca);
		em.remove(marca);
		em.getTransaction().commit();
		 
		em.close();
		emf.close();
	}

	@Override
	public Marca alterar(Marca marca) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TarefaMod33");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		marca = em.merge(marca);
		em.getTransaction().commit();
		
		em.close();
		emf.close();

		return marca;
	}

}
