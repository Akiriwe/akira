<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
    <title>Recipe Data</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg span {
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

        .tg p {
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
<h1>Recipe Details</h1>

<div class="tg">
    <a href="${recipe._id}/download" download="E:\temp\${recipe._id}.doc">Download</a>
    <h>${recipe.recipeName}</h>
    <p><span>ID: </span>${recipe._id}</p>
    <p><span>Source link: </span>${recipe.sourceLink}</p>
    <p><span>Cook time: </span>${recipe.cookTime}</p>
    <p>${recipe.advancedCookTime}</p><br/><br/>
    <img src="${recipe.imageLink}"/>
    <table>
        <caption>Ingredients</caption>
        <tr>
            <th>Product</th>
            <th>Weight (g)</th>
            <th>Kcal</th>
            <th>Proteins (g)</th>
            <th>Fats (g)</th>
            <th>Carbohydrates (g)</th>
        </tr>
        <c:forEach items="${recipe.products}" var="product">
            <tr>
                <td>${product.productName}</td>
                <td>${product.weight}</td>
                <td>${product.kcal}</td>
                <td>${product.proteins}</td>
                <td>${product.fats}</td>
                <td>${product.carbohydrates}</td>
            </tr>
        </c:forEach>
        <tr>
            <td><b>Total:</b></td>
            <td>${recipe.totalWeight}</td>
            <td>${recipe.totalKcal}</td>
            <td>${recipe.totalProteins}</td>
            <td>${recipe.totalFats}</td>
            <td>${recipe.totalCarbohydrates}</td>
        </tr>
        <tr>
            <td><b>Per 1 portion:</b></td>
            <td>${recipe.weightPerPortion}</td>
            <td>${recipe.kcalPerPortion}</td>
            <td>${recipe.proteinsPerPortion}</td>
            <td>${recipe.fatsPerPortion}</td>
            <td>${recipe.carbohydratesPerPortion}</td>
        </tr>
        <tr>
            <td><b>Per 100 g:</b></td>
            <td></td>
            <td>${recipe.kcalPer100g}</td>
            <td>${recipe.proteinsPer100g}</td>
            <td>${recipe.fatsPer100g}</td>
            <td>${recipe.carbohydratesPer100g}</td>
        </tr>
        <tr>
            <td><b>Percentage:</b></td>
            <td></td>
            <td></td>
            <td>${recipe.proteinsPercent}%</td>
            <td>${recipe.fatsPercent}%</td>
            <td>${recipe.carbohydratesPercent}%</td>
        </tr>
    </table>

    <h2>Preparation:</h2>
    <p>${recipe.preparation}</p>
</div>
</body>
</html>