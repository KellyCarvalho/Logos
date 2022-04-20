<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Programação | Cursos online de tecnologia</title>
        <link href="/assets/css/publicPageStyle.css" rel="stylesheet">
    </head>
    <body>
        <section class="category-banner__wrapper">
            <div class="container category-banner">
                <span class="category-banner__icon">
                   <img src="${category.imageUrl}">
                </span>
                <h1 class="category-banner__title">${category.name}</h1>
            </div>
        </section>
        <main class="container">
            <div class="subcategories">
                <p class="subcategories__label">O que você quer aprender?</p>
                <ul class="subcategories__list">
                    <c:forEach items="${subCategories}" var="subCategory">
                        <li class="subcategories__item">
                            <a href="#${subCategory.code}" class="subcategories__link">
                                <span class="subcategories__name">${subCategory.name}</span>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <c:forEach items="${subCategories}" var="subcategory">
                <div class="subcategory">
                    <h2 id="${subcategory.code}" class="subcategory__name">${subcategory.name}</h2>
                    <ul class="courses__list">
                        <c:forEach items="${subcategory.activeSubCategoriesWithPublicCourse}" var="course">
                            <li class="course-card">
                                <h3 class="course-card__name">${course.name}</h3>
                                <p class="course-card__hours">${course.estimatedTime}h</p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:forEach>
        </main>
    </body>
</html>