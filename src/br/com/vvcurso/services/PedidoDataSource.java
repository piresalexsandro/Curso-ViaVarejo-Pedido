package br.com.vvcurso.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.google.gson.Gson;

import br.com.vvcurso.model.Console;
import br.com.vvcurso.model.Pedido;

public class PedidoDataSource implements Acao, InterfacePedidoDataSource {

	Scanner sc = new Scanner(System.in);
	Pedido p = new Pedido();
	Console console = new Console();
	
	Set<Pedido> listPedido = new HashSet<Pedido>();
	Map<Integer, Object> mapPedidos = new HashMap<>();
	
	String desejaSair;
	int cdPedido;

	
	public boolean acao(final String opcao) {
		switch (opcao.toUpperCase()) {
		case "L":
			lerArquivo();
			break;
		case "X":
			// LISTALL
			break;
		case "C":
			System.out.print("Entre com o codigo do pedido a ser consultado: ");
			cdPedido = sc.nextInt();
			consultar(cdPedido);
			break;
		case "I":
			String retorno = incluir(p = console.preencherDados());
			System.out.println(retorno);
			break;
		case "E":
			System.out.print("Entre com o codigo do pedido a ser excluido: ");
			cdPedido = sc.nextInt();
			excluir(cdPedido);
			break;
		case "A":
			System.out.print("Entre com o codigo do pedido a ser alterado: ");
			cdPedido = sc.nextInt();
			alterar(cdPedido);
			break;
		case "S":
			sair();
			if (desejaSair.toUpperCase().equals("S")) {
				if (listPedido != null) {
					gravarArquivo(listPedido);
				}
				return true;
			}
			break;
		default:
			System.out.println("Opcao invalida");
			break;
		}

		return false;
	}

	@Override
	public String incluir(Pedido pedido) {
		mapPedidos.put(pedido.getCodigoPedido(), pedido);
		gravarListaPedido(pedido);
		return "Pedido incluido com sucesso";
	}

	@Override
	public Pedido consultar(final int cdPedidoConsultar) {
		System.out.println();
		Pedido retornoConsulta = new Pedido();
		retornoConsulta.setCodigoPedido(cdPedidoConsultar); 
		
		if (mapPedidos.containsKey(cdPedidoConsultar)) {
			retornoConsulta = (Pedido) mapPedidos.getOrDefault(retornoConsulta.getCodigoPedido(), null);
			retornoConsulta.setDataHoraAlteracao(null);
			console.retornarPedido(retornoConsulta);
		} else {
			System.out.println("Pedido não encontrado!");
			/*String x = " ";
			x = sc.next();
			if (x == "S") {
			   this.incluir(retornoConsulta);
			}*/
		}
		return retornoConsulta; 
	}
	
	@Override
	public void excluir(int cdPedidoExcluir) {
		
		
		Pedido pedidoExcluir = new Pedido();
		pedidoExcluir.setCodigoPedido(cdPedidoExcluir); 
		
		if (mapPedidos.containsKey(cdPedidoExcluir)) {
			mapPedidos.remove(pedidoExcluir.getCodigoPedido());
			System.out.println("Pedido: "+ cdPedido + ", excluido com sucesso");
		}else {
			System.out.println("Pedido não encontrado!");
		}
	}

	@Override
	public void alterar(int cdPedidoAlterar) {

		Pedido retornoPedidoAlterar = new Pedido();

		if (mapPedidos.containsKey(cdPedidoAlterar)) {
			retornoPedidoAlterar = consultar(cdPedidoAlterar);
			console.retornarPedido(retornoPedidoAlterar);
			mapPedidos.put(retornoPedidoAlterar.getCodigoPedido(), new Object());
			//gravarListaPedido(retornoPedidoAlterar);

		} else {
			System.out.println("Pedido não encontrado! Deseja incluir?");
			this.incluir(retornoPedidoAlterar);
		}
		
	}

	@Override
	public void sair() {
		System.out.println();
		System.out.print("Realmente deseja sair da aplicação? ");
		String x = sc.next();
		desejaSair = x;
	}
	
	
	
	@Override
	public List<Pedido> lerArquivo() {
		
		List<Pedido> listPedidoFromJson = new ArrayList<>();
		
        try {
    	    Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader("C:\\eclipse-workspace\\temp\\filePedido.json"));

            //Converte String JSON para objeto Java
            //listPedidoFromJson =  gson.fromJson(br, List.class);
            listPedidoFromJson = new Gson().fromJson(br, List.class);
            
            
            // OBSERVAR O TOSTRING DAS CLASSES
            
            System.out.println("==========================================================");
            System.out.println("Conteudo lido a partir do arquivo" + listPedidoFromJson);
            System.out.println("==========================================================");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPedidoFromJson;
    }

	private Class arg1(BufferedReader br, Class<Pedido> class1) {
		// TODO Auto-generated method stub
		return class1;
	}

	public void gravarListaPedido(Pedido pedido) {
		listPedido.add(p);
    }

	@Override
	public void gravarMemoria(Pedido pedido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gravarArquivo(Set<Pedido> listPedido) {
		    Gson gson = new Gson();
			  
		    // converte objetos Java para JSON e retorna JSON como String...
		    String json = gson.toJson(listPedido);
		  
		    try {
		        //Escreve Json convertido em arquivo chamado "file.json"
		        FileWriter writer = new FileWriter("C:\\eclipse-workspace\\temp\\filePedido.json");
		        writer.write(json);
		        writer.close();
		  
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		  
	        System.out.println("==========================================================");
		    System.out.println("JSON gerado: " + json);
	        System.out.println("==========================================================");
		}
	}

