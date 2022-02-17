package avalon.activity.validation;

public class VideoValidation extends RuntimeException {

    public static boolean isValidDuration(int duration, int durationMin) {

        if (duration < durationMin)
            throw new IllegalArgumentException("Minutos de duração do vídeo não podem ter valor menor que "+durationMin);
        return true;
    }
}
