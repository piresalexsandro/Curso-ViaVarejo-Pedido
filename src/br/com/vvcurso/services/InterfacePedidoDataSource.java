package br.com.vvcurso.services;

import java.util.List;
import java.util.Set;

import br.com.vvcurso.model.Pedido;

public interface InterfacePedidoDataSource {

	public List<Pedido> lerArquivo();
	//public void gravarArquivo(Pedido pedido);
	public void gravarMemoria(Pedido pedido);
	void gravarArquivo(Set<Pedido> listPedido);
	
}
