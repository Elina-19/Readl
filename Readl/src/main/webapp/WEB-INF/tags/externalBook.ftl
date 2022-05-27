<#import "/spring.ftl" as spring />

<#macro externalBook book>
    <div class="container-fluid wrapper">
        <div class="external-book">
            <#if book.fileStorageName?has_content>
                <img class="d-flex mt-5 image-book" src="<@spring.url relativeUrl="/files/${book.fileStorageName}"/>"/>
            </#if>
            <div class="book-content">
                <button class="btn" type="button">
                    <a href="<@spring.url relativeUrl="/books/${book.id}"/>">${book.name}</a>
                </button>
                <h4 class="book-describe">${book.description}</h4>
            </div>
        </div>
    </div>
</#macro>
