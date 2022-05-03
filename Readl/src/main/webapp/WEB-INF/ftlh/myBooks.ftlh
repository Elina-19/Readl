<#import "/spring.ftl" as spring />
<#import "/tags/layout.ftl" as t/>
<#import "/tags/externalBook.ftl" as extBook/>

<@t.layout>
    <#list books as book>
        <@extBook.externalBook book=book/>
    </#list>
</@t.layout>
