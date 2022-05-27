<#import "/spring.ftl" as spring />
<#import "/tags/layout.ftl" as t/>

<@t.layout>
    <form method="post" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="col-md-8 add-chapter">
            <h4 class="new-book">Добавление главы</h4>
            <@spring.bind path="addChapterForm"/>
            <#list spring.status.errorMessages as error>
                <p class="error">${error}</p>
            </#list>
            <input type="hidden" name="book-id" value="${bookId}">
            <div>
                <label class="labels">Название главы</label>
                <@spring.formInput "addChapterForm.name" "class='form-control' placeholder='Название'"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="row mt-4">
                <@spring.formInput "addChapterForm.file" "class='form-control' placeholder='Загруить главу'" "file"/>
                <@spring.showErrors "<br>" "error"/>
                <div class="col-md-4">
                    <input class="btn" type="submit" value="Добавить главу">
                </div>
            </div>
        </div>
    </form>
</@t.layout>
