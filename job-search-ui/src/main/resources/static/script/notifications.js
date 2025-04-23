document.addEventListener("DOMContentLoaded", function () {
    const applications = document.querySelectorAll(".application-block");

    // 🔹 Логирование загружаемых заявок
    console.log("📌 Найдено заявок:", applications.length);
    applications.forEach(app => {
        const appId = app.getAttribute("data-app-id");
        const resumeId = app.getAttribute("data-resume-id");
        const statusElement = app.querySelector("p[style]");
        const status = statusElement ? statusElement.textContent.trim() : "Статус не найден";

        console.log(`🔹 Заявка ID: ${appId}, Резюме ID: ${resumeId}, Статус: ${status}`);
    });

    // 🔹 Автоматически выбираем первый отклик
    if (applications.length > 0) {
        const firstApp = applications[0];
        const resumeId = firstApp.getAttribute("data-resume-id");

        // Показываем резюме для первого отклика
        const matchingResume = document.querySelector(`.resume-card[data-resume-id="${resumeId}"]`);
        if (matchingResume) {
            matchingResume.style.display = "block";
        }
    }

    // 🔹 Переключение резюме при клике
    applications.forEach(app => {
        app.addEventListener("click", function () {
            const resumeId = this.getAttribute("data-resume-id");

            // Скрываем все резюме
            document.querySelectorAll(".resume-card").forEach(card => {
                card.style.display = "none";
            });

            // Показываем нужное резюме
            const matchingResume = document.querySelector(`.resume-card[data-resume-id="${resumeId}"]`);
            if (matchingResume) {
                matchingResume.style.display = "block";
            }
        });
    });

    // 🔹 Изменение статуса отклика
    document.querySelectorAll(".invite-btn, .reject-btn").forEach(button => {
        button.addEventListener("click", function () {
            const appId = this.getAttribute("data-app-id");
            const newStatus = this.classList.contains("invite-btn") ? "INVITED" : "REJECTED";

            console.log(`📝 Меняем статус заявки ID: ${appId} на ${newStatus}`);

            fetch(`/admin/update-status/${appId}/${newStatus}`, { method: "POST" })
                .then(() => location.reload()) // Перезагрузка страницы после изменения
                .catch(err => console.error("❌ Ошибка обновления статуса:", err));
        });
    });
});
