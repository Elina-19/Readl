<#import "/spring.ftl" as spring />
<#import "/tags/layout.ftl" as t/>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<@t.layout>
    <div class="col-md-8 add-chapter">
        <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <h4 class="new-book">Рецензии</h4>
        <@security.authorize access="isAuthenticated()">
            <h1 id="message"></h1>
            <div>
                <label for="description" class="form-label">Добавьте рецензию</label>
                <textarea class="form-control" id="description" placeholder="Введите текст" rows="3"></textarea>
            </div>
            <div class="mt-3">
                <input id="button" class="btn" type="submit" value="Отправить">
            </div>
        </@security.authorize>
    </div>
    <div id="empty"></div>
    <div id="reviews"></div>
    <script>
        // csrf token
        bookId=${bookId};
        urlGet="<@spring.url "/reviews"/>";
        urlSend="<@spring.url "/reviews"/>";
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="<@spring.url "/js/review.js"/>"></script>
</@t.layout>
