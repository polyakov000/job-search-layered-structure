document.addEventListener("DOMContentLoaded", function () {
    fetch("/analyst/statistics/users-and-vacancies/result")
        .then(response => response.json())
        .then(data => {
            const ctx = document.getElementById("statisticsChart").getContext("2d");

            // Функция для создания 3D-градиента
            function createGradient(ctx, colorStart, colorEnd) {
                let gradient = ctx.createLinearGradient(0, 0, 0, 400);
                gradient.addColorStop(0, colorStart);
                gradient.addColorStop(1, colorEnd);
                return gradient;
            }

            let gradientPurple = createGradient(ctx, "rgba(128, 0, 128, 1)", "rgba(128, 0, 128, 0.5)");
            let gradientYellow = createGradient(ctx, "rgba(255, 255, 0, 1)", "rgba(255, 255, 0, 0.5)");
            let gradientGreen = createGradient(ctx, "rgba(0, 128, 0, 1)", "rgba(0, 128, 0, 0.5)");
            let gradientBlue = createGradient(ctx, "rgba(0, 0, 255, 1)", "rgba(0, 0, 255, 0.5)");

            new Chart(ctx, {
                type: "pie",
                data: {
                    labels: ["Всего пользователей", "Работодатели", "Соискатели", "Вакансии"],
                    datasets: [{
                        label: "Количество",
                        data: [data.users.length, data.employers.length, data.candidates.length, data.vacancies.length],
                        backgroundColor: [gradientPurple, gradientYellow, gradientGreen, gradientBlue],
                        borderColor: "black",
                        borderWidth: 2,
                        hoverBackgroundColor: ["rgba(128, 0, 128, 0.8)", "rgba(255, 255, 0, 0.8)", "rgba(0, 128, 0, 0.8)", "rgba(0, 0, 255, 0.8)"]
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        legend: {
                            position: 'right',
                            labels: {
                                font: {
                                    size: 18
                                }
                            }
                        },
                        title: {
                            display: true,
                            text: 'Распределение данных платформы',
                            font: {
                                size: 20
                            }
                        },
                        tooltip: {
                            callbacks: {
                                label: function(context) {
                                    return `${context.label}: ${context.raw}`;
                                }
                            }
                        }
                    }
                }
            });
        })
        .catch(error => console.error("Ошибка загрузки статистики:", error));
});
document.addEventListener("DOMContentLoaded", function () {
    fetch("/analyst/statistics/most-popular-vacancies/result")
        .then(response => response.json())
        .then(data => {
            console.log("Полученные данные:", data); // Вывод в консоль

            if (!Array.isArray(data)) {
                throw new Error("Ошибка: API не вернул массив!");
            }

            // 🔹 Подготавливаем данные для диаграммы
            const sortedVacancies = data.map(vacancy => ({
                position: vacancy.position,
                applicationsCount: vacancy.applicationsCount // ✅ исправлено
            })).sort((a, b) => b.applicationsCount - a.applicationsCount).slice(0, 5);

            const ctx = document.getElementById("vacancyChart").getContext("2d");

            // 🔹 Создаём градиентный цвет для ТОП-5 вакансий
            function createGradient(ctx, opacity) {
                let gradient = ctx.createLinearGradient(0, 0, 0, 400);
                gradient.addColorStop(0, `rgba(255, 0, 0, ${opacity})`);
                gradient.addColorStop(1, `rgba(255, 102, 102, ${opacity * 0.5})`);
                return gradient;
            }

            const labels = sortedVacancies.map(v => v.position);
            const values = sortedVacancies.map(v => v.applicationsCount);
            console.log("Количество откликов:", values);
            const colors = values.map((_, i) => createGradient(ctx, 1 - i * 0.2));

            new Chart(ctx, {
                type: "bar",
                data: {
                    labels: labels,
                    datasets: [{
                        label: "Отклики",
                        data: values,
                        backgroundColor: colors,
                        borderColor: "black",
                        borderWidth: 2,
                        hoverBackgroundColor: "rgba(255, 0, 0, 0.8)"
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        })
        .catch(error => console.error("Ошибка загрузки статистики вакансий:", error));
});

