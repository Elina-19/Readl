<#import "/spring.ftl" as spring />
<#import "/tags/layout.ftl" as t/>

<@t.layout>
    <form method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="sign-form">
<#--            <c:if test="${error != null}">${error}<br></c:if>-->
            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input id="email" class="form-control" type="email" name="email" placeholder="Email">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <input id="password" class="form-control" type="password" name="password" placeholder="Password">
            </div>
            <div>
                <a href="<@spring.url relativeUrl="/auth/vk"/>">Войти через vk</a>
            </div>
            <br>
            <input class="btn" type="submit" value="Войти">
        </div>
    </form>
</@t.layout>
