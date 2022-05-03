<#import "/spring.ftl" as spring />
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<#macro header>
    <header>
        <nav class="navbar fixed-top navbar-expand-lg navbar-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="<@spring.url relativeUrl="/main"/>">Readl</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="actions collapse navbar-collapse" id="navbarSupportedContent">
                    <div class="search">
                        <form class="d-flex" action="<@spring.url relativeUrl="/books/search"/>" method="get">
                            <input class="form-control mr-2" type="search" placeholder="Поиск" name="query">
                            <button class="btn" type="submit">Поиск</button>
                        </form>
                    </div>
                    <@security.authorize access="!isAuthenticated()">
                        <div class="d-flex">
                            <button class="btn" type="button">
                                <a href="<@spring.url relativeUrl="/signIn"/>" class="logout-button" id="signIn">Войти</a>
                            </button>
                            <button class="btn" type="button">
                                <a href="<@spring.url relativeUrl="/signUp"/>" class="logout-button" id="signUp">Регистрация</a>
                            </button>
                            <a>
                                <img class="userIcon" src="<@spring.url relativeUrl="/WEB-INF/static/images/user.png"/>"/>
                            </a>
                        </div>
                    </@security.authorize>
                    <@security.authorize access="isAuthenticated()">
                        <div>
                            <button class="btn" type="button">
                                <a href="<@spring.url relativeUrl="/profile"/>" class="logout-button">Профиль</a>
                            </button>
                        </div>
                    </@security.authorize>
                </div>
            </div>
        </nav>
    </header>
</#macro>
