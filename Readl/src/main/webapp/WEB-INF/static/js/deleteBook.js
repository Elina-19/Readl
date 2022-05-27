button = document.getElementById('deleteBook');

csrf = document.getElementById('csrf');
csrf_name = csrf.getAttribute('name');
csrf_value = csrf.getAttribute('value');

$(document).ready(function() {
    button.addEventListener('click', function (e) {
        e.preventDefault();
        deleteBook();
    })
});

function deleteBook(){
    $.ajax({
        url: deleteUtl + '?' + csrf_name + '=' + csrf_value,
        method: 'delete',
        success: function (data){
            document.getElementById('deleteBook').value = 'Книга удалена';
        }
    });
}
