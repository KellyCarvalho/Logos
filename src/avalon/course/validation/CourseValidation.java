package avalon.course.validation;

public class CourseValidation extends RuntimeException {

    public static boolean isValidEstimatedTime(int estimatedTime, int min, int max) {

            if (estimatedTime < min && estimatedTime > max) throw new IllegalArgumentException("Tempo estimado de curso não pode ser menor que"+ min +" ou maior que+"+max);

            return true;
 }}

