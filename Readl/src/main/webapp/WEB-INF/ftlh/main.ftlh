<#import "/spring.ftl" as spring />
<#import "/tags/layout.ftl" as t/>
<#import "/tags/externalBook.ftl" as extBook/>

<@t.layout>
<#--    <c:if test="${message != null}">-->
<#--        <div>${message}</div>-->
<#--    </c:if>-->
    <#list books as bookP>
        <@extBook.externalBook book=bookP/>
    </#list>
</@t.layout>
