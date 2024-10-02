package br.com.bdam.dao;

import br.com.bdam.domain.Carro;

public interface ICarroDAO {
	
	public Carro cadastrar(Carro carro);
	
	public Carro pesquisarPorCod(String codigo);
	
	public Carro alterar(Carro carro);
	
	public void exluir(Carro carro);
	}
