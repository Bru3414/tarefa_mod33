package br.com.bdam.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.bdam.domain.Carro;

public class CarroDAO implements ICarroDAO {

	@Override
	public Carro cadastrar(Carro carro) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TarefaMod33");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(carro);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return carro;
		
	}

	@Override
	public Carro pesquisarPorCod(String codigo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TarefaMod33");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder  builder = em.getCriteriaBuilder();
		CriteriaQuery<Carro> query = builder.createQuery(Carro.class);
		
		Root<Carro> root = query.from(Carro.class);
		
		query.select(root).where(builder.equal(root.get("codigo"), codigo));
		
		TypedQuery<Carro> tpQuery = em.createQuery(query);
		
		Carro carro;
		try {
			carro = tpQuery.getSingleResult();
		} catch (NoResultException e) {
			carro = null;
		}
		
		em.close();
		emf.close();
		
		return carro;
	}

	@Override
	public Carro alterar(Carro carro) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TarefaMod33");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		carro = em.merge(carro);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return carro;
	}

	@Override
	public void exluir(Carro carro) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TarefaMod33");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();	
		carro = em.merge(carro);
		em.remove(carro);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}	

}
