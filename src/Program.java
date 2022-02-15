import avalon.activity.Explanation;
import avalon.activity.Question;
import avalon.activity.Video;
import avalon.activity.enums.TypeActivity;
import avalon.activity.enums.TypeQuestion;
import avalon.alternative.Alternative;
import avalon.category.Category;
import avalon.category.SubCategory;
import avalon.course.Course;
import avalon.section.Section;

public class Program {
    public static void main(String[] args) {


        Category category = new Category("Programação","programacao","Cursos de Programação","Aprenda com mais diversos cursos e conteúdos de programação em diversas linguagens",true,1,"caminhodoicone.com.br","#363636");


        SubCategory subCategory = new SubCategory("Programação", "programacao", "Lógica de programação", "guia de estudos", true, 1, category.getName());

        Course course = new Course("Java OO: Introdução à Orientação a Objetos", "java-introducao-orientacao-objetos", 8, "Paulo Silveira", subCategory);

        Section section = new Section("O problema do paradigma procedural", "section1",course);

        System.out.println("Informações do curso");
        System.out.println("Nome: "+course.getName());
        System.out.println("Código: "+course.getCode());
        System.out.println("Tempo estimado: "+course.getEstimatedTime());
        System.out.println("Visibilidade: "+(course.isVisibility()?"Público":"Privado"));
        System.out.println("Público Alvo: "+course.getTarget());
        System.out.println("Instrutor: "+course.getInstructor());
        System.out.println("Ementa: "+course.getCourseProgram());
        System.out.println("Skills desenvolvidas: "+course.getSkillsDeveloped());

        Explanation explanation = new Explanation("Mão na massa: Criando as primeiras classes","30957",true,1,section, TypeActivity.EXPLANATION,"Vamos começar a implementar nosso projeto!");

        Video video = new Video("Introdução","29414",true,1,section,TypeActivity.VIDEO,"https://cursos.alura.com.br/course/java-introducao-orientacao-objetos/task/29414",2,"Olá, meu nome é Paulo Silveira. Neste curso iremos desvendar a orientação a objetos.");

        Question question  = new Question("Idéia central do paradigma OO","30949",true,3,section,TypeActivity.QUESTION, "Com base no que você ouviu no vídeo, selecione a alternativa que expressa a ideia central do paradigma da Orientação a Objetos.", TypeQuestion.MULTIPLE_CHOICE);

        Alternative alternative = new Alternative("Todo código deve estar dentro de uma classe.",1,false,"Apesar dessa sentença ser uma consequência das linguagens orientadas a objetos, não é sua idéia central, uma vez que é possível encontrarmos classes com códigos totalmente procedurais (podemos ter várias classes que representam formulários com campo CPF e código de validação duplicado).",question);

        Alternative alternative2 = new Alternative("Dado e funcionalidade sobre ele andam juntos.",2,true,"No exemplo do vídeo, se o CPF e sua função de validação andassem juntos, teríamos aplicado OO.",question);

        Alternative alternative3 = new Alternative("Getters e setters devem ser usados em suas classes.",3,false,"Como você vai aprender mais a frente, getters e setters são usados para esconder o mecanismo de funcionamento de determinado comportamento. Contudo, o uso deles não garante que o código será orientado a objetos.",question);


        System.out.println();
        System.out.println();
        System.out.println("Primeira Atividade");
        System.out.println("Tipo de Atividade: "+explanation.getTypeActivity().name());
        System.out.println("Título: "+explanation.getTitle());
        System.out.println("Código: "+explanation.getCode());
        System.out.println("Está ativo? "+(explanation.isActive()?"Sim":"Não"));
        System.out.println("Seção: "+explanation.getSection().getName());
        System.out.println("Descrição: "+explanation.getDescription());


        System.out.println();
        System.out.println();
        System.out.println("Segunda Atividade");
        System.out.println("Tipo de Atividade: "+video.getTypeActivity().name());
        System.out.println("Título: "+video.getTitle());
        System.out.println("Código: "+video.getCode());
        System.out.println("Está ativo? "+(video.isActive()?"Sim":"Não"));
        System.out.println("Seção: "+video.getSection().getName());
        System.out.println("Caminho: "+video.getUrl());
        System.out.println("Transcrição: "+video.getTranscription());
        System.out.println("Duração: "+video.getDuration());


        System.out.println();
        System.out.println();
        System.out.println("Terceira Atividade");
        System.out.println("Tipo de Atividade: "+question.getTypeActivity().name());
        System.out.println("Título: "+question.getTitle());
        System.out.println("Código: "+question.getCode());
        System.out.println("Está ativo? "+(question.isActive()?"Sim":"Não"));
        System.out.println("Seção: "+question.getSection().getName());
        System.out.println("Enunciado: "+question.getStatement());
        System.out.println("Alternativas: ");
        System.out.println("Alternativa 1: "+alternative.getDescription());
        System.out.println("Alternativa 2: "+alternative2.getDescription());
        System.out.println("Alternativa 3: "+alternative3.getDescription());

        System.out.println();
        System.out.println();
        System.out.println("Validação Categoria");
        System.out.println("Validando nulos");

        Category categoryValidationNullName = new Category(null,"programacao","Cursos de Programação","Aprenda com mais diversos cursos e conteúdos de programação em diversas linguagens",true,1,"caminhodoicone.com.br","#363636");

        Category categoryValidationNullCode = new Category("java",null,"Cursos de Programação","Aprenda com mais diversos cursos e conteúdos de programação em diversas linguagens",true,1,"caminhodoicone.com.br","#363636");

        System.out.println();
        System.out.println();
        System.out.println("Validando brancos e vazios");
        Category categoryValidationEmptyBlankName = new Category(" ", "programacao", "Cursos de Programação", "Aprenda com mais diversos cursos e conteúdos de programação em diversas linguagens", true, 1, "caminhodoicone.com.br", "#363636");

        Category categoryValidationEmptyBlankCode = new Category("java", " ", "Cursos de Programação", "Aprenda com mais diversos cursos e conteúdos de programação em diversas linguagens", true, 1, "caminhodoicone.com.br", "#363636");

        System.out.println();
        System.out.println();

        System.out.println("Validação Cor");


        Category categoryColorValidation = new Category("Programação", "programacao", "Cursos de Programação", "Aprenda com mais diversos cursos e conteúdos de programação em diversas linguagens", true, 1, "caminhodoicone.com.br", "#35454");

        System.out.println();
        System.out.println();

        System.out.println("Validação SubCategoria");
        System.out.println("Validando nulos");

        SubCategory subCategoryNullValidationName = new SubCategory(null, "programacao", "Lógica de programação", "guia de estudos", true, 1, category.getName());
        SubCategory subCategoryNullValidationCode = new SubCategory("java", null, "Lógica de programação", "guia de estudos", true, 1, category.getName());

        System.out.println();
        System.out.println();
        System.out.println("Validando brancos e vazios");

        System.out.println();
        System.out.println();
        SubCategory subCategoryBlankValidationName = new SubCategory(" ", "programacao", "Lógica de programação", "guia de estudos", true, 1, category.getName());
        SubCategory subCategoryBlankValidationCode = new SubCategory("java", " ", "Lógica de programação", "guia de estudos", true, 1, category.getName());
        System.out.println("Validação Curso");


        System.out.println();
        System.out.println("Validando nulos");

        Course courseNullValidationName = new Course(null, "java-introducao-orientacao-objetos", 8, "Paulo Silveira", subCategory);
        Course courseNullValidationCode = new Course("java", null, 8, "Paulo Silveira", subCategory);
        Course courseNullValidationInstructor = new Course("java", "java-introducao-orientacao-objetos", 8, null, subCategory);
        Course courseNullValidationSubCategory = new Course("java", "java", 8, "Paulo Silveira", null);


        System.out.println();
        System.out.println("Regra de Tempo estimado de curso");
        Course courseMinEstimedTimeValidationCode = new Course("java", "java", -8, "Paulo Silveira", subCategory);

        Course courseMaxEstimedTimeValidationCode = new Course("java", "java", 21, "Paulo Silveira", subCategory);

        System.out.println();
        System.out.println();

        System.out.println("Validação brancos e vazios");

        Course courseEmptyBlankValidationName = new Course(" ", "java-introducao-orientacao-objetos", 8, "Paulo Silveira", subCategory);
        Course courseEmptyBlankValidationCode = new Course("java", " ", 8, "Paulo Silveira", subCategory);
        Course courseEmptyBlankValidationInstructor = new Course("java", "java-introducao-orientacao-objetos", 8, " ", subCategory);


        System.out.println();
        System.out.println();

        System.out.println("Validação Seção");
        System.out.println();
        System.out.println("Validação nulos");
        Section sectionNullValidationName = new Section(null, "section1", course);
        Section sectionNullValidationCode = new Section("java", null, course);
        Section sectionEmptyBlankValidationCourse = new Section("java", "java", null);

        System.out.println();
        System.out.println("Validação brancos e vazio");
        Section sectionEmptyBlankValidationName = new Section(" ", "section1", course);
        Section sectionEmptyBlankValidationCode = new Section("java", " ", course);
        System.out.println();
        System.out.println("Ordem");
        Section sectionOrderValidationCode = new Section("java", "java", -1, true, true, course);

        System.out.println();
        System.out.println();
        System.out.println("Validação Atividades -> Explicação");

        Explanation explanationNullValidationTitle = new Explanation(null, "30957", true, 1, section, TypeActivity.EXPLANATION, "Vamos começar a implementar nosso projeto!");

        Explanation explanationNullValidationCode = new Explanation("java", null, true, 1, section, TypeActivity.EXPLANATION, "Vamos começar a implementar nosso projeto!");

        System.out.println("Validação brancos e nulos");

        Explanation explanationEmptyBlankValidationTitle = new Explanation(" ", "30957", true, 1, section, TypeActivity.EXPLANATION, "Vamos começar a implementar nosso projeto!");

        Explanation explanationEmptyBlankValidationCode = new Explanation("java", " ", true, 1, section, TypeActivity.EXPLANATION, "Vamos começar a implementar nosso projeto!");
        System.out.println();
        System.out.println();

        System.out.println("Validação Atividades -> Vídeo");
        Video videoNullValidation = new Video("Introdução", "29414", true, 1, null, TypeActivity.VIDEO, "https://cursos.alura.com.br/course/java-introducao-orientacao-objetos/task/29414", 2, "Olá, meu nome é Paulo Silveira. Neste curso iremos desvendar a orientação a objetos.");


    }
}
