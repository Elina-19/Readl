<#import "/spring.ftl" as spring />
<#import "/tags/layout.ftl" as t/>

<@t.layout>
        <div class="chapter-content">
            ${chapter.content}
        </div>
        <div class="row chapter-buttons">
            <button class="btn mt-5">
                <a href="<@spring.url "/books/${chapter.book.id}"/>">Книга</a>
            </button>
        </div>
</@t.layout>
