package Logos.utils;

import Logos.category.Category;
import Logos.category.enums.CategoryStatus;
import Logos.commonValidator.StringValidator;
import Logos.course.Course;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;

public class CsvReader {

    public static List<Category> readCsvCategories(String pathName) {

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
                if ((name != "" && name != null) && (code != "" && code != null)) {
                    categories.add(new Category(name, code, description, status, orderInt, icon, color));
                }
            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro no caminho do arquivo de cursos, certifique-se de que o arquivo existe ou se de fato foi preenchido");
            System.out.println(e.getMessage());
        } finally {
            return categories;
        }

    }

    public static List<Course> readCsvCourses(String pathName, List<SubCategory> subCategories){
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

                if ((name != "" && name != null) && (code != "" && code != null) && (estimatedTime != "" && estimatedTime != null) && subCategory.isPresent()) {
                    courses.add(new Course(name, code, time, visibility.equals("PÚBLICA"), targetAudience, instructor, courseProgramDescription, skillsDeveloped, subCategory.get()));
                }
            }


        } catch (Exception e) {
            System.out.println("Ocorreu um erro no caminho do arquivo de cursos, certifique-se de que o arquivo existe ou se de fato foi preenchido");
        } finally {
            return courses;
        }
    }

    public static List<SubCategory> readCsvSubCategories(String pathName, List<Category> categories) {
        List<SubCategory> subCategories = new ArrayList<>();
       try( Scanner scanner = new Scanner(new File(pathName));) {
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

               if ((name != "" && name != null) && (code != "" && code != null) && (categoryCode != "" && categoryCode != null) && category.isPresent()) {
                   subCategories.add(new SubCategory(name, code, description, status, orderInt, category.get()));
               }
           }

       }catch (Exception e){
           System.out.println("Ocorreu um erro no caminho do arquivo de cursos, certifique-se de que o arquivo existe ou se de fato foi preenchido");

       }finally {
           return subCategories;
       }

    }

    private static String checkIfNext(Scanner lineScanner) {
        return lineScanner.hasNext() ? lineScanner.next().strip() : "";
    }
}
