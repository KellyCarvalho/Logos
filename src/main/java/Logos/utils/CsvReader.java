package Logos.utils;

import Logos.category.Category;
import Logos.category.enums.CategoryStatus;
import Logos.course.Course;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CsvReader {

    public static List<Category> readCategories(String pathName) {

        List<Category> categories = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(pathName))) {
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
                String name = checkIfNext(lineScanner);
                String code = checkIfNext(lineScanner);
                String order = checkIfNext(lineScanner);
                String description = checkIfNext(lineScanner);
                CategoryStatus status = lineScanner.next().equals("ATIVA") ? CategoryStatus.ACTIVE : CategoryStatus.DISABLED;
                String icon = checkIfNext(lineScanner);
                String color = checkIfNext(lineScanner);
                int orderInt = order == "" ? 0 : Integer.parseInt(order);
                isValidCategory(categories, name, code, description, status, icon, color, orderInt);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro no caminho do arquivo de cursos, certifique-se de que o arquivo existe ou se de fato foi preenchido");
            System.out.println(e.getMessage());
        } finally {
            return categories;
        }
    }

    private static void isValidCategory(List<Category> categories, String name, String code, String description, CategoryStatus status,
                                        String icon, String color, int orderInt) {
        if ((name != "" && name != null) && (code != "" && code != null)) {
            categories.add(new Category(name, code, description, status, orderInt, icon, color));
        }
    }

    public static List<Course> readCourses(String pathName, List<SubCategory> subCategories) {
        List<Course> courses = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(pathName))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
                String name = checkIfNext(lineScanner);
                String code = checkIfNext(lineScanner);
                String estimatedTime = checkIfNext(lineScanner);
                String visibility = checkIfNext(lineScanner);
                String targetAudience = checkIfNext(lineScanner);
                String instructor = checkIfNext(lineScanner);
                String courseProgramDescription = checkIfNext(lineScanner);
                String skillsDeveloped = checkIfNext(lineScanner);
                String subCategoryCode = checkIfNext(lineScanner);
                int time = Integer.parseInt(estimatedTime);

                Optional<SubCategory> subCategory = subCategories.stream().filter(cat -> cat.getCode().equals(subCategoryCode)).findFirst();

                isValidCourse(courses, name, code, estimatedTime, visibility, targetAudience, instructor, courseProgramDescription,
                        skillsDeveloped, time, subCategory);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro no caminho do arquivo de cursos, certifique-se de que o arquivo existe ou se de fato foi preenchido");
        } finally {
            return courses;
        }
    }

    private static void isValidCourse(List<Course> courses, String name, String code, String estimatedTime, String visibility,
                                      String targetAudience, String instructor, String courseProgramDescription, String skillsDeveloped,
                                      int time, Optional<SubCategory> subCategory) {
        if ((name != "" && name != null) && (code != "" && code != null) && (estimatedTime != "" && estimatedTime != null) && subCategory.isPresent()) {
            courses.add(new Course(name, code, time, visibility.equals("PÚBLICA"), targetAudience, instructor, courseProgramDescription,
                    skillsDeveloped, subCategory.get()));
        }
    }

    public static List<SubCategory> readSubCategories(String pathName, List<Category> categories) {
        List<SubCategory> subCategories = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(pathName));) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
                String name = checkIfNext(lineScanner);
                String code = checkIfNext(lineScanner);
                String order = checkIfNext(lineScanner);
                String description = checkIfNext(lineScanner);
                SubCategoryStatus status = lineScanner.next().equals("ATIVA") ? SubCategoryStatus.ACTIVE : SubCategoryStatus.DISABLED;
                String categoryCode = checkIfNext(lineScanner);
                int orderInt = order == "" ? 0 : Integer.parseInt(order);

                Optional<Category> category = categories.stream().filter(cat -> cat.getCode().equals(categoryCode)).findFirst();

                isValidSubCategory(subCategories, name, code, description, status, categoryCode, orderInt, category);
            }

        } catch (IOException e) {
            System.out.println("Ocorreu um erro no caminho do arquivo de cursos, certifique-se de que o arquivo existe ou se de fato foi preenchido");

        } finally {
            return subCategories;
        }
    }

    private static void isValidSubCategory(List<SubCategory> subCategories, String name, String code, String description,
                                           SubCategoryStatus status, String categoryCode, int orderInt, Optional<Category> category) {
        if ((name != "" && name != null) && (code != "" && code != null) && (categoryCode != "" && categoryCode != null) && category.isPresent()) {
            subCategories.add(new SubCategory(name, code, description, status, orderInt, category.get()));
        }
    }

    private static String checkIfNext(Scanner lineScanner) {
        return lineScanner.hasNext() ? lineScanner.next().strip() : "";
    }
}
