package br.com.bdam.dao;

import br.com.bdam.domain.Marca;

public interface IMarcaDAO {
	
	public Marca cadastrar(Marca marca);
	
	public Marca pesquisarPorCod(String codigo);
	
	public Marca alterar(Marca marca);
	
	public void exluir(Marca marca);

}
