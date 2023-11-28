import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Uma aplicação GUI para um sistema de biblioteca, permitindo que os usuários interajam
 * e gerenciem livros e autores em uma biblioteca.
 */

public class BibliotecaGUI extends JFrame {
    private Biblioteca biblioteca;

    private JTextArea outputTextArea;

    /**
     * Construtor da classe BibliotecaGUI.
     *
     * Uma instância da classe Biblioteca representando o sistema de biblioteca.
     */
    public BibliotecaGUI(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        // Configurando as propriedades principais do JFrame
        setTitle("Sistema de Biblioteca");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializando e configurando os componentes da GUI
        initComponents();

        // Repintando e revalidando o JFrame
        SwingUtilities.invokeLater(() -> {
            this.repaint();
            this.revalidate();
        });
    }

    /**
     * Inicializa os componentes gráficos da GUI.
     */
    private void initComponents() {
        // Criando o painel principal com BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Criando uma área de texto não editável
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        // Adicionando uma barra de rolagem à área de texto
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Criando um painel para botões
        JPanel buttonPanel = new JPanel();

        // Criando botões para várias ações
        JButton listarLivrosButton = new JButton("Listar Livros");
        JButton adicionarLivroButton = new JButton("Adicionar Livro");
        JButton listarAutoresButton = new JButton("Listar Autores");
        JButton adicionarAutorButton = new JButton("Adicionar Autor");
        JButton removerLivroButton = new JButton("Remover Livro");

        

        // Adicionando os botões ao painel de botões
        buttonPanel.add(listarLivrosButton);
        buttonPanel.add(adicionarLivroButton);
        buttonPanel.add(listarAutoresButton);
        buttonPanel.add(adicionarAutorButton);
        buttonPanel.add(removerLivroButton);

        // Adicionando o painel de botões ao painel principal
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adicionando ouvintes de ação aos botões
        listarLivrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarLivros();
            }
        });

        adicionarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarLivro();
            }
        });

        listarAutoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarAutores();
            }
        });

        adicionarAutorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAutor();
            }
        });

        removerLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerLivro();
            }
        });

        // Adicionando o painel principal ao JFrame
        add(mainPanel);
    }

    /**
     * Exibe um diálogo ao usuário com opções para listar livros.
     * Invoca métodos correspondentes com base na escolha do usuário.
     */
    private void listarLivros() {
        // Opções para o diálogo de escolha do JOptionPane
        String[] opcoes = {"Listar Todos os Livros", "Buscar por ISBN"};

        // Perguntando ao usuário pela escolha
        int escolha = JOptionPane.showOptionDialog(
                this,
                "Escolha uma opção:",
                "Listar Livros",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        // Executando a ação correspondente à escolha do usuário
        switch (escolha) {
            case 0:
                // Listar todos os livros
                listarTodosLivros();
                break;
            case 1:
                // Buscar por ISBN
                listarLivrosPorISBN();
                break;
            default:
                // O usuário clicou em Cancelar ou fechou a janela
                break;
        }
    }

    /**
     * Lista todos os livros na biblioteca e os exibe na área de texto de saída.
     */
    private void listarTodosLivros() {
        outputTextArea.setText("");
        outputTextArea.append("Lista de Livros na Biblioteca:\n");
        for (Livro livro : biblioteca.getListaLivros()) {
            outputTextArea.append(livro.toString() + "\n\n");
        }
    }

    /**
     * Lista livros com base no ISBN fornecido pelo usuário e exibe o resultado na área de texto de saída.
     */
    private void listarLivrosPorISBN() {
        String isbnDesejado = JOptionPane.showInputDialog("Digite o ISBN do livro que deseja encontrar:");

        // Lidando com o caso em que a entrada está vazia ou o usuário clicou em Cancelar
        if (isbnDesejado == null || isbnDesejado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ISBN não pode ser vazio.");
            return;
        }

        boolean livroEncontrado = false;
        outputTextArea.setText("");
        outputTextArea.append("Resultado da Busca por ISBN:\n");

        // Procurando o livro com o ISBN fornecido
        for (Livro livro : biblioteca.getListaLivros()) {
            if (livro.getISBN().equals(isbnDesejado)) {
                outputTextArea.append(livro.toString() + "\n\n");
                livroEncontrado = true;
                break; // Encerra o loop assim que o livro é encontrado
            }
        }

        // Exibindo uma mensagem se o livro não for encontrado
        if (!livroEncontrado) {
            JOptionPane.showMessageDialog(this, "Livro com o ISBN " + isbnDesejado + " não encontrado na biblioteca.");
        }
    }

    /**
     * Solicita ao usuário informações para adicionar um novo livro à biblioteca.
     * Valida a entrada e adiciona o livro à biblioteca.
     */
    private void adicionarLivro() {
        // Solicitando o título do livro ao usuário
        String titulo = JOptionPane.showInputDialog("Digite o título do livro:");
        if (titulo == null || titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Título do livro não pode ser vazio.");
            return;
        }

        // Solicitando o ISBN do livro ao usuário
        String ISBN = getNumericInput("Digite o ISBN do livro:");
        if (ISBN == null || ISBN.isEmpty()) {
            return; // Se o usuário clicar em Cancelar, interrompe a operação
        }

        // Solicitando o preço do livro ao usuário
        String precoStr = JOptionPane.showInputDialog("Digite o preço do livro:");
        if (precoStr == null || precoStr.isEmpty()) {
            return; // Se o usuário clicar em Cancelar, interrompe a operação
        }

        // Verificando se a entrada do preço contém um número decimal válido
        try {
            // Substituir vírgulas por pontos para garantir que o Double.parseDouble funcione corretamente
            precoStr = precoStr.replace(',', '.');
            double preco = Double.parseDouble(precoStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, adicione um número válido no campo de preço.");
            return;
        }

        // Apresentando autores existentes para escolha do usuário
        String[] nomesAutores = biblioteca.getListaAutores().stream()
                .map(Autor::getNome)
                .toArray(String[]::new);

        // Adicionando a opção de adicionar um novo autor
        String[] opcoesAutores = new String[nomesAutores.length + 1];
        System.arraycopy(nomesAutores, 0, opcoesAutores, 0, nomesAutores.length);
        opcoesAutores[opcoesAutores.length - 1] = "Adicionar Novo Autor";

        // Solicitando ao usuário que escolha um autor
        String nomeAutor = (String) JOptionPane.showInputDialog(
                this,
                "Escolha um autor:",
                "Escolher Autor",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoesAutores,
                null
        );

        Autor autorEscolhido = null;
        if (nomeAutor != null) {
            if (nomeAutor.equals("Adicionar Novo Autor")) {
                // Adicionando um novo autor
                autorEscolhido = criarNovoAutor();
                biblioteca.adicionarAutor(autorEscolhido);
            } else {
                autorEscolhido = biblioteca.getListaAutores().stream()
                        .filter(autor -> autor.getNome().equals(nomeAutor))
                        .findFirst()
                        .orElse(null);
            }
        }

        // Exibindo uma mensagem de erro se a escolha do autor for inválida
        if (autorEscolhido == null) {
            JOptionPane.showMessageDialog(this, "Escolha de autor inválida.");
            return;
        }

        // Criando um novo livro e adicionando-o à biblioteca
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setISBN(ISBN);
        livro.setPreco(Double.parseDouble(precoStr));
        livro.setAutor(autorEscolhido);

        biblioteca.adicionarLivro(livro);
        JOptionPane.showMessageDialog(this, "Livro adicionado à biblioteca.");
    }

    /**
     * Solicita ao usuário uma entrada numérica, garantindo que apenas entrada válida seja aceita.
     *
     * @param message A mensagem a ser exibida ao usuário.
     * @return Uma string contendo a entrada numérica válida.
     */
    private String getNumericInput(String message) {
        String input;
        do {
            input = JOptionPane.showInputDialog(message);
            if (input == null) {
                return null; // Se o usuário clicar em Cancelar, interrompe a operação
            }
            if (!input.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Por favor, adicione apenas números.");
            }
        } while (!input.matches("\\d+"));
        return input;
    }

    /**
     * Cria um novo autor solicitando informações ao usuário.
     *
     * Retorna uma instância da classe Autor com as informações fornecidas pelo usuário.
     */
    private Autor criarNovoAutor() {
        String nomeAutor = JOptionPane.showInputDialog("Digite o nome do autor:");
        int idadeAutor = Integer.parseInt(getNumericInput("Digite a idade do autor:"));
        String enderecoAutor = JOptionPane.showInputDialog("Digite o endereço do autor:");
        String biografiaAutor = JOptionPane.showInputDialog("Digite a biografia do autor:");

        Autor autor = new Autor();
        autor.setNome(nomeAutor);
        autor.setIdade(idadeAutor);
        autor.setEndereco(enderecoAutor);
        autor.setBiografia(biografiaAutor);

        return autor;
    }

    /**
     * Lista todos os autores na biblioteca e os exibe na área de texto de saída.
     */
    private void listarAutores() {
        outputTextArea.setText("");
        outputTextArea.append("Lista de Autores na Biblioteca:\n");
        for (Autor autor : biblioteca.getListaAutores()) {
            outputTextArea.append(autor.toString() + "\n\n");
        }
    }

    /**
     * Adiciona um novo autor à biblioteca solicitando informações ao usuário.
     */
    private void adicionarAutor() {
        Autor autor = criarNovoAutor();
        biblioteca.adicionarAutor(autor);
        JOptionPane.showMessageDialog(this, "Autor adicionado à biblioteca.");
    }

    /**
     * Remove um livro da biblioteca com base no título fornecido pelo usuário.
     */
    private void removerLivro() {
        String titulo = JOptionPane.showInputDialog("Digite o título do livro que deseja remover:");

        Livro livroParaRemover = null;
        for (Livro livro : biblioteca.getListaLivros()) {
            if (livro.getTitulo().equals(titulo)) {
                livroParaRemover = livro;
                break;
            }
        }

        if (livroParaRemover != null) {
            biblioteca.removerLivro(livroParaRemover);
            JOptionPane.showMessageDialog(this, "Livro removido da biblioteca.");
        } else {
            JOptionPane.showMessageDialog(this, "Livro não encontrado na biblioteca.");
        }
    }
    
    /**
     * Método principal que cria uma instância da Biblioteca e da BibliotecaGUI,
     * tornando a GUI visível para o usuário.
     */
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        BibliotecaGUI bibliotecaGUI = new BibliotecaGUI(biblioteca);
        bibliotecaGUI.setVisible(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

