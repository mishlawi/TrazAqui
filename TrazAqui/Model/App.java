package Model;

import View.Menu;

import java.io.IOException;
import java.util.*;
import java.util.Scanner;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.Serializable;
/*
public class App implements Serializable
{
    private App() {}
    private static TrazAqui dados=new TrazAqui();
    private static Menu principal,cliente,proprietario,escolhaC,showPreco;

    private static void carregaMenus(){
        String [] menuP =     {"Registar",
                "Login",
                "Top 10 Clientes com mais alugueres",
                "Top 10 Clientes com mais kms"};

        String [] menuCliente ={"Dados pessoais",
                "Listagem de alugueres",
                "Alugar"};

        String[] menuProp = {"Dados pessoais",
                "Listagem de alugueres",
                "Inserir viaturas",
                "Meus veiculos",
                "Mostrar total faturado num veiculo",
                "Pedidos aluguer"};



        String [] mshowPreco = {"Total",
                "Total faturado ",
                "Total faturado num periodo"};
        String [] mescolhaC ={"Sou cliente","Sou proprietario"};

        principal = new Menu(menuP);
        cliente = new Menu(menuCliente);
        proprietario = new Menu(menuProp);
        escolhaC=new Menu(mescolhaC);
        showPreco = new Menu(mshowPreco);
    }
    public static void main(String[] args) {


        //carregaDados();
        carregaMenus();
        lerDadosGravados();


        do{
            principal.executa();
            try{
                dados.gravar();
            }
            catch(IOException e){
                System.out.println("Falha gravar estado");
            }

            switch(principal.getOp()){
                case 1:
                    inclui();
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
        } while(principal.getOp()!= 0);
    }

    public static void alugar(){
        Point2D.Double inicio=new Point2D.Double();
        Point2D.Double destino=new Point2D.Double();
        Aluguer al=new Aluguer();
        double x,y,a,b;
        Scanner input = new Scanner(System.in);
        String tipo,combustivel;
        System.out.println("1. Indique onde está coordenada X");
        x=input.nextDouble();
        System.out.println("2. Indique onde está coordenada Y");
        y=input.nextDouble();
        inicio.setLocation(x,y);
        System.out.println("1. Indique coordenada X do seu destino");
        a=input.nextDouble();
        System.out.println("2. Indique coordenada Y do seu destino");
        b=input.nextDouble();
        destino.setLocation(a,b);
        System.out.println("Indique o tipo de combustivel:");
        combustivel=input.nextLine();
        System.out.println("Escolha tipo de viatura:");
        tipo=input.nextLine();
        int opcao;

        dados.setAlu(al);

        dados.defCliente();

        dados.definirCoor(inicio,destino);

        String matricula;
        System.out.println("1:Escolher veiculos mais proximo");
        System.out.println("2:Escolher veiculos mais baarato");
        System.out.println("Opçao:");
        opcao=input.nextInt();
        if (opcao==1)
            matricula=dados.veiMaisProx(inicio,destino,tipo);
        else matricula=dados.veiMaisBarato(destino, tipo);
        if (dados.TemAutonomia()==true) System.out.println("O veiculo tem autonomia suficiente");


        dados.tempoChegada(inicio);

        int nota;

        dados.duracaoViagem(destino);

        dados.atualizaData();





        dados.updateLocalViatCli(destino);

        System.out.println("Classificação a atribuir ao veiculo:");
        nota=input.nextInt();

        dados.addClassProp(matricula,nota);



        //if (dados.getAlu().getAceite()==false){


        //dados.addPedido(dados.getAlu());
        //}
        //else {

        dados.addRegistoC();

        dados.addRegistoV();

        dados.addRegistoP();


        dados.addTotalFaturado();

        dados.addKmspercoridos();

        // }
        input.close();
    }

    public static void pedidos(){

        Scanner input = new Scanner(System.in);
        int op;
        if (dados.getplogado().getPedidos().size()>0){
            System.out.println("Aceitar pedido?(1)->Aceitar (2)->Recusar");

            op=input.nextInt();
            if (op==1) dados.addAluguer();
        }else System.out.println("Não tem pedidos");

    }
    public static void perfilCliente(){

        do{
            cliente.executa();


            switch(cliente.getOp()){
                case 1:
                    showdadosC();
                    break;
                case 2:
                    showaluguer(1);
                    break;
                case 3:
                    alugar();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while(cliente.getOp()!=0);

    }
    public static void showaluguer(int x){
        LocalDate data,data2;
        String date,date2;
        Scanner input = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        System.out.println("Insira o intervalo de datas 1º:(d/mm/yyyy) ");
        date = input.nextLine();
        data= LocalDate.parse(date, formatter);
        System.out.println("2º:(d/m/yyyy) ");
        date2 = input.nextLine();
        data2=LocalDate.parse(date2, formatter);
        dados.showAluguer(data,data2, x);
        input.close();
    }
    public static void perfilProp(){


        do{
            proprietario.executa();


            switch(proprietario.getOp()){
                case 1:
                    showdadosP();
                    break;
                case 2:
                    showaluguer(2);
                    break;
                case 3:
                    inserirViatura();
                    break;
                case 4:
                    ShowVeiculos();
                    break;
                case 5:
                    showPreco();
                    break;
                case 6:
                    pedidos();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while(proprietario.getOp() != 0);

    }
    public static void showPreco()
    {





        do{

            showPreco.executa();

            switch(showPreco.getOp()){
                case 1:
                    totalPeriodo();
                    break;
                case 2:
                    totalFperiodo();
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while(showPreco.getOp() != 0);


    }
    public static void totalPeriodo(){
        String matricula;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Insira a matricula: ");
        matricula = entrada.nextLine();
        System.out.println("Total faturado com o veiculo:"+dados.showTotalFaturado(matricula));
        entrada.close();
    }
    public static void totalFperiodo(){

        String matricula;
        LocalDate data;
        String date;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Insira a matricula: ");
        matricula = entrada.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        System.out.println("Insira a data:(d/mm/yyyy) ");
        date = entrada.nextLine();
        data= LocalDate.parse(date, formatter);
        System.out.println("Total faturado com o veiculo:"+dados.totalFaturadoPeriodo(matricula,data));
        entrada.close();

    }


    public static void showdadosC()
    {

        System.out.println(dados.ShowDadosC().toString());

    }
    public static void showdadosP()
    {

        System.out.println(dados.ShowDadosP().toString());

    }
    public static void inclui()
    {
        String email,nome,password,morada,data;
        int op,nif;

        Scanner input = new Scanner(System.in);
        System.out.println("1. Sou Cliente");
        System.out.println("2. Sou Proprietário");
        op=input.nextInt();

        input.nextLine();


        System.out.println("Insira o seu email: ");
        email = input.nextLine();

        System.out.println("Insira o seu nome: ");
        nome = input.nextLine();

        System.out.println("Insira a password: ");
        password = input.nextLine();

        System.out.println("Insira a morada: ");
        morada = input.nextLine();

        System.out.println("Insira a sua data de nascimento (dd-mm-yyyy)");
        data = input.nextLine();
        System.out.println("Insira o seu Nif");
        nif = input.nextInt();

        if (op==1)
        {
            Cliente c = new Cliente();

            c.setEmail(email);
            c.setNome(nome);
            c.setPassword(password);
            c.setMorada(morada);
            c.setDataN(data);
            c.setNif(nif);
            try{
                dados.registarCliente(c);
                input.close();
                System.out.println("Registado com sucesso");
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        if (op==2)
        {
            Proprietario p = new Proprietario();

            p.setEmail(email);
            p.setNome(nome);
            p.setPassword(password);
            p.setMorada(morada);
            p.setDataN(data);
            p.setNif(nif);
            try{
                dados.registarProprietario(p);
                input.close();
                System.out.println("Registado com sucesso");
            }
            catch(Exception e){
                System.out.println(e);
            }

        }


    }
    public static void inserirViatura()
    {
        String matricula,marca,tipo;
        int nif;
        double velmedia,precobase,consumo,autonomia;

        double x,y;
        Scanner input = new Scanner(System.in);



        System.out.println("Insira a matricula da sua viatura: ");
        matricula = input.nextLine();

        System.out.println("Insira a marca da sua viatura: ");
        marca = input.nextLine();

        System.out.println("Insira o tipo: ");
        tipo = input.nextLine();

        System.out.println("Insira o seu nif: ");
        nif = input.nextInt();

        System.out.println("Insira a velocidade media da viatura");
        velmedia = input.nextDouble();
        System.out.println("Insira o preço base da viatura");
        precobase = input.nextDouble();
        System.out.println("Insira a autonomia da viatura");
        autonomia = input.nextDouble();
        System.out.println("Insira o consumo da viatura");
        consumo = input.nextDouble();
        System.out.println("Insira a coordenada X onde se encontra a sua viatura");
        x = input.nextDouble();
        System.out.println("Insira a coordenada Y onde se encontra a sua viatura");
        y = input.nextDouble();
        if (tipo.equals("Gasolina")) {
            CarroGasolina c = new CarroGasolina();
            c.setMatricula(matricula);
            c.setVel_Media(velmedia);
            c.setConsumo(consumo);
            c.setPrecoBase(precobase);
            c.setNif(nif);
            c.setAutonomia(autonomia);
            c.setAutonomiaAtual(autonomia);
            c.setTipo(tipo);
            c.setMarca(marca);
            c.setLocalizacao(x,y);
            dados.Registaveiculo(c);
        }
        else if (tipo.equals("Electrico")) {
            CarroGasolina c = new CarroGasolina();
            c.setMatricula(matricula);
            c.setVel_Media(velmedia);
            c.setConsumo(consumo);
            c.setPrecoBase(precobase);
            c.setNif(nif);
            c.setAutonomia(autonomia);
            c.setAutonomiaAtual(autonomia);
            c.setTipo(tipo);
            c.setMarca(marca);
            c.setLocalizacao(x,y);
            dados.Registaveiculo(c);
        }
        else if(tipo.equals("Hibrido")){


        }
        else{
            System.out.println("Esse tipo de veículo não está disponível!");
        }
        input.close();
    }

    private static void iniciaSessaoAux(int op)
    {
        String mail, pass;
        Scanner in = new Scanner(System.in);
        System.out.println("Email: ");
        mail=in.nextLine();
        System.out.println("Password: ");
        pass =in.nextLine();
        if (op==1) {
            try{
                dados.iniciaSessaoC(mail,pass);
                System.out.println("Sessão iniciada com sucesso");
                perfilCliente();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        else{
            try{
                dados.iniciaSessaoP(mail,pass);
                System.out.println("Sessão iniciada com sucesso");
                perfilProp();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        in.close();
    }
    private static void iniciaSessao()
    {


        do{
            escolhaC.executa();

            switch(escolhaC.getOp()){
                case 1:
                    iniciaSessaoAux(1);
                    break;
                case 2:
                    iniciaSessaoAux(2);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while(escolhaC.getOp()!=0);
    }
    public static void ShowVeiculos()
    {
        List<Veiculo> veiculos=dados.VeiculosProp();
        Iterator<Veiculo> it = veiculos.iterator();
        while(it.hasNext()){
            Veiculo l = it.next();
            System.out.println(l.toString());
        }
    }
    public static void lerDadosGravados()
    {
        try{
            dados = TrazAqui.lerDados();
        }
        catch(IOException e){
            dados = new TrazAqui();
            System.out.println("Não conseguiu ler os dados!.");
        }
        catch(ClassNotFoundException e){
            dados = new TrazAqui();
            System.out.println("Não conseguiu ler os dados!");
        }
        catch(ClassCastException e){
            dados = new TrazAqui();
            System.out.println("Não conseguiu ler os dados!");
        }
    }

    public static void carregaDados()
    {
        try{
            BufferedReader br = new BufferedReader(new FileReader("logs.txt"));
            while(br.ready()){
                String linha = br.readLine();
                tratalinhas(linha);
            }
            br.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }
    public static void showTop(){

        List<Cliente>top=new ArrayList<>();
        top=dados.top10Aluguers();
        int i=1;
        Iterator<Cliente> it=top.iterator();
        while(it.hasNext()){
            Cliente c=it.next();
            System.out.println("Pos"+i+c.getNome()+""+c.getNif());
            i++;
        }


    }
    public static void showTopKm(){

        List<Cliente>top=new ArrayList<>();
        top=dados.top10Kms();
        int i=1;
        Iterator<Cliente> it=top.iterator();
        while(it.hasNext()){
            Cliente c=it.next();
            System.out.println("Pos:"+" "+i+" "+c.getNome()+" "+c.getKmPercorridos());
            i++;
        }
    }
    public static void tratalinhas(String linha)
    {
        Proprietario p = new Proprietario();
        Aluguer alu=new Aluguer();
        Cliente c=new Cliente();
        StringTokenizer Tok = new StringTokenizer(linha,":,");

        List<String> tokens = new ArrayList<>();
        while (Tok.hasMoreElements()) {
            tokens.add(Tok.nextToken());
        }

        CarroGasolina cg=new CarroGasolina();
        CarroEletrico ce=new CarroEletrico();
        if (tokens.get(0).equals("NovoCliente")){

            Point2D.Double pcliente=new Point2D.Double();
            c.setEmail(tokens.get(3));
            c.setNome(tokens.get(1));
            c.setPassword(tokens.get(3));
            c.setMorada(tokens.get(4));
            c.setDataN("12-11-1999");
            c.setNif(Integer.parseInt(tokens.get(2)));
            double x=Double.parseDouble(tokens.get(5));
            double y=Double.parseDouble(tokens.get(6));
            pcliente.setLocation(x,y);
            c.setLocalizacao(pcliente);
            try{
                dados.registarCliente(c);

                System.out.println("Registado com sucesso");
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        else if(tokens.get(0).equals("NovoProp")){
            p.setEmail(tokens.get(3));
            p.setNome(tokens.get(1));
            p.setPassword(tokens.get(3));
            p.setMorada(tokens.get(2));
            p.setDataN("13-11-1985");
            p.setNif(Integer.parseInt(tokens.get(2)));
            try{
                dados.registarProprietario(p);

                System.out.println("Registado com sucesso");
            }
            catch(Exception e){
                System.out.println(e);
            }

        }else if (tokens.get(0).equals("NovoCarro")){


            if (tokens.get(1).equals("Gasolina")) {

                cg.setMatricula(tokens.get(3));
                cg.setVel_Media(Double.parseDouble(tokens.get(5)));
                cg.setConsumo(Double.parseDouble(tokens.get(7)));
                cg.setPrecoBase(Double.parseDouble(tokens.get(6)));
                cg.setNif(Integer.parseInt(tokens.get(4)));
                cg.setAutonomia(Double.parseDouble(tokens.get(8)));
                cg.setAutonomiaAtual(Double.parseDouble(tokens.get(8)));
                cg.setTipo(tokens.get(1));
                cg.setMarca(tokens.get(2));
                cg.setLocalizacao(Double.parseDouble(tokens.get(9)),Double.parseDouble(tokens.get(10)));
                dados.Registaveiculo(cg);
            }
            else if (tokens.get(1).equals("Electrico")) {

                ce.setMatricula(tokens.get(3));
                ce.setVel_Media(Double.parseDouble(tokens.get(5)));
                ce.setConsumo(Double.parseDouble(tokens.get(7)));
                ce.setPrecoBase(Double.parseDouble(tokens.get(6)));
                ce.setNif(Integer.parseInt(tokens.get(4)));
                ce.setAutonomia(Double.parseDouble(tokens.get(8)));
                ce.setAutonomiaAtual(Double.parseDouble(tokens.get(8)));
                ce.setTipo(tokens.get(1));
                ce.setMarca(tokens.get(2));
                ce.setLocalizacao(Double.parseDouble(tokens.get(9)),Double.parseDouble(tokens.get(10)));
                dados.Registaveiculo(ce);
            }
            else if(tokens.get(1).equals("Hibrido")){


            }
            else{
                System.out.println("Esse tipo de veículo não está disponível!");
            }
        }else if (tokens.get(0).equals("Aluguer")){
            int result = Integer.parseInt(tokens.get(1));
            alu.setC(dados.tiraCliente(result));
            alu.setAceite(true);
            Point2D.Double destino=new Point2D.Double();
            double x=Double.parseDouble(tokens.get(2));
            double y=Double.parseDouble(tokens.get(3));
            dados.setAlu(alu);
            String tipo=tokens.get(4);
            String combustivel=tokens.get(5);
            dados.defCliente(dados.tiraCliente(result));
            destino.setLocation(x,y);
            dados.definirCoor(dados.tiraCliente(result).getLocalizacao(),destino);
            System.out.println("Iremos selecionar o veiculo que se encontra mais próximo de si");
            if (tokens.get(5).equals("MaisBarato")) dados.veiMaisBarato(destino,tipo);
            else dados.veiMaisProx(dados.tiraCliente(result).getLocalizacao(),destino,tipo);
            if (dados.TemAutonomia()==true) System.out.println("O veiculo tem autonomia suficiente");

            dados.tempoChegada(dados.tiraCliente(result).getLocalizacao());

            dados.duracaoViagem(destino);

            dados.atualizaData();

            dados.addRegistoC();

            dados.addRegistoV();

            dados.addRegistoP();

            dados.addTotalFaturado();

            dados.addKmspercoridos();

            dados.updateLocalViatCli(destino);


        }else if  (tokens.get(0).equals("Classificar")){

            int nota=Integer.parseInt(tokens.get(2));

            try {
                int nif= Integer.parseInt(tokens.get(1));
                dados.addClassCli(nif,nota);

            } catch (NumberFormatException nfe) {
                dados.addClassProp(tokens.get(1),nota);
            }





        }
    }


}

 */