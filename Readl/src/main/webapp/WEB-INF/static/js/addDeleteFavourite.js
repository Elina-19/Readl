button = document.getElementById('buttonFav');
csrf = document.getElementById('csrf');
const add = "Добавить";
const del = "Удалить";

if (button !== null) {
    button.addEventListener('click', function (e) {
        e.preventDefault();

        var xhr = new XMLHttpRequest();

        if (button.value === add) {
            xhr.open('post', url + "?" + csrf.getAttribute('name') + "=" + csrf.getAttribute('value') +
                "&" + "bookId=" + bookId, false);
            xhr.send();
            button.value = del;
        } else {
            xhr.open('delete', url + "/" + bookId +
                "?" + csrf.getAttribute('name') + "=" + csrf.getAttribute('value'), false);
            xhr.send();
            button.value = add;
        }
    })
};
