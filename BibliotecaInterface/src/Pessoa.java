// Classe chamada Pessoa, que será herdada por Autor

public class Pessoa {
	
	// Declarar as variáveis nome, idade e endereço
    
	private String nome;
    private int idade;
    private String endereco;

    // Getters e setters da classe pessoa

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    //Retorna uma lista com o Nome, Idade e Endereço da pessoa
    
    //Converter um objeto em uma representação String
    
    @Override
    public String toString() {
        return "Nome: " + nome + "\nIdade: " + idade + "\nEndereço: " + endereco;
    }


}

