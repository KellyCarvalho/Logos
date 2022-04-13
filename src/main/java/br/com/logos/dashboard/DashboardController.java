package br.com.logos.dashboard;

import br.com.logos.course.CourseByCategoryProjection;
import br.com.logos.course.CourseRepository;
import br.com.logos.course.CoursesQuantityByInstructorNameProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = "/admin/dashboard")
    public String getCoursesByCategory(Model model) {
        List<CourseByCategoryProjection> allCoursesFromCategory = courseRepository.findAllCoursesCountByCategory();
        List<CoursesQuantityByInstructorNameProjection> allCoursesFromInstructorName = courseRepository.findAllInstructorCountCourses();
        model.addAttribute("coursesByCategory", allCoursesFromCategory);
        model.addAttribute("coursesByInstructor", allCoursesFromInstructorName);
        return "dashboard/dashboard";
    }

    @GetMapping(value = "/admin/")
    public String getDashboard() {
        return "redirect:/admin/dashboard";
    }
}
