button = document.getElementById('button');

csrf = document.getElementById('csrf');
csrf_name = csrf.getAttribute('name');
csrf_value = csrf.getAttribute('value');

$(document).ready(function() {
    getReviews();

    if (button !== null){
        button.addEventListener('click', function (e) {
            e.preventDefault();

            var description = document.getElementById('description').value;

            saveReview(description);
        })
    }
});

function saveReview(description){
    $.ajax({
        url: urlSend + '?' + csrf_name + '=' + csrf_value,
        method: 'post',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify({
            bookId: bookId,
            content: description,
        }),
        success: function (data) {
            document.getElementById('description').value = '';
        },
        error: function (data) {
            document.getElementById('message').value = data.responseJSON.errors[0].message;
        }
    })
}

date = '0001-01-01T00:00:00';
function getReviews(){
    $.ajax({
        url: urlGet + '?' + csrf_name + '=' + csrf_value + '&id=' + bookId + "&date=" + date,
        method: 'get',
        dataType: 'json',
        success: function (data){
            var reviews = document.getElementById('reviews');
            for(var i = 0; i < data.length; i++) {
                reviews.appendChild(createReview(data[i]));

                if (Date.parse(date) < Date.parse(data[i].date)){
                    date = data[i].date;
                }
            }
        }
    });

    setTimeout(function() {
        getReviews();
    }, 2000);
}

createReview = function (element){
    var review = document.createElement('div');
    review.className = 'review';

    var top = document.createElement('div');
    top.className = 'row border-bottom';

    var author = document.createElement('div');
    author.className = 'col-9';
    authorText = document.createTextNode(element.author.nickname);
    author.appendChild(authorText);

    var date = document.createElement('div');
    date.className = 'col-3';
    dateText = document.createTextNode(getDate(element.date));
    date.appendChild(dateText);

    var content = document.createElement('div');
    contentText = document.createTextNode(element.content);
    content.appendChild(contentText);

    top.appendChild(author);
    top.appendChild(date);
    review.appendChild(top);
    review.appendChild(content);
    return review;
}

function getDate(timestamp){
    return timestamp.replace('T', ' ').substring(0, 16);
}
