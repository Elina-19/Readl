<#import "/spring.ftl" as spring />
<#import "/tags/layout.ftl" as t/>

<@t.layout>
    <form method="post" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="container form-add-book">
            <div class="row">
                <div class="col-md-4 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span> </span></div>
                    <div class="mt-5 text-center">
                        <input type="file" class="btn" name="file" placeholder="Загрузить обложку">
                    </div>
                </div>
                <div class="col-md-8 border-right">
                    <div class="p-3 py-5">
                        <h4 class="new-book">Добавление книги</h4>
                        <div>
                            <label class="labels">Название книги</label>
                            <@spring.formInput "addBookForm.name" "class='form-control' placeholder='Название'"/>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                        <div>
                            <label for="description" class="form-label">Описание книги</label>
                            <@spring.formTextarea "addBookForm.description" "class='form-control' placeholder='Введите описание'"/>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                        <br>
                        <div class="col-md-12">
                            <label class="labels">Жанры</label>
                            <ul class="list-group">
                                <li>
                                    <#list genres as genre>
                                        <input type="checkbox" name="genres" value="${genre.id}">
                                        ${genre.name}
                                    </#list>
                                </li>
                            </ul>
                        </div>
                        <div class="mt-5 text-center">
                            <input class="btn" type="submit" value="Добавить">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</@t.layout>
