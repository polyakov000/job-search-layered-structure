document.addEventListener("DOMContentLoaded", function () {
    fetch("/admin/statistics/result")
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
                type: "bar",
                data: {
                    labels: ["Total Users", "Employers", "Candidates", "Vacancies"],
                    datasets: [{
                        label: "Количество",
                        data: [data.users.length, data.employers.length, data.candidates.length, data.vacancies.length],
                        backgroundColor: [gradientPurple, gradientYellow, gradientGreen, gradientBlue],
                        borderColor: ["black", "black", "black", "black"],
                        borderWidth: 2,
                        barThickness: 50, // Увеличение толщины столбцов
                        hoverBackgroundColor: ["rgba(128, 0, 128, 0.8)", "rgba(255, 255, 0, 0.8)", "rgba(0, 128, 0, 0.8)", "rgba(0, 0, 255, 0.8)"]
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    },
                    plugins: {
                        legend: {
                            labels: {
                                font: {
                                    size: 16
                                }
                            }
                        }
                    }
                }
            });
        })
        .catch(error => console.error("Ошибка загрузки статистики:", error));
});
