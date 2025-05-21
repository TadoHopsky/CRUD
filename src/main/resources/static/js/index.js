document.addEventListener("DOMContentLoaded", () => {
    // Анимация появления контейнера
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
});
