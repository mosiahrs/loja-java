package com.mycompany.testeidbrasil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import modelo.Produto;

/**
 *
 * @author Mosiah Silva <mosiahrabello at gmail.com>
 */
public class TesteIdBrasil {

    private static final Scanner entrada = new Scanner(System.in);
    private static ArrayList<Produto> produtos;
    private static Map<Produto, Integer> carrinho;

    public static void main(String[] args) {

        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }

    private static void menu() {
        System.out.println("-----------------Bem indo a loja-----------------");
        System.out.println("-------------------------------------------------");
        System.out.println(" **** Escolha uma opcao **** ");
        System.out.println("|      Opcao 1 - Cadastrar        ");
        System.out.println("|      Opcao 2 - Mostrar Itens  ");
        System.out.println("|      Opcao 3 - Comprar          ");
        System.out.println("|      Opcao 4 - Carrinho          ");
        System.out.println("|      Opcao 5 - Encerrar          ");
        System.out.println("-------------------------------------------------");

        int opcao = entrada.nextInt();

        switch (opcao) {
            case 1 ->
                cadastrarProduto();
            case 2 ->
                mostrarItens();
            case 3 ->
                comprarProduto();
            case 4 ->
                mostrarCarrinho();
            case 5 -> {
                System.out.println("Volte sempre");
                System.exit(0);
            }
            default -> {
                System.out.println("Opcao invalida, por favor escolha novamente");
                menu();
            }
        }
    }

    private static void cadastrarProduto() {
        System.out.println("Digite o nome do produto: ");
        String nome = entrada.next();

        System.out.println("Digite o valor do produto: ");
        double preco = entrada.nextDouble();

        Produto produto = new Produto(nome, preco);
        produtos.add(produto);

        System.out.println(produto.getNome() + " Cadastrado com sucesso!!");
        menu();

    }

    private static void mostrarItens() {
        if (!produtos.isEmpty()) {
            System.out.println("Itens em Estoque");

            for (Produto prod : produtos) {
                System.out.println(prod);
            }
        } else {
            System.out.println("Nenhum produto encontrado!");
        }

        menu();
    }

    private static void comprarProduto() {
        if (!produtos.isEmpty()) { //if (produtos.size() > 0) {
            System.out.println("Produtos Disponiveis");
            for (Produto prod : produtos) {
                System.out.println(prod + "\n");
            }
            System.out.println("Digite o codigo do produto que deseja comprar: ");
            int id = Integer.parseInt(entrada.next());

            boolean validaItem = false;

            for (Produto prod : produtos) {
                if (prod.getId() == id) {
                    int qtd = 0;
                    try {
                        qtd = carrinho.get(prod);
                        carrinho.put(prod, qtd + 1);
                    } catch (NullPointerException e) {
                        carrinho.put(prod, 1);
                    }

                    System.out.println(" Item adicionado ao carrinho! ");
                    validaItem = true;

                    if (validaItem) {
                        System.out.println(" Gostaria de adicionar mais itens?  ");
                        System.out.println(" Digite 1 para sim \n 2 para ver o carrinho \n 3 para finalizar a compra");

                        int opcao = Integer.parseInt(entrada.next());

                        if (opcao == 1) {
                            comprarProduto();
                        } else {
                            finalizarCompra();
                        }
                    }
                } else {
                    System.out.println("Produto nao encontrado");
                    menu();
                }
            }
        } else {
            System.out.println("Nao existem produtos cadastrados");
            menu();
        }
    }

    private static void mostrarCarrinho() {
        if (!carrinho.isEmpty()) {
            for (Produto prod : carrinho.keySet()) {
                System.out.println("Produto: " + prod + "\nQuantidade: " + carrinho.get(prod));
            }
        } else {
            System.out.println("Carrinho vazio");
        }
        menu();
    }

    private static void finalizarCompra() {
        double valorTotal = 0d;
        System.out.println("Seus produtos: ");

        for (Produto prod : carrinho.keySet()) {
            int qtd = carrinho.get(prod);
            valorTotal = prod.getPreco() * qtd;

            System.out.println(prod);
            System.out.println("Quantidade:  " + qtd);
        }
        System.out.println("O valor total da compra : " + utilidades.Utilidades.doubleParaString(valorTotal));
        carrinho.clear();
        System.out.println("Obrigado pela compra!");
        menu();
    }
}
