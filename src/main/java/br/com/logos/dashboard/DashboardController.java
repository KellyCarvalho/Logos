package br.com.logos.dashboard;

import br.com.logos.course.CourseByCategoryProjection;
import br.com.logos.course.CourseRepository;
import br.com.logos.course.CoursesQuantityByInstructorNameProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class DashboardController {

    private final CourseRepository courseRepository;

    @GetMapping(value = "/admin/dashboard")
    public String getCoursesByCategory(Model model) {
        List<CourseByCategoryProjection> allCoursesFromCategory = courseRepository.getAllCoursesCountByCategory();
        Optional<CoursesQuantityByInstructorNameProjection> quantityCoursesFromInstructorNameWithMoreCourses = courseRepository.getInstructorWithMoreCourses();
        model.addAttribute("coursesByCategory", allCoursesFromCategory);
        if (quantityCoursesFromInstructorNameWithMoreCourses.isPresent()){
        model.addAttribute("coursesByInstructor", quantityCoursesFromInstructorNameWithMoreCourses.get());
        }
        return "dashboard/dashboard";
    }

    @GetMapping(value = "/admin")
    public String getDashboard() {
        return "redirect:/admin/dashboard";
    }
}
