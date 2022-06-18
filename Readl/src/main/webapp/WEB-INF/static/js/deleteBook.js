deleteButton = document.getElementById('deleteBook');

csrf = document.getElementById('csrf');
csrf_name = csrf.getAttribute('name');
csrf_value = csrf.getAttribute('value');

$(document).ready(function() {
    deleteButton.addEventListener('click', function (e) {
        e.preventDefault();
        deleteBook();
    })
});

function deleteBook(){
    $.ajax({
        url: deleteUrl + '?' + csrf_name + '=' + csrf_value,
        method: 'delete',
        success: function (data){
            document.getElementById('deleteBook').value = 'Книга удалена';
        }
    });
}
