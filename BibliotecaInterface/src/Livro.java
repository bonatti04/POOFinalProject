// Classe chamada Livro

public class Livro {
	
	// Declarar as variáveis titulo, ISBN, preco e autor
	
	// Livro está criando uma instancia da classe Autor
	
    private String titulo;
    private String ISBN;
    private double preco;
    private Autor autor;

    // Getters e setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    //Retorna uma lista com o Título, ISBN, Preço e Autor do livro
    
    //Converter um objeto em uma representação String
    
    @Override
    public String toString() {
        return "Título: " + titulo + "\nISBN: " + ISBN + "\nPreço: R$" + preco + "\nAutor:\n" + autor.toString();
    }
}
