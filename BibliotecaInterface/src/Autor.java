// Classe chamada Autor que esta herdando a classe Pessoa

public class Autor extends Pessoa {
	
	// Declarar a variável biografia
	
    private String biografia;

    // Getters e setters

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    
    //Retorna a Biografia do autor
    
    //Converter um objeto em uma representação String

    @Override
    public String toString() {
        return super.toString() + "\nBiografia: " + biografia;
    }
}
