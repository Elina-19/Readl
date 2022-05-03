<#import "/spring.ftl" as spring />
<#import "/tags/layout.ftl" as t/>

<@t.layout>
    <form method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="sign-form">
            <@spring.bind path="signUpForm"/>
            <#list spring.status.errorMessages as error>
                <p class="error">${error}</p>
            </#list>
            <div class="mb-3">
                <label for="nickname" class="form-label">Nickname</label>
                <@spring.formInput "signUpForm.nickname" "class='form-control' placeholder='Nickname'"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <@spring.formInput "signUpForm.email" "class='form-control' placeholder='Email'" "email"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <@spring.formInput "signUpForm.password" "class='form-control' placeholder='Password'" "password"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="mb-3">
                <label for="repeatPassword" class="form-label">Repeat password</label>
                <@spring.formInput "signUpForm.repeatPassword" "class='form-control' placeholder='Repeat password'" "password"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <br>
            <input type="submit" class="btn" value="Создать">
        </div>
    </form>
</@t.layout>
