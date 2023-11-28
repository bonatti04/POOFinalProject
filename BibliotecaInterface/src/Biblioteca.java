import java.util.ArrayList;
import java.util.List;

// Classe chamada Biblioteca

public class Biblioteca {
	
	// Declara uma lista de objetos do tipo Livro e outra para Autores
	
    private List<Livro> listaLivros;
    private List<Autor> listaAutores;
    
    //Construtor da classe Biblioteca que inicializa as listas como novas intancias de ArrayList

    public Biblioteca() {
        this.listaLivros = new ArrayList<>();
        this.listaAutores = new ArrayList<>();
    }

    // Métodos para adicionar e remover autores
    
    public void adicionarAutor(Autor autor) {
        listaAutores.add(autor);
    }

    public void removerAutor(Autor autor) {
        listaAutores.remove(autor);
        
        // Também remove os livros associados a esse autor
        
        listaLivros.removeIf(livro -> livro.getAutor().equals(autor));
    }

    // Métodos para obter a lista de autores e livros
    
    public List<Autor> getListaAutores() {
        return listaAutores;
    }

    public List<Livro> getListaLivros() {
        return listaLivros;
    }

    // Métodos para adicionar e remover livros
    
    public void adicionarLivro(Livro livro) {
        listaLivros.add(livro);
    }

    public void removerLivro(Livro livro) {
        listaLivros.remove(livro);
    }
}
