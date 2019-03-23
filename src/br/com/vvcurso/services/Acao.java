package br.com.vvcurso.services;

import br.com.vvcurso.model.Pedido;

public interface Acao {

	public String incluir(Pedido pedido);
	public Pedido consultar(int cdPedidoConsultar);
	public void alterar(int cdPedidoAlterar);
	public void excluir(int cdPedidoExcluir);
	public void sair();
}
