package Logos.activity.validation;

public class VideoValidation {

    public static boolean isValidDuration(int duration, int durationMin) {

        if (duration < durationMin)
            throw new IllegalArgumentException("Minutos de duração do vídeo não podem ter valor menor que "+durationMin);
        return true;
    }
}
