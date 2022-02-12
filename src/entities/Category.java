package entities;

import static validation.CategoryValidation.isValidColor;

public class Category {
  private int id;
  private String name;
  private String code;
  private String description;
  private String studyGuide;
  private boolean active;
  private int order;
  private String codeColor;

  public Category(int id, String name, String code, String description, String studyGuide, boolean active, int order, String codeColor) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.description = description;
    this.studyGuide = studyGuide;
    this.active = active;
    this.order = order;
    this.codeColor = codeColor;
  }

  public Category(String name, String code, String description, String studyGuide, boolean active, int order, String codeColor) {
    isValidColor(codeColor);
    this.name = name;
    this.code = code;
    this.description = description;
    this.studyGuide = studyGuide;
    this.active = active;
    this.order = order;
    this.codeColor = codeColor;
  }

  public Category(String name, String code) {
    this.name = name;
    this.code = code;
  }
}
