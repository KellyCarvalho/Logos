<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template style="/assets/css/listPage.css" title="Categorias">

        <section class="container block_container">
            <h3>Categorias</h3>
            <a href="/admin/categories/new">
                <button class="btn btn-primary">Nova Categoria</button>
            </a>
        </section>

        <section class="container block_container">
            <table class="table table-dark table-bordered">
                <thead>
                    <th scope="col">Nome</th>
                    <th scope="col">Código</th>
                    <th scope="col">Status</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </thead>
                <c:forEach items="${categories}" var="category">
                    <tbody>
                        <tr scope="row">
                            <td>${category.name}</td>
                            <td>${category.code}</td>
                            <td id="status_${category.code}">${category.status == 'ACTIVE' ? 'Ativa' : 'Inativa'}</td>
                            <td>
                                <a href="/admin/subcategories/${category.code}">SubCategorias</a>
                            </td>
                            <td>
                                <a class="action_button" href="/admin/categories/${category.code}">
                                    <button class="btn btn-dark">Editar</button>
                                </a>
                            </td>
                            <td>
                                <a class="action_button">
                                  <c:if test="${category.status == 'ACTIVE'}">
                                      <button onclick="disable('${category.code}')" id="disableButton_${category.code}" class="btn btn-dark">Desativar</button>
                                  </c:if>
                                </a>
                            </td>
                        </tr>
                    </tbody>
               </c:forEach>
            </table>
        </section>

        <script src="/assets/js/disableCategory.js"></script>

</templates:admin-template>
