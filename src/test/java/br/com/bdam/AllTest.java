package br.com.bdam;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.bdam.dao.AcessorioDAO;
import br.com.bdam.dao.CarroDAO;
import br.com.bdam.dao.IAcessorioDAO;
import br.com.bdam.dao.ICarroDAO;
import br.com.bdam.dao.IMarcaDAO;
import br.com.bdam.dao.MarcaDAO;
import br.com.bdam.domain.Acessorio;
import br.com.bdam.domain.Carro;
import br.com.bdam.domain.Marca;

public class AllTest {
	
	private IMarcaDAO marcaDAO;
	private IAcessorioDAO acessorioDAO;
	private ICarroDAO carroDAO;
	
	public AllTest() {
		marcaDAO = new MarcaDAO();
		acessorioDAO = new AcessorioDAO();
		carroDAO = new CarroDAO();
	}
	
	@Test
	public void geralMarcaTest() {
		//Teste cadastro Marca
		Marca marca = criaMarca("A1", "Hilux", null);
		Assert.assertNotNull(marca);
		Assert.assertNotNull(marca.getId());
		
		//Teste pesquisarPorCod Marca
		Marca marcaBD = marcaDAO.pesquisarPorCod(marca.getCodigo());
		Assert.assertNotNull(marca);
		Assert.assertEquals(marca.getId(), marcaBD.getId());
		
		//Teste alterar Marca
		marcaBD.setCodigo("A2");
		marcaBD.setNome("Tesla");
		Marca marcaUP = marcaDAO.alterar(marcaBD);
		Assert.assertNotNull(marcaUP);
		Assert.assertEquals("A2", marcaUP.getCodigo());
		Assert.assertEquals("Tesla", marcaUP.getNome());
		
		//Teste excluir Marca
		marcaDAO.exluir(marcaBD);
		Marca marcaEx = marcaDAO.pesquisarPorCod(marcaBD.getCodigo());
		Assert.assertNull(marcaEx);
	}
	
	@Test
	public void geralAcessorioTest() {
		//Teste cadastro Acessorio
		Acessorio acessorio = criaAcessorio("A1", "Adesivo", null);
		Assert.assertNotNull(acessorio);
		Assert.assertNotNull(acessorio.getId());
		
		//Teste pesquisaPorCod Acessorio
		Acessorio acessorioBD = acessorioDAO.pesquisarPorCod(acessorio.getCodigo());
		Assert.assertNotNull(acessorioBD);
		Assert.assertEquals(acessorio.getId(), acessorioBD.getId());
		
		//Teste alterar Acessorio
		acessorioBD.setCodigo("A2");
		acessorioBD.setName("Pisca Alerta");
		Acessorio acessorioUp = acessorioDAO.alterar(acessorioBD);
		Assert.assertEquals("A2", acessorioUp.getCodigo());
		Assert.assertEquals("Pisca Alerta", acessorioUp.getName());
		
		//Teste excluir Acessorio
		acessorioDAO.exluir(acessorioUp);
		Acessorio acessorioEx = acessorioDAO.pesquisarPorCod(acessorioUp.getCodigo());
		Assert.assertNull(acessorioEx);
	}
	
	@Test
	public void geralCarroTest() {
		Marca marca = criaMarca("A1", "HONDA", null);
		Assert.assertNotNull(marca);
		Assert.assertNotNull(marca.getId());
		
		//Teste cadastro Carro
		Carro carro = criaCarro("A1", "HONDA ACCORD", marca, null);
		Assert.assertNotNull(carro);
		Assert.assertNotNull(carro.getId());
		
		//Teste pesquisaProCod Carro
		Carro carroBD = carroDAO.pesquisarPorCod(carro.getCodigo());
		Assert.assertNotNull(carro);
		Assert.assertEquals(carro.getId(), carroBD.getId());

		//Teste alterar Carro
		carroBD.setCodigo("A2");
		carroBD.setNome("HONDA CIVIC");
		Carro carroUp = carroDAO.alterar(carroBD);
		Assert.assertEquals("A2", carroUp.getCodigo());
		Assert.assertEquals("HONDA CIVIC", carroUp.getNome());
		
		//Teste excluir Carro
		carroDAO.exluir(carroUp);
		Carro carroEx = carroDAO.pesquisarPorCod(carroUp.getCodigo());
		Assert.assertNull(carroEx);
		marcaDAO.exluir(marca);
		Marca marcaEx = marcaDAO.pesquisarPorCod(marca.getCodigo());
		Assert.assertNull(marcaEx);
	}
	
	@Test
	public void geralCarroComAcessoriosTest() {
		Marca marca = criaMarca("A3", "HONDA", null);
		Assert.assertNotNull(marca);
		Assert.assertNotNull(marca.getId());
		
		List<Acessorio> acessorios = new ArrayList<>();
		Acessorio acessorio1 = criaAcessorio("A1", "Pisca Alerta", null);
		Acessorio acessorio2 = criaAcessorio("A2", "Adesivo", null);
		acessorios.add(acessorio1);
		acessorios.add(acessorio2);
		Assert.assertNotNull(acessorios);
		Assert.assertEquals(2, acessorios.size());
		
		//Teste cadastro Carro
		Carro carro = criaCarro("A3", "HONDA ACCORD", marca, acessorios);
		Assert.assertNotNull(carro);
		Assert.assertNotNull(carro.getId());
		
		List<Carro> carros = new ArrayList<>();
		carros.add(carro);
		marca.setCarros(carros);
		marcaDAO.alterar(marca);
		for (Acessorio aces : acessorios) {
			aces.setCarros(carros);
			acessorioDAO.alterar(aces);
		}
		
		//Teste pesquisaProCod Carro
		Carro carroBD = carroDAO.pesquisarPorCod(carro.getCodigo());
		Assert.assertNotNull(carro);
		Assert.assertEquals(carro.getId(), carroBD.getId());

		//Teste alterar Carro
		carroBD.setCodigo("A4");
		carroBD.setNome("HONDA CIVIC");
		Carro carroUp = carroDAO.alterar(carroBD);
		Assert.assertEquals("A4", carroUp.getCodigo());
		Assert.assertEquals("HONDA CIVIC", carroUp.getNome());
		
	}
	
	private Marca criaMarca(String codigo, String nome, List<Carro> carros) {
		Marca marca = new Marca();
		marca.setCodigo(codigo);
		marca.setNome(nome);
		marca.setCarros(carros);
		return marcaDAO.cadastrar(marca);
	}
	
	private Acessorio criaAcessorio(String codigo, String nome, List<Carro> carros) {
		Acessorio aces = new Acessorio();
		aces.setCodigo(codigo);
		aces.setName(nome);
		aces.setCarros(carros);
		return acessorioDAO.cadastrar(aces);
	}
	
	private Carro criaCarro(String codigo, String nome, Marca marca, List<Acessorio> acessorios) {
		Carro carro = new Carro();
		carro.setCodigo(codigo);
		carro.setNome(nome);
		carro.setMarca(marca);
		carro.setAcessorios(acessorios);
		return carroDAO.cadastrar(carro);
	}
	
}
