package avalon.category;

import static avalon.category.validation.CategoryValidation.fieldsContainsValue;
import static avalon.category.validation.CategoryValidation.isValidColor;

public class Category {
  private int id;
  private String name;
  private String code;
  private String description;
  private String studyGuide;
  private boolean active;
  private int order;
  private String iconUrl;
  private String codeColor;

  public Category(String name, String code, String description, String studyGuide, boolean active, int order, String iconUrl, String codeColor) {
    fieldsContainsValue(name,code);
    isValidColor(codeColor);
    this.name = name;
    this.code = code;
    this.description = description;
    this.studyGuide = studyGuide;
    this.active = active;
    this.order = order;
    this.iconUrl = iconUrl;
    this.codeColor = codeColor;
  }

  public Category(String name, String code) {
    this.name = name;
    this.code = code;
  }

  public Category(String name, String code, String description, String studyGuide, boolean active, int order) {
    this.name = name;
    this.code = code;
    this.description = description;
    this.studyGuide = studyGuide;
    this.active = active;
    this.order = order;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public String getStudyGuide() {
    return studyGuide;
  }

  public boolean isActive() {
    return active;
  }

  public int getOrder() {
    return order;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  public String getCodeColor() {
    return codeColor;
  }
}
