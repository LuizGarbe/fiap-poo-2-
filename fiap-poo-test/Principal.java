/** Guarda os atributos que Caminhão e Moto têm em comum, evitando Herança */

class Veiculo {

    private String placa;
    private double capacidade;

    /** Cria um veículo
    @param placa      
    @param capacidade *Carga em kg*/
     
    public Veiculo(String placa, double capacidade) {
        setPlaca(placa);
        setCapacidade(capacidade);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("A placa não pode ser vazia.");
        }
        this.placa = placa;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(double capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que zero.");
        }
        this.capacidade = capacidade;
    }
}

/**Veículo do tipo caminhão. Herda placa e capacidade, acrescenta apenas a quantidade de eixos*/
class Caminhao extends Veiculo {

    private int eixos;

    /**
    @param placa      
    @param capacidade carga em kg
    @param eixos    
     */

    public Caminhao(String placa, double capacidade, int eixos) {
        super(placa, capacidade);
        setEixos(eixos);
    }

    public int getEixos() {
        return eixos;
    }

    public void setEixos(int eixos) {
        if (eixos <= 0) {
            throw new IllegalArgumentException("A quantidade de eixos deve ser maior que zero.");
        }
        this.eixos = eixos;
    }
}

/**Veículo do tipo moto. Herda placa e capacidade, acrescenta apenas o que é próprio da moto se possui baú*/
class Moto extends Veiculo {

    private boolean possuiBau;

    /**
    @param placa      
    @param capacidade capacidade de carga em kg
    @param possuiBau  
     */

    public Moto(String placa, double capacidade, boolean possuiBau) {
        super(placa, capacidade);
        this.possuiBau = possuiBau;
    }

    public boolean isPossuiBau() {
        return possuiBau;
    }

    public void setPossuiBau(boolean possuiBau) {
        this.possuiBau = possuiBau;
    }
}

/**Pacote entregue pelo FiapDelivery*/
class Pacote {

    private String codigo;
    private double peso;
    private String status;

    /**
    @param codigo rastreio do pacote
    @param peso   pacote em kg 
     */

    public Pacote(String codigo, double peso) {
        setCodigo(codigo);
        setPeso(peso);
        this.status = "Pendente";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("O código do pacote não pode ser vazio.");
        }
        this.codigo = codigo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("O peso deve ser maior que zero.");
        }
        this.peso = peso;
    }

    public String getStatus() {
        return status;
    }

    /**Atualiza o status do pacote
    @param novoStatus 
     */
    
    public void atualizarStatus(String novoStatus) {
        if (novoStatus == null || novoStatus.isBlank()) {
            throw new IllegalArgumentException("O status não pode ser vazio.");
        }
        this.status = novoStatus;
    }
}

/**Representa uma rota de entrega*/
class Rota {

    private Pacote pacote;
    private Veiculo veiculo;

    /**Cria uma rota
    @param pacote  
    @param veiculo 
     */

    public Rota(Pacote pacote, Veiculo veiculo) {
        setPacote(pacote);
        setVeiculo(veiculo);
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        if (pacote == null) {
            throw new IllegalArgumentException("A rota precisa de um pacote.");
        }
        this.pacote = pacote;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        if (veiculo == null) {
            throw new IllegalArgumentException("A rota precisa de um veículo.");
        }
        this.veiculo = veiculo;
    }

    /**Inicia a entrega: confere se o veículo suporta o peso do pacote*/
    public void iniciarEntrega() {
        if (pacote.getPeso() > veiculo.getCapacidade()) {
            System.out.println("Não foi possível levar o pacote " + pacote.getCodigo()
                    + ": o veículo " + veiculo.getPlaca() + " não suporta esse peso.");
            return;
        }
        pacote.atualizarStatus("Em transporte");
        System.out.println("Levando pacote " + pacote.getCodigo()
                + " no veículo " + veiculo.getPlaca());
    }
}

public class Principal {

    public static void main(String[] args) {

        // O dado inválido do código legado
        try {
            new Caminhao("ABC1234", -500.0, 3);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar caminhão: " + e.getMessage());
        }

        // Entrega de caminhão
        Caminhao caminhao = new Caminhao("ABC1234", 500.0, 3);
        Pacote pacote1 = new Pacote("BR999", 10.5);
        Rota rota1 = new Rota(pacote1, caminhao);
        rota1.iniciarEntrega();
        System.out.println("Status do pacote: " + pacote1.getStatus());

        // Entrega de moto 
        Moto moto = new Moto("XYZ9876", 30.0, true);
        Pacote pacote2 = new Pacote("BR111", 5.0);
        Rota rota2 = new Rota(pacote2, moto);
        rota2.iniciarEntrega();
        System.out.println("Status do pacote: " + pacote2.getStatus());
    }
}
