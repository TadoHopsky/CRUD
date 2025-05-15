document.addEventListener("DOMContentLoaded", () => {
    console.log("Страница загружена!");

    /*
        Анимация появления контейнера
    */
    const containers = document.querySelectorAll(".container, .error-container");
    containers.forEach(container => {
        container.style.opacity = "0";
        container.style.transform = "translateY(-20px)";
        setTimeout(() => {
            container.style.transition = "opacity 1.5s ease-in-out, transform 1.5s ease-in-out";
            container.style.opacity = "1";
            container.style.transform = "translateY(0)";
        }, 100);
    });


    /*
        Анимация "Матрица" на canvas
    */
    const canvas = document.getElementById("matrix");
    const ctx = canvas.getContext("2d");

    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;

    const letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    const fontSize = 16;
    const columns = canvas.width / fontSize;

    const drops = Array(Math.floor(columns)).fill(1);

    function drawMatrix() {
        ctx.fillStyle = "rgba(13, 17, 23, 0.1)";
        ctx.fillRect(0, 0, canvas.width, canvas.height);

        ctx.fillStyle = "#002f65";
        ctx.font = `${fontSize}px monospace`;

        drops.forEach((y, index) => {
            const text = letters[Math.floor(Math.random() * letters.length)];
            const x = index * fontSize;

            ctx.fillText(text, x, y * fontSize);

            if (y * fontSize > canvas.height && Math.random() > 0.975) {
                drops[index] = 0;
            }

            drops[index] += 0.5; // Уменьшение скорости падения символов
        });

        requestAnimationFrame(drawMatrix);
    }

    drawMatrix();

    window.addEventListener("resize", () => {
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
    });
});
