<#import "/spring.ftl" as spring />
<#import "/tags/layout.ftl" as t/>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<@t.layout>
<#--    <c:if test="${message != null}">-->
<#--        <h1 class="message">${message}</h1>-->
<#--    </c:if>-->
<#--    <c:if test="${message == null}">-->
    <div class="book">
        <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="row">
            <div class="col-md-4 border-right">
                <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                    <#if book.fileStorageName?has_content>
                        <img class="d-flex mt-5 image-book" src="<@spring.url relativeUrl="/files/${book.fileStorageName}"/>"/>
                    </#if>
<#--                    <iframe class="d-flex mt-5 image-book" scrolling="no" src="<@spring.url "/downloadImage?id=${book.id}"/>"></iframe>-->
                    <div class="row">
                        <div class="btn mt-5 text-center" type="button">
                            <a href="<@spring.url "/books/${book.id}/reviews"/>">Рецензии</a>
                        </div>
<#--<%--                        <div class="btn mt-5 text-center col-6" type="button">--%>-->
<#--<%--                            <a href="<c:url value="/bookComments?id=${book.id}"/>">Комментарии</a>--%>-->
<#--<%--                        </div>--%>-->
                        <@security.authorize access="isAuthenticated()">
                        <div class="mt-5 text-center col-6">
                            <#if book.isFavourite>
                                <input id="button" class="btn" type="submit" value="Удалить">
                            <#else>
                                <input id="button" class="btn" type="submit" value="Добавить">
                            </#if>
<#--                            <div class="mt-5 text-center col-6">-->
<#--                                <input id="button" class="btn" type="submit" value="${status}">-->
<#--                            </div>-->
                        </div>
                        </@security.authorize>
                    </div>
                </div>
            </div>
            <div class="col-md-8 border-right">
                <div class="p-3 py-5">
                    <div class="row">
                        <h5 class="col-6">Автор: ${book.author.nickname}</h5>
                        <h5 id="date" class="col-4">${book.dateOfAdding}</h5>
                    </div>
                    <h4 class="name-book">${book.name}</h4>
                    <h2 class="book-describe">${book.description}</h2>
                    <div class="mt-2">
                        <#if book.genres?has_content>
                            <h5>Жанры: </h5>
                            <#list book.genres as genre>
                                <h6>${genre.name}</h6>
                            </#list>
                        </#if>
                    </div>
                    <@security.authorize access="isAuthenticated()">
                        <#if book.author.id == currentUserId>
                            <div class="mt-5 text-center">
                                <button class="btn" type="button">
                                    <a href="<@spring.url "/chapter?id=${book.id}"/>">Добавить главу</a>
                                </button>
                            </div>
                        </#if>
                    </@security.authorize>
                </div>
            </div>
        </div>
        <div class="chapters mt-5">
            <#if chapters?has_content!>
                <h1 class="message">Нет глав</h1>
            <#else>
                <div>
                    <#list book.chapters as chapter>
                        <h1 class="chapter">
                            <a href="<@spring.url "/chapter/${chapter.id}"/>">${chapter.name}</a>
                        </h1>
                    </#list>
                </div>
            </#if>
        </div>
    </div>
    <script>
        date = "${book.dateOfAdding}";
        document.getElementById("date").innerHTML = date.substring(0, 16);
        bookId = ${book.id};
        url = "<@spring.url "/profile/favourites"/>";
    </script>
    <script src="<@spring.url "/js/addDeleteFavourite.js"/>" charset="utf-8"/>
</@t.layout>
