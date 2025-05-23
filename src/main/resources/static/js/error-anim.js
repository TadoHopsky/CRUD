document.addEventListener("DOMContentLoaded", () => {
    const fadeBg = document.getElementById('error-fade-bg');
    const img = document.getElementById('error-anim-img');
    const imgLeft = document.getElementById('error-anim-img-left');
    const msg = document.querySelector('.error-center-message');

    // 1. Запуск затемнения
    setTimeout(() => {
        fadeBg.style.opacity = "0.92";
    }, 100);

    // 2. На половине затемнения — появление картинок снизу
    setTimeout(() => {
        img.style.opacity = "1";
        img.style.transform = "translateY(0)";
        if (imgLeft) {
            imgLeft.style.opacity = "1";
            imgLeft.style.transform = "translateY(0)";
        }
    }, 700);

    // 3. Через 1.2с — показываем сообщение
    setTimeout(() => {
        msg.style.opacity = "1";
    }, 1200);

    // Скрываем сообщение до анимации
    msg.style.opacity = "0";
});
