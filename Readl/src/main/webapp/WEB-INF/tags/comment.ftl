<#macro comment comment>
    <div class="comment">
        <div class="comment-header">
        </div>
        <div class="comment-content">
            ${comment}
        </div>
        <div class="comment-footer">
            <div class="rate">
                <div class="response-btn underline-on-hover"
                     data-id="${comment.length()}">
                    Ответить
                </div>
            </div>
        </div>
    </div>
</#macro>
