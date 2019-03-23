package br.com.vvcurso.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Pedido extends Base {

	private String nomeCliente;
	private Integer codigoFilial;
	BigDecimal valorTotalPedido = BigDecimal.ZERO;
	private Set<Item> itens = new HashSet<>();
	
	public Pedido() {
	}

	public Pedido(final String nomeCliente, final Integer codigoFilial) {
		super();
		this.nomeCliente = nomeCliente;
		this.codigoFilial = codigoFilial;
		//this.itens = itens;
	}

	public Pedido(Date dataHoraInclusao, Date dataHoraAlteracao, Integer codigoPedido, String nomeCliente,
			Integer codigoFilial, Set<Item> itens) {
		super(dataHoraInclusao, dataHoraAlteracao, codigoPedido);
		this.nomeCliente = nomeCliente;
		this.codigoFilial = codigoFilial;
		this.itens = itens;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(final String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getCodigoFilial() {
		return codigoFilial;
	}

	public void setCodigoFilial(final Integer codigoFilial) {
		this.codigoFilial = codigoFilial;
	}

	public Set<Item> getItens() {
		return itens;
	}

    public void addItemPedido(Item itemPedido) {
        itens.add(itemPedido);
    }
    
    public void removeItemPedido(Item itemPedido) {
        itens.remove(itemPedido);
    }

	public BigDecimal valorPedido(final BigDecimal valorTotalItem) {
		valorTotalPedido = valorTotalPedido.add(valorTotalItem);
		if(valorTotalPedido == BigDecimal.ZERO) {
			return valorTotalPedido;
		} 
		return valorTotalPedido;
	}
	
	public BigDecimal getValorTotal() {
		BigDecimal soma = BigDecimal.ZERO;
		for (Item ip : itens) {
			soma = soma.add(ip.getValorTotalItem());
		}
		return soma;
	}

	@Override
	public String toString() {
		//return "\n Pedido: " + getDataHoraInclusao() + ", " + getDataHoraAlteracao() + ", " + getCodigoPedido() + ", " + nomeCliente + ", " + codigoFilial + ", " + itens;
		return getDataHoraInclusao() + ", " + getDataHoraAlteracao() + ", " + getCodigoPedido() + ", " + nomeCliente + ", " + codigoFilial + ", " + itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((codigoFilial == null) ? 0 : codigoFilial.hashCode());
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		result = prime * result + ((nomeCliente == null) ? 0 : nomeCliente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (codigoFilial == null) {
			if (other.codigoFilial != null)
				return false;
		} else if (!codigoFilial.equals(other.codigoFilial))
			return false;
		if (itens == null) {
			if (other.itens != null)
				return false;
		} else if (!itens.equals(other.itens))
			return false;
		if (nomeCliente == null) {
			if (other.nomeCliente != null)
				return false;
		} else if (!nomeCliente.equals(other.nomeCliente))
			return false;
		return true;
	}
}
