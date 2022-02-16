package avalon.activity.validation;

public class VideoValidation extends RuntimeException {

    public static boolean isValidDuration(int duration) {

        if (duration < 1)
            throw new IllegalArgumentException("Minutos de duração do vídeo não podem ter valor menor que 1");
        return true;
    }
}
