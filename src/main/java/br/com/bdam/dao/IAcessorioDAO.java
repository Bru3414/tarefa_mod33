package br.com.bdam.dao;

import br.com.bdam.domain.Acessorio;

public interface IAcessorioDAO {
	
	public Acessorio cadastrar(Acessorio acessorio);
	
	public Acessorio pesquisarPorCod(String codigo);
	
	public Acessorio alterar(Acessorio acessorio);
	
	public void exluir(Acessorio acessorio);

}
