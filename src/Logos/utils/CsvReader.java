package Logos.utils;

import Logos.category.Category;
import Logos.category.enums.CategoryStatus;
import Logos.course.Course;
import Logos.subCategory.SubCategory;
import Logos.subCategory.enums.SubCategoryStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static Logos.commonValidator.StringValidator.isNotBlankEmptyOrNull;
import static Logos.subCategory.SubCategory.filterSubCategoriesByCode;

public class CsvReader {


    public static List<Category> readCsvCategories(String pathName) throws FileNotFoundException {
        isNotBlankEmptyOrNull(pathName, "Caminho de arquivo deve ser preenchidpo");
        List<Category> categories = new ArrayList<>();
        Scanner scanner = new Scanner(new File(pathName));
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            String name = lineScanner.hasNext() ? lineScanner.next().strip() : null;
            String code = lineScanner.hasNext() ? lineScanner.next().strip() : null;
            String order = lineScanner.next().strip();
            String description = lineScanner.next();
            CategoryStatus status = lineScanner.next().equals("ATIVA") ? CategoryStatus.ACTIVE : CategoryStatus.DISABLED;
            String icon = lineScanner.next();
            String color = lineScanner.next();
            int orderInt = order == "" ? 0 : Integer.parseInt(order);
            if ((name != "" && name != null) && (code != "" && code != null)) {
                categories.add(new Category(name, code, description, status, orderInt, icon, color));
            }
        }
        scanner.close();
        return categories;
    }

    public static List<Course> toReadCsvTocourses(String pathName, List<SubCategory> subCategories) throws FileNotFoundException {
        List<Course> courses = new ArrayList<>();

        Scanner scanner = new Scanner(new File(pathName));

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
            String subCategory = checkIfNext(lineScanner);

            int time = Integer.parseInt(estimatedTime);

            if ((name != "" && name != null) && (code != "" && code != null) && (estimatedTime != "" && estimatedTime != null) && (subCategory != "" && subCategory != null)) {
                courses.add(new Course(name, code, time, visibility.equals("PÚBLICA"), targetAudience, instructor, courseProgramDescription, skillsDeveloped, filterSubCategoriesByCode(subCategories, subCategory)));
            }
        }
        scanner.close();
        return courses;

    }

    public static List<SubCategory> toReadCsvToSubCategories(String pathName, List<Category> categories) throws FileNotFoundException {
        List<SubCategory> subCategories = new ArrayList<>();
        Scanner scanner = new Scanner(new File(pathName));
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
        scanner.close();
        return subCategories;
    }

    private static String checkIfNext(Scanner lineScanner) {
        return lineScanner.hasNext() ? lineScanner.next().strip() : "";
    }
}
