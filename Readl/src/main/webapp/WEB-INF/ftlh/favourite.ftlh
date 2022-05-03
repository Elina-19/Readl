<#import "/spring.ftl" as spring />
<#import "/tags/layout.ftl" as t/>
<#import "/tags/externalBook.ftl" as extBook/>

<@t.layout>
<#--    <c:if test="${message != null}">-->
<#--        <div class="message">-->
<#--            ${message}-->
<#--        </div>-->
<#--    </c:if>-->
    <#list books as book>
        <@extBook.externalBook book=book/>
    </#list>
</@t.layout>
