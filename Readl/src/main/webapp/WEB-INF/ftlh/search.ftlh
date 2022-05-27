<#import "/spring.ftl" as spring />
<#import "/tags/layout.ftl" as t/>
<#import "/tags/externalBook.ftl" as extBook/>

<@t.layout>
    <div class="search_filter">
        <h1>Найти книги</h1>
        <form method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <ul class="genres">
                Выберите жанры
                <br>
                <#list genres as genre>
                    <input name="genres" class="form-check-input me-1" type="checkbox" value="${genre.id}">
                    ${genre.name}
                    <br>
                </#list>
                <br>
                <input name="name" placeholder="Введите название">
                <input class="btn" type="submit" value="Найти">
            </ul>
        </form>
    </div>
</@t.layout>
