package br.com.logos.category;

import br.com.logos.course.Course;

import java.util.ArrayList;
import java.util.List;

public class CategoryDashboardDTO {
    private String categoryName;
    private int quantityCourses;


    @Deprecated
    public CategoryDashboardDTO() {
    }

    public CategoryDashboardDTO(String categoryName, int quantityCourses) {
        this.categoryName = categoryName;
        this.quantityCourses = quantityCourses;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQuantityCourses() {
        return quantityCourses;
    }

    public void setQuantityCourses(int quantityCourses) {
        this.quantityCourses = quantityCourses;
    }

    public static void toView(List<Course> courses, List<CategoryDashboardDTO> categoryDashboardDTOList) {
            categoryDashboardDTOList.add(new CategoryDashboardDTO(courses.stream().findFirst().get().getCategoryName(), courses.size()));
    }


    @Override
    public String toString() {
        return "CategoryDashboardDTO{" +
                "categoryName='" + categoryName + '\'' +
                ", quantityCourses=" + quantityCourses +
                '}';
    }
}

