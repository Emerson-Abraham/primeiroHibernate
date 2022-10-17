package br.com.hibernate;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CriaTabelas {

	public static void main(String[] args) {

		//inicia 
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("produtos");
		EntityManager manager = factory.createEntityManager();
		
		//cria um produto
		Produto produto = new Produto();
		produto.setNome("Macarrão");
		produto.setPreco(10);
		produto.setDataVenda(Calendar.getInstance());
		
		Produto produto2 = new Produto();
		produto.setNome("Sabão");
		produto.setPreco(5);
		produto.setDataVenda(Calendar.getInstance());
		
		//insere registro
		manager.getTransaction().begin();
		manager.persist(produto);
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		manager.persist(produto2);
		manager.getTransaction().commit();
				
		//busca pelo id
		Produto encontrado = manager.find(Produto.class, 2L);
			
		//altera pelo id
		encontrado.setNome("Macarrão chinês premium");
		encontrado.setDataVenda(Calendar.getInstance());
		encontrado.setPreco(20);
	
		manager.getTransaction().begin();
		manager.merge(encontrado);
		manager.getTransaction().commit();
		
		//remove pelo id
		manager.getTransaction().begin();
		manager.remove(encontrado);
		manager.getTransaction().commit();
		
		//encerra
		manager.close();
		factory.close();

	}

}
