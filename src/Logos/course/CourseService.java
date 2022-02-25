package Logos.course;

import Logos.subCategory.enums.SubCategoryStatus;

import java.util.List;
import java.util.stream.Collectors;

public class CourseService {
    public static long getTotalCourseHours(List<Course> courses) {
        return courses.stream().map(course -> course.getEstimatedTime()).reduce(0, (subtotal, element) -> subtotal + element);
    }

    public static String getCoursesNames(List<Course> courses) {
        return courses.stream().map(course -> course.getName()).collect(Collectors.joining(", "));
    }

    public static String getSubCategoryName(List<Course> courses) {
        return courses.stream().filter(course -> course.isActiveSubCategory().equals(SubCategoryStatus.ACTIVE))
                .map(activeCourse -> activeCourse.getNameSubCategory() + "</br> Descrição: " +
                        isPresentText(activeCourse.getDescriptionSubCategory()) + "</br></br>").collect(Collectors.joining(" "));
    }

    private static String isPresentText(String text) {
        return text.isEmpty() || text.isBlank() || text.equals(null) ? "Campo Vazio" : text;
    }
}
