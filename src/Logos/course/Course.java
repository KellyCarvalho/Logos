package Logos.course;

import Logos.subCategory.SubCategory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Logos.commonValidator.ObjectValidator.isObjectValid;
import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static Logos.commonValidator.StringValidator.isValidCode;
import static Logos.course.validation.CourseValidation.isValidEstimatedTime;
import static Logos.subCategory.SubCategory.filterSubCategoriesByCode;

public class Course {

    private String name;
    private String code;
    private int estimatedTime;
    private boolean visibility;
    private String targetAudience;
    private String instructor;
    private String courseProgramDescription;
    private String skillsDeveloped;
    private SubCategory subCategory;

    public Course(String name, String code, int estimatedTime, String instructor, SubCategory subCategory) {
        isValidCode(code, "Código do curso não é válido ou está null ou vazio - deve ter caracteres de a-z - algarismos de 0-9 - Único caractere especial permitido é o hífen");
        isNotBlankEmptyOrNull(name, "Nome do curso é requerido, não pode ser vazio ou nulo");
        isValidEstimatedTime(estimatedTime, 1, 20);
        isNotBlankEmptyOrNull(instructor, "Nome do instrutor não pode ser vazio");
        isObjectValid(subCategory, "SubCategoria é obrigatória e não pode estar nula");
        this.name = name;
        this.code = code;
        this.estimatedTime = estimatedTime;
        this.instructor = instructor;
        this.subCategory = subCategory;
    }

    public Course(String name, String code, int estimatedTime, boolean visibility, String targetAudience, String instructor, String courseProgramDescription, String skillsDeveloped, SubCategory subCategory) {
        this(name, code, estimatedTime, instructor, subCategory);
        this.visibility = visibility;
        this.targetAudience = targetAudience;
        this.courseProgramDescription = courseProgramDescription;
        this.skillsDeveloped = skillsDeveloped;

    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public static List<Course> toReadCsvTocourses(String pathName, List<SubCategory> subCategories) throws FileNotFoundException {
        List<Course> courses = new ArrayList<>();
        Scanner scanner = new Scanner(new File("/home/kelly/Downloads/planilha-dados-escola - Curso.csv"));

        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            String name = lineScanner.hasNext() ? lineScanner.next().strip() : null;
            String code = lineScanner.hasNext() ? lineScanner.next().strip() : null;
            String estimedTime = lineScanner.hasNext() ? lineScanner.next().strip() : null;
            String visibility = lineScanner.hasNext() ? lineScanner.next().strip() : null;
            String targetAudience = lineScanner.hasNext() ? lineScanner.next().strip() : null;
            String instructor = lineScanner.hasNext() ? lineScanner.next().strip() : null;
            String courseProgramDescription = lineScanner.hasNext() ? lineScanner.next().strip() : null;
            String skillsDeveloped = lineScanner.hasNext() ? lineScanner.next().strip() : null;
            String subCategory = lineScanner.hasNext() ? lineScanner.next().strip() : null;

            int time = Integer.parseInt(estimedTime);

            if (name != null && code != null && estimedTime != null &&subCategory != null) {
                courses.add(new Course(name, code, time, visibility.equals("PÚBLICA"), targetAudience, instructor, courseProgramDescription, skillsDeveloped, filterSubCategoriesByCode(subCategories, subCategory)));
            }
        }
        return courses;

    }

    @Override
    public String toString() {
        return "Course" + '\n' +
                "name='" + name + '\n' +
                "code='" + code + '\n' +
                "estimatedTime=" + estimatedTime + '\n' +
                "visibility=" + visibility +'\n' +
                "targetAudience='" + targetAudience + '\n' +
                "instructor='" + instructor + '\n' +
                "courseProgramDescription='" + courseProgramDescription + '\n' +
                "skillsDeveloped='" + skillsDeveloped + '\n' +
                "subCategory:" + subCategory + '\n';
    }
}
