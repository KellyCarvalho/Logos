import Logos.category.Category;
import Logos.category.enums.CategoryStatus;
import Logos.course.Course;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;
import Logos.utils.CsvReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static Logos.utils.CsvReader.readCategories;
import static Logos.utils.CsvReader.readSubCategories;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvReaderTest {
    private static List<Category> categories;
    private static List<SubCategory> subcategories;
    private static List<Course> courses;
    private static Category categoryProgramming;
    private static Category categoryDevops;
    private static Category categoryBusiness;
    private static SubCategory subCategoryJava;
    private static SubCategory subCategoryJavaAndPersistence;
    private static SubCategory subCategoryPhp;
    private static SubCategory subCategoryCobol;
    private static SubCategory subCategoryBuilds;
    private static Course courseGit;
    private static Course courseJpa;
    private static Course courseJavaOO;
    private static Course courseJdk;

    @BeforeAll
    private static void setUp() {
        setUpCategory();
        setUpSubCategory();
        setUpCourse();
    }

    @Test
    void readCategoriesShouldReturnAListOfCategories() {
        List<Category> categoriesCsv = readCategories("files/planilha-dados-escola - Categoria.csv");
        assertEquals(categories, categoriesCsv);
    }

    @Test
    void readSubCategoriesShouldReturnAListOfSubCategories() {
        List<SubCategory> subCategoriesCsv = readSubCategories("files/planilha-dados-escola - Subcategoria.csv", categories);
        assertEquals(subcategories, subCategoriesCsv);
    }

    @Test
    void readCoursesShouldReturnAListOfCourses() {
        List<Course> coursesCsv = CsvReader.readCourses("files/planilha-dados-escola - Curso.csv", subcategories);
        assertEquals(coursesCsv, courses);
    }

    private static void setUpCategory() {
        categoryProgramming = new Category("Programação", "programacao",
                "Programe nas principais linguagens e plataformas. Iniciantes são bem vindos nos cursos de lógica e JavaScript.",
                CategoryStatus.ACTIVE, 1, "https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png",
                "#00c86f");

        categoryDevops = new Category("DevOps", "devops",
                "Aprenda Git. Entenda a entrega contínua. Estude Linux. Gerencie servidores na nuvem. Explore o mundo de Internet das coisas e da robótica.",
                CategoryStatus.ACTIVE, 2, "https://www.alura.com.br/assets/api/formacoes/categorias/512/devops-transparent.png",
                "#f16165");

        categoryBusiness = new Category("Business", "business",
                "Agilidade. Práticas de gestão. Vendas. Liderança.",
                CategoryStatus.DISABLED, 0, "https://www.alura.com.br/assets/api/formacoes/categorias/512/inovacao-gestao-transparent.png",
                "#ff8c2a");
        categories = Arrays.asList(categoryProgramming, categoryDevops, categoryBusiness);
    }

    private static void setUpSubCategory() {
        subCategoryJava = new SubCategory("Java", "java", "Java é uma grande plataforma presente" +
                " em todo lugar: de corporações à bancos e governo. Desenvolva aplicações robustas com um back-end e construa APIs.",
                SubCategoryStatus.ACTIVE, 1, categoryProgramming);

        subCategoryJavaAndPersistence = new SubCategory("Java e Persistência", "java-e-persistencia", "",
                SubCategoryStatus.ACTIVE, 2, categoryProgramming);

        subCategoryPhp = new SubCategory("PHP", "php", "PHP é uma das linguagens mais utilizadas.",
                SubCategoryStatus.ACTIVE, 3, categoryProgramming);

        subCategoryCobol = new SubCategory("COBOL", "cobol", "",
                SubCategoryStatus.DISABLED, 0, categoryProgramming);

        subCategoryBuilds = new SubCategory("Builds e Controle de versão", "builds-e-controle-de-versao",
                "As ferramentas mais utilizadas para desenvolvimento: controle de versão com Git e Github além de build da aplicação através de Maven.",
                SubCategoryStatus.ACTIVE, 1, categoryDevops);
        subcategories = Arrays.asList(subCategoryJava, subCategoryJavaAndPersistence, subCategoryPhp, subCategoryCobol, subCategoryBuilds);
    }

    private static void setUpCourse() {
        courseGit = new Course("Git e Github para Sobrevivência", "git-e-github-para-sobrevivencia",
                6, true,
                "Desenvolvedores em qualquer linguagem ou plataforma que desejam mais segurança para seus projetos" +
                        " com as ferramentas de controle de versão Git e GitHub.", "Mario Souto",
                "-O que é Git? <br> *Introdução <br> *Para que serve Git? <br> *Utilidade de um VCS <br> " +
                        "*Instalando o Git <br> *Para saber mais: Instalação <br> *Repositórios <br> *Primeiros passos <br>" +
                        "  <br> -Iniciando os trabalhos <br> *Salvando alterações <br> *Primeira configuração do Git <br> " +
                        "*Para saber mais: git status <br> *Vendo o histórico <br> *Para saber mais: git log <br> " +
                        "*Ignorando arquivos <br> *Para saber mais: Quando commitar <br>  <br> -Compartilhando o " +
                        "trabalho <br> *Repositórios remotos <br> *Servidor Git <br> *Trabalhando com repositórios remotos " +
                        "<br> *Sincronizando os dados <br> *Compartilhamos as alterações <br> *GitHub <br> *Para saber mais:" +
                        " GitHub <br>  <br> -Trabalhando em equipe <br> *Branches <br> *Para saber mais: Ramificações <br>" +
                        " *Unindo o trabalho <br> *Merge de branches <br> *Atualizando a branch <br> *Rebase vs Merge <br>" +
                        " *Resolvendo conflitos <br> *Para saber mais: Conflitos com rebase <br>  <br> -Manipulando as versões <br>" +
                        " *Ctrl+Z no Git <br> *Desfazendo o trabalho <br> *Guardando para depois <br> *Salvando temporariamente <br> " +
                        "*Viajando no tempo <br> *Checkout <br>  <br> -Gerando entregas <br> *Vendo as alterações <br>" +
                        " *Exibição das mudanças com o diff <br> *Tags e releases <br> *Tags no GitHub <br> " +
                        "*Consolidando o seu conhecimento", "Descubra o que é Git e Github? <br>" +
                " Entenda um sistema de controle de versão <br> Salve e recupere seu código em diferentes versões <br>" +
                " Resolva merges e conflitos <br> Trabalhe com diferentes branches", subCategoryBuilds);

        courseJpa = new Course("Java e JPA: Consultas avançadas performance e modelos complexos", "java-jpa-consultas" +
                "-avancadas-performance-modelos-complexos",
                10, true, "Pessoas desenvolvedoras que já conhecem o básico de JPA " +
                "e queiram aprofundar os conhecimentos.", "Rodrigo Ferreira",
                "-Mais relacionamentos <br> *Apresentação <br> " +
                        "*Mapeando novas entidades <br> *Relacionamentos many-to-many <br> *Relacionamentos bidirecionais <br> " +
                        "*Teste do relacionamento bidirecional <br>  <br> -Consultas avançadas <br> *Consultas com funções" +
                        " de agregação <br> *Consultas para relatórios <br> *Consultas com select new <br> *Utilizando Named " +
                        "Queries <br>  <br> -Performance de consultas <br> *Entendendo Lazy e Eager <br> *Consultas com Join" +
                        " Fetch <br>  <br> -Criteria API <br> *Consultas com parâmetros dinâmicos <br> *Consultas com Criteria API" +
                        " <br>  <br> -Outros tópicos <br> *Simplificando entidades com Embeddable <br> *Mapeamento de herança <br> " +
                        "*Mapeamento de chaves compostas <br> *Conclusão",
                "Saiba como modelar corretamente relacionamentos bidirecionais <br> Aprenda a utilizar o " +
                        "recurso de select new para realizar consultas avançadas <br> Entenda a diferença entre" +
                        " relacionamentos EAGER e LAZY <br> Conheça o recurso de join fetch para planejar queries <br>" +
                        " Conheça a API de Criteria da JPA <br> Saiba como mapear entidades que utilizam herança e chave composta",
                subCategoryJavaAndPersistence);

        courseJavaOO = new Course("Java OO: Introdução à Orientação a Objetos", "java-introducao-orientacao-objetos", 8,
                true, "Desenvolvedores que estão começando com Java e querem aprender mais sobre OO.",
                "Paulo Silveira", "-O problema do paradigma procedural <br> *Paradigma procedural" +
                " vs Objetos <br> *Idéia central do paradigma OO <br> *Cheiro procedural <br>  <br> -Começando com Orientação " +
                "a Objetos <br> *Primeira classe - Contas <br> *Características dos objetos <br> *Instanciação atributos e referências" +
                " <br> *Definindo tipos <br> *Segunda Instância <br> *Valores default de atributos <br> *Definindo valor de atributos" +
                " <br> *Referências vs Objetos <br> *Referências de objetos <br> *Mão na massa: Criando as primeiras classes " +
                "<br>  <br> -Definindo comportamento <br> *Nosso primeiro método <br> *Sobre métodos <br> *Como chamar um método?" +
                " <br> *Você conhece o this? <br> *Métodos com retorno <br> *Métodos validos <br> *Onde usar o this? <br> " +
                "*Métodos com referência e mais retorno <br> *Declaração do método <br> *Mão na massa: Criando métodos <br>  " +
                "<br> -Composição de objetos <br> *Composição de Objetos <br> *Extraindo o que é comum  <br> *Referência Null " +
                "<br> *Problema não esperado <br> *Solucionando o problema no código  <br> *O que aprendemos? <br> *Mão na massa: " +
                "Referências <br>  <br> -Encapsulamento e visibilidade <br> *Atributos privados e encapsulamento <br> " +
                "*Público x Privado <br> *Getters e Setters <br> *Criando Getters e Setters <br> *Getters e Setters de referência " +
                "<br> *Vantagens de atributos privados <br> *Mão na massa: Criando Getters e Setters <br> *Para saber mais: " +
                "Cuidado com o Modelo Anêmico <br>  <br> -Construtores e membros estáticos <br> *Construtores <br> *Utilizando " +
                "Construtores <br> *Aonde está o erro? <br> *Static <br> *Por que não soma? <br> *Mãos na massa: Criando construtores" +
                " e variáveis estáticas <br> *Para saber mais: Reaproveitamento entre construtores", "Domine o paradigma" +
                " de programação mais usado no mercado de trabalho <br> Entenda o que são referências e objetos <br> " +
                "Use atributos métodos da instancia e da classe <br> Define objetos através de construtores <br> Aprenda " +
                "sobre encapsulamento", subCategoryJava);

        courseJdk = new Course("Java JRE e JDK: Escreva o seu primeiro código com Eclipse", "java-primeiros-passos",
                8, true, "Desenvolvedores que querem começar com a linguagem Java.",
                "Paulo Silveira", "-O que é Java? <br> *A plataforma Java <br> *Benefício da JVM <br>" +
                " *Quais características? <br> *Quais sistemas? <br> *Bytecode vs EXE? <br> *Sobre o Bytecode <br> *Para " +
                "saber mais: o nome Bytecode <br>  <br> -Instalação e o primeiro programa <br> *Instalação do JDK no Windows" +
                " <br> *JRE vs JDK <br> *Para saber mais: JVM vs JRE vs JDK <br> *Compile e rode seu primeiro programa Java <br>" +
                " *Entrada da aplicação <br> *Sobre a compilação e execução <br> *Compilar e executar <br> *Mão na massa: Instalando" +
                " o JDK <br> *Mão na massa: Escrevendo nosso primeiro código <br>  <br> -Começando com Eclipse <br> " +
                "*Instalando o Eclipse <br> *Mão na massa: Instale a IDE Eclipse <br> *Sobre IDE's e editores <br> *Dentro" +
                " do Eclipse IDE <br> *Nosso programa rodando no Eclipse <br> *Mão na massa: Rodando nosso programa no Eclipse" +
                " <br> *Projeto Java <br> *Sobre a View Navigator <br>  <br> -Tipos e variáveis <br> *Tipo inteiro: int <br> " +
                "*Criando uma variável numérica <br> *Mão na massa: Utilizando o tipo int <br> *Tipo flutuante: double <br> " +
                "*Operações entre numeros <br> *Mão na massa: Utilizando o tipo double <br> *Conversões e outros tipos <br> " +
                "*Imprimindo texto e números <br> *Mão na massa: Algumas conversões em Java <br> *Para saber mais: Type Casting" +
                " <br>  <br> -Trabalhando com caracteres <br> *Char e String <br> *Declarando String e char <br> " +
                "*Qual será o resultado? <br> *Variáveis guardam valores <br> *Concatenação de String e inteiros <br> " +
                "*Mão na massa: Praticando char e String <br>  <br> -Praticando condicionais <br> *Testes com IF <br> " +
                "*Trabalhando com if <br> *Mão na massa: A condicional if <br> *Boolean condicionais <br> *Tipo boolean " +
                "<br> *Operador lógico <br> *Mão na massa: Um pouco mais de if <br> *Escopo e inicialização de variáveis " +
                "<br> *Declaração da variável <br> *Mão na massa: Escopo de variáveis <br> *Opcional: Alíquota com ifs <br> " +
                "*Para saber mais: o comando switch <br>  <br> -Controlando fluxo com laços <br> *Laço com while <br> " +
                "*Enquanto isso o while... <br> *Fixando o laço while <br> *Escopo nos laços <br> *Um erro de compilação... " +
                "<br> *Laço com for <br> *Transformando while em for <br> *Mão na massa: Laços <br> *Laços encadeados <br> " +
                "*Mais laços com break <br> *Fixando o comando break <br> *Exercitando laços aninhados e break <br> *Mão na " +
                "massa: Aprofundando laços <br> *Desafio Opcional: Múltiplos de 3 <br> *Desafio opcional: Fatorial",
                "JVM? JDK? JRE? Aprenda o que são essas siglas? <br> Compile e execute código java <br> " +
                        "Aprenda a usar Eclipse <br> Veja como usar variáveis e controle de fluxo <br> Conheça os principais" +
                        " tipos do Java", subCategoryJava);
        courses = Arrays.asList(courseGit, courseJpa, courseJavaOO, courseJdk);
    }
}
