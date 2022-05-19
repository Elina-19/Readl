<#import "/spring.ftl" as spring />
<#import "/tags/layout.ftl" as t/>
<#import "/tags/externalBook.ftl" as extBook/>

<@t.layout>
    <#if !books?has_content>
        <h1>Нет книг</h1>
    </#if>
    <#list books as book>
        <@extBook.externalBook book=book/>
    </#list>
</@t.layout>
