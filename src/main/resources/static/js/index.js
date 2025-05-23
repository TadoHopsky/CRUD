document.addEventListener("DOMContentLoaded", () => {
    // Анимация появления карточек пользователей
    document.querySelectorAll('.users-list.grid-list li').forEach((el, i) => {
        setTimeout(() => el.classList.add('fade-in'), 80 * i);
    });

    // Для будущих карточек книг
    document.querySelectorAll('.books-list.grid-list li').forEach((el, i) => {
        setTimeout(() => el.classList.add('fade-in'), 80 * i);
    });

    // Переключатель темы
    const themeBtn = document.getElementById('theme-toggle');
    if (themeBtn) {
        themeBtn.addEventListener('click', () => {
            document.body.classList.toggle('dark-theme');
        });
    }
});
