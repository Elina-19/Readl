<#import "/spring.ftl" as spring />

<#macro head>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
        <link href="<@spring.url relativeUrl="/css/style.css"/>" rel="stylesheet">
        <meta name="viewport" content="width=device-width" />
        <link rel="shortcut icon" href="<@spring.url relativeUrl="/images/open-book.png"/>" type="image/png"/>
        <title>Readl</title>
    </head>
</#macro>
