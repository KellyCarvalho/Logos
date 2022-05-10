<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login | Cursos online de tecnologia</title>
        <link rel="stylesheet" href="/assets/css/loginPage.css">
    </head>
    <body>
        <main class="container">
            <section class="auth">
                <section class="login">
                    <h1 class="login__title">Já estuda com a gente?</h1>
                    <p class="login__subtitle">Faça seu login e boas aulas!</p>
                    <form class="login__form" action="/login" method="POST">
                        <label for="login-email" >E-mail</label>
                        <input type="email" name="username" id="login-email" autofocus="true"/>

                        <label for="login-password">Senha</label>
                        <input type="password" name="password" id="login-password" autocomplete="off"/>

                        <button class="login__button" type="submit">Entrar</button>
                    </form>
                </section>
                <section class="signup">
                    <h2 class="signup__title">Ainda não estuda com a gente?</h2>
                    <p class="signup__text">São mais de mil cursos nas seguintes áreas</p>
                    <ul class="categories">
                        <c:forEach items="${categories}" var="category">
                            <li class="category-card">
                                <a href="category/${category.code}" class="category-card__link" href="/programacao">
                                    <span class="category-card__icon">
                                        <img src="${category.imageUrl}">
                                    </span>
                                    <h3 class="category-card__title"> ${category.name}</h3>
                                    <c:forEach begin="0" varStatus="number" items="${category.activeSubCategoriesWithVisibleCoursesSortedBySubCategoryOrder}" end="2" var="subcategory">
                                        <p class="category-card__details">
                                            ${subcategory.name}<c:if test="${number.index < category.activeSubCategoriesWithVisibleCoursesSortedBySubCategoryOrder.size() -1}">,</c:if>
                                        </p>
                                    </c:forEach>
                                    <c:if test="${category.activeSubCategoriesWithVisibleCoursesSortedBySubCategoryOrder.size() > 3}">e mais...</c:if>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </section>
            </section>
        </main>
    </body>
</html>