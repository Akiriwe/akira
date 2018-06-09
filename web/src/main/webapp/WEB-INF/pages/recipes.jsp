<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" %>
<html>
<head>
    <title>Recipes Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<a href="../../index.jsp">Back to main menu</a>

<br/>
<br/>

<h1>Recipes List</h1>

<c:if test="${!empty listRecipes}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Recipe name</th>
            <th width="60">Total weight</th>
            <th width="60">Total kcal</th>
            <th width="60">Total fats</th>
            <th width="60">Total proteins</th>
            <th width="60">Total carbohydrates</th>
            <th width="60">Cook time</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listRecipes}" var="recipeDto">
            <tr>
                <td>${recipeDto._id}</td>
                <td><a href="/recipedata/${recipeDto._id}" target="_blank">${recipeDto.recipeName}</a></td>
                <td>${recipeDto.totalWeight}</td>
                <td>${recipeDto.totalKcal}</td>
                <td>${recipeDto.totalFats}</td>
                <td>${recipeDto.totalProteins}</td>
                <td>${recipeDto.totalCarbohydrates}</td>
                <td>${recipeDto.cookTime}</td>
                <td><a href="<c:url value='/edit/${recipeDto._id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${recipeDto._id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1>Add a Recipe</h1>

<c:url var="addAction" value="/recipes/add"/>

<form:form action="${addAction}" modelAttribute="recipeDto">
    <table>
        <c:if test="${!empty recipe.recipeName}">
            <tr>
                <td>
                    <form:label path="_id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="_id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="_id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="recipeName">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="recipeName"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="totalWeight">
                    <spring:message text="Total weight"/>
                </form:label>
            </td>
            <td>
                <form:input path="totalWeight"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="totalKcal">
                    <spring:message text="Total kcal"/>
                </form:label>
            </td>
            <td>
                <form:input path="totalKcal"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="totalFats">
                    <spring:message text="Total fats"/>
                </form:label>
            </td>
            <td>
                <form:input path="totalFats"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="totalProteins">
                    <spring:message text="Total proteins"/>
                </form:label>
            </td>
            <td>
                <form:input path="totalProteins"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="totalCarbohydrates">
                    <spring:message text="Total carbohydrates"/>
                </form:label>
            </td>
            <td>
                <form:input path="totalCarbohydrates"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="cookTime">
                    <spring:message text="Cook time"/>
                </form:label>
            </td>
            <td>
                <form:input path="cookTime"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty recipe.recipeName}">
                    <input type="submit"
                           value="<spring:message text="Edit recipe"/>"/>
                </c:if>
                <c:if test="${empty recipe.recipeName}">
                    <input type="submit"
                           value="<spring:message text="Add recipe"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
