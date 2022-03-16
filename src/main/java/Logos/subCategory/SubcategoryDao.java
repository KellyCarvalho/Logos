package Logos.subCategory;

import Logos.category.CategoryDTO;
import Logos.utils.ConnectionFactory;

import java.sql.*;

public class SubcategoryDao {
    public SubCategoryDTO getSubCategoryById(int id) {
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
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
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    int subcategoryId = resultSet.getInt("id_subcategory");
                    String subcategoryName = resultSet.getString("subcategory_name");
                    String subcategoryCode = resultSet.getString("subcategory_identifier_code");
                    int categoryId = resultSet.getInt("fk_category");
                    String categoryName = resultSet.getString("category_name");
                    String categoryCode = resultSet.getString("category_identifier_code");
                    CategoryDTO category = new CategoryDTO(categoryId, categoryName, categoryCode);
                    subCategoryDTO = new SubCategoryDTO(subcategoryId, subcategoryName, subcategoryCode, category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subCategoryDTO;
    }
}
