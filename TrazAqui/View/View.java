package View;

import Model.*;
import jdk.swing.interop.SwingInterOpUtils;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;
import java.time.Duration;
import java.util.*;
import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.io.Serializable;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Long.parseLong;


public class View implements Serializable {
    private View() {
    }

    private static TrazAqui dados = new TrazAqui();
    private static Menu principal, cliente, transportador, loja, escolhaC, showPreco;

    private static void carregaMenus() {
        String[] menuPrincipal = {"Registar",
                "Login",
                "Top 10 utilizadores que mais usam o sistema",
                "Top 10 Empresas Transportadoras com mais kms"};

        String[] menuCliente = {"Dados pessoais",
                "Requesitar entrega",
                "Listagem de encomendas",
                "Minhas encomendas",
        };

        String[] menuTransportador = {"Dados pessoais",
                "Minhas Encomendas",
                "Mostrar total faturado",
                "Pedidos"};

        String[] menuLoja = {"Dados da sua loja",
                "Listagem de encomendas",
                "Meus Produtos",
                "Adicionar Produto",
                "Pedidos"};


        String[] mshowPreco = {"Total",
                "Total faturado ",
                "Total faturado num periodo"};

        String[] mescolhaC = {"Sou cliente", "Sou transportador de encomendas", "Sou voluntario", "Sou lojista"};

        principal = new Menu(menuPrincipal);
        cliente = new Menu(menuCliente);
        transportador = new Menu(menuTransportador);
        loja = new Menu(menuLoja);
        escolhaC = new Menu(mescolhaC);
        showPreco = new Menu(mshowPreco);
    }

