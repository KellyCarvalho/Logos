package entities;



public abstract class Activity {
    private Long id;
    private String title;
    private String code;
    private boolean active;
    private int order;
    private Section section;

    @Deprecated
    public Activity() {

    }

    public Activity(Long id, String title, String code, boolean active, int order, Section section) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.active = active;
        this.order = order;
        this.section = section;
    }


    public Activity(Long id, String title, String code) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.active = active;
        this.order = order;
        this.section = section;
    }

    public Activity(String title, String code) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.active = active;
        this.order = order;
        this.section = section;
    }
}
