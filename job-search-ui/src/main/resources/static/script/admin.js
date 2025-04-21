// Переключение между вкладками
    document.getElementById('candidates-tab').addEventListener('click', function() {
        document.getElementById('candidates-section').style.display = 'block';
        document.getElementById('employers-section').style.display = 'none';
        this.classList.add('active');
        document.getElementById('employers-tab').classList.remove('active');
    });

    document.getElementById('employers-tab').addEventListener('click', function() {
        document.getElementById('candidates-section').style.display = 'none';
        document.getElementById('employers-section').style.display = 'block';
        this.classList.add('active');
        document.getElementById('candidates-tab').classList.remove('active');
    });

    // Функции для блокировки/разблокировки пользователей
    function blockUser(userId) {
        fetch('/admin/block/' + userId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
        })
        .then(response => response.text())
        .then(data => {
            alert(data);
            location.reload();
        })
        .catch(error => console.error('Error:', error));
    }

    function unblockUser(userId) {
        fetch('/admin/unblock/' + userId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
        })
        .then(response => response.text())
        .then(data => {
            alert(data);
            location.reload();
        })
        .catch(error => console.error('Error:', error));
    }