    public static void main(String[] args) throws IOException {

      /*  System.out.println("1-Ler ficheiro de logs?\n2-Prosseguir");
        int a = Input.lerInt();
        if (a == 1) {*/
        carregaDados();
        carregaMenus();
        lerDadosGravados();


        do {

            principal.executa();
            try {
                dados.gravar();
            } catch (IOException e) {
                System.out.println("Falha gravar estado");
            }

            switch (principal.getOp()) {
                case 1:
                    registar();
                    break;
                case 2:
                    iniciaSessao();
                    break;
                case 3:
                    showTop();
                    break;
                case 4:
                    showTopKm();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (principal.getOp() != 0);
    }

    public static void encomendar() {


        Encomenda encomenda = new Encomenda();



        String x;
        List<String> a = dados.getLojas().values().stream().map(Loja::navString).collect(Collectors.toList());
        Navegador n1 = new Navegador(a,1,5);
        n1.run();
        System.out.println("1. Indique a referencia da loja");
        x = Input.lerString();
        Loja loja = dados.getLojas().get(x);


        int produtos;
        System.out.println("1. Indique o numero de produtos que pretende comprar");
        produtos = Input.lerInt();

        int i = 0;
        List<Produto> prod = new ArrayList<>();
        while (i < produtos) {
            List<String> b = dados.getProdutos().values().stream().map(Produto::navString).collect(Collectors.toList());

            Navegador n2 = new Navegador(b,1,15);
            n2.run();

            Produto p = new Produto();
            int quantidade;
            System.out.println("Indique a referencia do produto");
            String nome = Input.lerString();
            p = dados.getProdutos().get(nome);
            System.out.println("Quantidade:");
            quantidade = Input.lerInt();
            p.setQuantidade(quantidade);
            prod.add(p);
            i++;
        }

        encomenda.setCusto(); //calcula o custo da encomenda

        System.out.println("Produtos adicionados com sucesso");

        dados.setEnc(encomenda); //encomenda a ser tratada

        int opcao;
        System.out.println("1:Escolher transportador mais proximo");
        System.out.println("2:Escolher Voluntario");
        System.out.println("3:Escolher Empresa mais barata");
        System.out.println("Opçao:");
        opcao = Input.lerInt();
        Transporte transportador = new Transporte();
        if (opcao == 1)
            transportador = dados.sortEncomendaTransporte(encomenda); //transportador mais proximo
        if (opcao == 2) transportador = dados.sortEncomendaVoluntario(encomenda); //voluntario mais proximo
        //if (opcao==3)

        float peso;
        System.out.println("Indique o peso aproximado da sua encomenda");
        peso = Input.lerFloat();

        encomenda.DefineEncomenda(prod, dados.getClienteIn(), loja, transportador, peso);

        int nota;

        Duration duracaoViagem = transportador.tempoViagem(encomenda);
        encomenda.setTempo(duracaoViagem);
        encomenda.setEfetuada(false);

        dados.setEnc(encomenda);
        dados.geraReferenciaEncomenda(encomenda);

        System.out.println("Classificação a atribuir ao Transportador:");
        nota = Input.lerInt();

        dados.daClassificacao(nota);

        dados.addRegistoC();

        dados.addRegistoT();

        dados.addRegistoL();

    }

    public static void pedidosEmpresa() {


        int op;
        if (dados.getEmpresaIn().getEncomendasPedidas().size() > 0) {
            System.out.println("Aceitar pedido?(1)->Aceitar (2)->Recusar");
            op = Input.lerInt();
            if (op == 1) dados.addEncomendaEmpresa();
        } else System.out.println("Não tem pedidos");

    }

    public static void pedidosVoluntario() {


        int op;
        if (dados.getVoluntarioIn().getEncomendasPedidas().size() > 0) {
            System.out.println("Aceitar pedido?(1)->Aceitar (2)->Recusar");

            op = Input.lerInt();
            if (op == 1) dados.addEncomendaVoluntario();
        } else System.out.println("Não tem pedidos");

    }



    public static void showencguer(int x) {
        LocalDate data, data2;
        String date, date2;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        System.out.println("Insira o intervalo de datas 1º:(d/mm/yyyy) ");
        date = Input.lerString();
        data = LocalDate.parse(date, formatter);
        System.out.println("2º:(d/m/yyyy) ");
        date2 = Input.lerString();
        data2 = LocalDate.parse(date2, formatter);
        dados.showEncomenda(data, data2, x);

    }

    public static void perfilCliente() {

        do {
            cliente.executa();


            switch (cliente.getOp()) {
                case 1:
                    showdadosC();
                    break;
                case 2:
                    encomendar();
                    break;
                case 3:

                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (cliente.getOp() != 0);

    }
    public static void perfilEmpresa() {


        do {
            transportador.executa();


            switch (transportador.getOp()) {
                case 1:
                    showdadosE();
                    break;
                case 2:
                    showencguer(2);
                    break;
                case 3:
                    showPreco();
                    break;
                case 4:
                    pedidosEmpresa();
                case 5:
                    pedidosVoluntario();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (transportador.getOp() != 0);

    }

    public static void perfilLoja() {


        do {
            loja.executa();


            switch (loja.getOp()) {
                case 1:
                    showdadosL();
                    break;
                case 2:
                    insereProdutos();
                    break;
                case 3:
                    produtosDisponiveis();
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (transportador.getOp() != 0);

    }

    public static void insereProdutos() {
        Loja l = dados.ShowDadosL();

        Produto p = new Produto();
        System.out.println("Nome do produto que deseja adicionar");
        String nome = Input.lerString();
        System.out.println("Preco do produto");
        double preco = Input.lerDouble();
        System.out.println("Peso do produto");
        float peso = Input.lerFloat();
        String aws = "";
        while (!aws.equals("S") || !aws.equals("N")){
            System.out.println("É medicinal? (S/N)");
        aws = Input.lerString();
    }
        p.setNome(nome);
        p.setPreco(preco);
        p.setPeso(peso);


        if (aws.equals("S")){
          p.setMedicinal(true);
        }
        if (aws.equals("N")){
            p.setMedicinal(false);
        }
        p = dados.geraReferenciaProduto(p);
        dados.adicionaProduto(p);
        System.out.println("Produto adicionado com sucesso com a referenica " + p.getReferencia());
    }

    public static void produtosDisponiveis(){

        System.out.println("Lista de produtos disponiveis:");
    }

    public static void showPreco() {


        do {

            showPreco.executa();

            switch (showPreco.getOp()) {
                case 1:
                    totalFaturadoEmpresa();
                    break;
                case 2:
                    totalFperiodo();
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (showPreco.getOp() != 0);


    }

    public static void totalFaturadoEmpresa() {
        /*
        String referencia;

        System.out.println("Insira a sua referencia ");
        referencia = Input.lerString();

         */
        System.out.println("Total faturado pela empresa :" + dados.getEmpresaIn().getTotalFaturado());

    }

    public static void totalFperiodo() {

        String email;
        LocalDate data;
        String date;

        System.out.println("Insira a referencia : ");
        email = Input.lerString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        System.out.println("Insira a data:(d/mm/yyyy) ");
        date = Input.lerString();
        data = LocalDate.parse(date, formatter);
        System.out.println("Total faturado nesse periodo:" + dados.totalFaturadoPeriodo(email, data));


    }


    public static void showdadosC() {

        User e = dados.ShowDadosU();
        System.out.println("Cliente:"+e.getNome());
        System.out.println("Referencia:" + e.getReferencia());
        System.out.println("Numero de encomendas em que esteve envolvido:"+ e.getEncomendas().size());
        System.out.println("________________________________________________");
    }

    public static void showdadosE() {
        EmpresaTransportadora e = dados.ShowDadosE();
        System.out.println("Empresa:"+e.getNome());
        System.out.println("Referencia:" + e.getReferencia());
        System.out.println("Numero de encomendas em que esteve envolvido:"+ e.getEncomendas().size());
        System.out.println("_______________________________________________");
    }

    public static void showdadosV() {

        Voluntario v = dados.ShowDadosV();
        System.out.println("Empresa:"+ v.getNome());
        System.out.println("Referencia:" + v.getReferencia());
        System.out.println("Numero de encomendas em que esteve envolvido:"+ v.getEncomendas().size());
        System.out.println("_______________________________________________");
    }

    public static void showdadosL() {

       Loja l = dados.ShowDadosL();
        System.out.println("Loja:" + l.getNome() );
        System.out.println("Referencia:" + l.getReferencia());
        System.out.println("Numero de encomendas em que esteve envolvido:"+ l.getEncomendas().size());
        System.out.println("_______________________________________________");
    }

    public static void registar() {
        String email, nome, password;
        int op, nif;
        double morada1, morada2;


        System.out.println("1. Sou Cliente");
        System.out.println("2. Sou Transportador afiliado a uma empresa");
        System.out.println("3. Sou Transportador voluntario");
        System.out.println("4. Sou Lojista");
        op = Input.lerInt();
        if(op>4 || op<1){ System.out.println("Opção invalida");

        }
        else {


            System.out.println("Insira o seu email: ");
            email = Input.lerString();

            System.out.println("Insira o seu nome: ");
            nome = Input.lerString();

            System.out.println("Insira a password: ");
            password = Input.lerString();

            System.out.println("Insira a coordenada x da sua morada ou localização atual:");
            morada1 = Input.lerDouble();
            System.out.println("Insira a coordenada y da sua morada ou localização atual:");
            morada2 = Input.lerDouble();

            Point2D.Double morada = new Point2D.Double(morada1, morada2);

            System.out.println("Insira o seu Nif");
            nif = Input.lerInt();

            if (op == 1) {
                User c = new User();

                c.setEmail(email);
                c.setNome(nome);
                c.setPassword(password);
                c.setMorada(morada);
                c.setNif(nif);
                c = dados.geraReferenciaUser(c);
                System.out.println(dados.getUsers());

                try {
                    dados.registarUtilizadorEmail(c);
                    System.out.println("A sua referencia de cliente é: " + c.getReferencia());
                    System.out.println("Registado com sucesso");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (op == 2) {
                double taxa=0;

                    System.out.println("Insira a taxa de transporte que pretende (0-15) (%)");
                    taxa = Input.lerDouble();
                    if (taxa>15) {
                        while(taxa>15){
                            System.out.println("Insira a taxa de transporte que pretende (0-15) (%)");
                            taxa = Input.lerDouble();
                        }
                    }

                taxa=taxa/100;
                EmpresaTransportadora p = new EmpresaTransportadora();

                p.setEmail(email);
                p.setNome(nome);
                p.setPassword(password);
                p.setMorada(morada);
                p.setNif(nif);
                p.setTaxa(taxa);
                p = dados.geraReferenciaTransportadorEmpresa(p);

                try {
                    dados.registarEmpresaEmail(p);
                    System.out.println("A sua referencia de transportador é: " + p.getReferencia());

                    System.out.println("Empresa transportadora Registada com sucesso");
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
            if (op == 3) {


                Voluntario p = new Voluntario();

                p.setEmail(email);
                p.setNome(nome);
                p.setPassword(password);
                p.setMorada(morada);
                p.setNif(nif);
                p = dados.geraReferenciaTransportadorVoluntario(p);


                try {
                    dados.registarVoluntarioEmail(p);
                    System.out.println("A sua referencia de voluntario é: " + p.getReferencia());
                    System.out.println("Voluntario Registado com sucesso");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (op == 4) {

                Loja l = new Loja();

                l.setEmail(email);
                l.setNome(nome);
                l.setPassword(password);
                l.setMorada(morada);
                l.setNif(nif);
                l = dados.geraReferenciaLoja(l);



                try {

                    dados.RegistaLojaEmail(l);
                    System.out.println(dados.getLojas());
                    System.out.println(dados.getLojasEmail());

                    System.out.println("A sua referencia de loja é: " + l.getReferencia());
                    System.out.println("Registado com sucesso");
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }


    }

    private static void iniciaSessaoAux(int op) {
        String email, pass;

        System.out.println("Email: ");
        email = Input.lerString();
        System.out.println("Password: ");
        pass = Input.lerString();
        if (op == 1) {
            try {
                dados.iniciaSessaoC(email, pass);
                System.out.println("Sessão iniciada com sucesso");
                perfilCliente();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (op == 2) {
            try {
                dados.iniciaSessaoE(email, pass);
                System.out.println("Sessão iniciada com sucesso");
                perfilEmpresa();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (op == 3) {
            try {
                dados.iniciaSessaoV(email, pass);
                System.out.println("Sessão iniciada com sucesso");
                perfilEmpresa();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (op == 4) {
            try {
                dados.iniciaSessaoL(email, pass);
                System.out.println("Sessão iniciada com sucesso");
                perfilLoja();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    private static void iniciaSessao() {


        do {
            escolhaC.executa();

            switch (escolhaC.getOp()) {
                case 1:
                    iniciaSessaoAux(1);
                    break;
                case 2:
                    iniciaSessaoAux(2);
                    break;
                case 3:
                    iniciaSessaoAux(3);
                    break;
                case 4:
                    iniciaSessaoAux(4);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (escolhaC.getOp() != 0);
    }

    public static void showTop() {

        List<User> top;
        top = dados.topUsers();
        int i = 1;
        Iterator<User> it = top.iterator();
        while (it.hasNext()) {
            User c = it.next();
            System.out.println("Pos" + i + c.getNome() + "" + c.getNif());
            i++;
        }


    }

    public static void showTopKm() {

        List<EmpresaTransportadora> top = new ArrayList<>();
        top = dados.top10Kms();
        int i = 1;
        Iterator<EmpresaTransportadora> it = top.iterator();
        while (it.hasNext()) {
            EmpresaTransportadora c = it.next();
            System.out.println("Pos:" + " " + i + " " + c.getNome() + " " + c.getNumeroKms());
            i++;
        }
    }


    public static void lerDadosGravados() {
        try {
            dados = TrazAqui.lerDados();
        } catch (IOException e) {
            dados = new TrazAqui();
            System.out.println("Não conseguiu ler os dados 1 !.");
        } catch (ClassNotFoundException e) {
            dados = new TrazAqui();
            System.out.println("Não conseguiu ler os dados 2 !");
        } catch (ClassCastException e) {
            dados = new TrazAqui();
            System.out.println("Não conseguiu ler os dados 3 !");
        }
    }



    public static void carregaDados() {
        try {

            BufferedReader br = new BufferedReader(new FileReader("TrazAqui/logs.txt"));

            while (br.ready()) {
                String linha = br.readLine();
                tratalinhas(linha);
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }




    public static void tratalinhas(String linha) {

        EmpresaTransportadora p = new EmpresaTransportadora();
        Voluntario v = new Voluntario();
        Encomenda enc = new Encomenda();
        User u = new User();
        Loja l = new Loja();
        StringTokenizer Tok = new StringTokenizer(linha, ":,");

        List<String> tokens = new ArrayList<>();
        while (Tok.hasMoreElements()) {
            tokens.add(Tok.nextToken());
        }


        if (tokens.get(0).equals("Utilizador")) {

            Point2D.Double coordenadascliente = new Point2D.Double();

            u.setReferencia(tokens.get(1));
            u.setNome(tokens.get(2));
            double x = parseDouble(tokens.get(3));
            double y = parseDouble(tokens.get(4));
            coordenadascliente.setLocation(x, y);
            u.setMorada(coordenadascliente);

            try {
                dados.registarUtilizador(u);


                System.out.println("Utilizador Registado com sucesso");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (tokens.get(0).equals("Loja")) {

            l.setReferencia(tokens.get(1));
            l.setNome(tokens.get(2));
            double x = Double.parseDouble(tokens.get(3));
            double y = Double.parseDouble(tokens.get(4));
            Point2D.Double coordenadas = new Point2D.Double();
            coordenadas.setLocation(x, y);
            l.setMorada(coordenadas);

            // System.out.println(l);

            try {
                dados.RegistaLoja(l);
                System.out.println("Loja Registada com sucesso");
                //System.out.println(dados.getLojas());
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (tokens.get(0).equals("Transportadora")) {

            p.setReferencia(tokens.get(1));
            p.setNome(tokens.get(2));
            double x = Double.parseDouble(tokens.get(3));
            double y = Double.parseDouble(tokens.get(4));
            p.setNif(Long.parseLong(tokens.get(5)));
            Point2D.Double coordenadastransporte = new Point2D.Double();
            coordenadastransporte.setLocation(x, y);
            p.setMorada(coordenadastransporte);
            p.setRaio(Float.parseFloat(tokens.get(6)));
            p.setTaxa(Double.parseDouble(tokens.get(7)));


            // System.out.println(p);

            try {
                dados.registarEmpresa(p);
                System.out.println("Transportadora Registado com sucesso");
                //System.out.println(dados.getEmpresaTransporte());
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (tokens.get(0).equals("Voluntario")) {
            v.setReferencia(tokens.get(1));
            v.setNome(tokens.get(2));
            v.setRaio(parseFloat(tokens.get(5)));
            double x = Double.parseDouble(tokens.get(3));
            double y = Double.parseDouble(tokens.get(4));
            Point2D.Double coordenadasvoluntario = new Point2D.Double();
            coordenadasvoluntario.setLocation(x, y);
            v.setMorada(coordenadasvoluntario);

            try {
                dados.registarVoluntario(v);

                System.out.println("Voluntario Registado com sucesso");
            } catch (Exception e) {
                System.out.println(e);
            }


        } else if (tokens.get(0).equals("Encomenda")) {

            enc.setReferencia(tokens.get(1));
            enc.setComprador(dados.getUsers().get(tokens.get(2)));
            enc.setLoja(dados.getLojas().get(tokens.get(3)));
            enc.setPeso(parseFloat(tokens.get(4)));


            List<Produto> aux = new ArrayList<>();
            int i = 5;
            while (i < tokens.size()) {
                Produto x = new Produto();
                x.setReferencia(tokens.get(i++));
                x.setNome(tokens.get(i++));
                x.setQuantidade(Float.parseFloat(tokens.get(i++)));
                x.setPreco(Double.parseDouble(tokens.get(i++)));
                aux.add(x);
            }
            //System.out.println(aux);

            enc.setProdutos(aux);

            dados.adicionaEncomenda(enc);


            //atualizar encomendas nos users
            User e = dados.getUsers().get(tokens.get(2));
            e.adicionaEncomendaUser(enc);
            dados.adicionaUser(e);


            //atualizar encomenda nas loja
            Loja j = dados.getLojas().get(tokens.get(3));
            j.adicionaEncomendaLoja(enc);
            dados.adicionaLoja(j);



            System.out.println("produtos adicionados");

        } else if (tokens.get(0).equals("Aceite")) {

            Encomenda e = dados.getEncomendas().get(tokens.get(1));
            e.setEfetuada(true);
            dados.adicionaEncomenda(e);
            System.out.println("Encomendas efetuadas lidas");

            try {
            } catch (NumberFormatException nfe) {

            }


        }

    }

}






