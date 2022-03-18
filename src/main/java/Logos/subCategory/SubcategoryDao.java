package Logos.subCategory;

import Logos.category.Category;
import Logos.category.CategoryDTO;
import Logos.factory.ConnectionFactory;

import java.sql.*;

public class SubcategoryDao {

    public SubCategory getById(int id) {
        SubCategoryDTO subCategoryDTO = null;
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection connection = factory.recoverConnection()) {
            String sql = """
                    SELECT s.id id_subcategory, s.name subcategory_name, s.identifier_code subcategory_identifier_code,
                    s.fk_category fk_category, c.name category_name, c.identifier_code category_identifier_code
                    FROM Subcategory AS s
                    INNER JOIN Category AS c
                    ON s.fk_category = c.id
                    WHERE s.id=%s
                    """.formatted(id);
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                CategoryDTO   categoryDTO = new CategoryDTO(resultSet.getInt("fk_category"), resultSet.getString("category_name"), resultSet.getString("category_identifier_code"));
                subCategoryDTO = new SubCategoryDTO(resultSet.getInt("id_subcategory"), resultSet.getString("subcategory_name"), resultSet.getString("subcategory_identifier_code"), categoryDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toSubcategory(subCategoryDTO);
    }

    private SubCategory toSubcategory(SubCategoryDTO subCategoryDTO) {
        Category category = new Category(subCategoryDTO.getCategoryName(), subCategoryDTO.getCategoryCode());
        category.setId(subCategoryDTO.getCategoryId());
        SubCategory subCategory = new SubCategory(subCategoryDTO.getName(), subCategoryDTO.getCode(), category);
        subCategory.setId(subCategoryDTO.getId());
        return subCategory;
    }

}
