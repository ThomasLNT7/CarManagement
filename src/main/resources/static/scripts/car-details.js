document.addEventListener('DOMContentLoaded', () => {
    const container = document.querySelector('.container');
    container.addEventListener('mouseenter', () => {
        container.style.boxShadow = '0 12px 25px rgba(0, 0, 0, 0.2)';
    });
    container.addEventListener('mouseleave', () => {
        container.style.boxShadow = '0 8px 20px rgba(0, 0, 0, 0.1)';
    });

    const buttons = document.querySelectorAll('button');
    buttons.forEach(button => {
        button.addEventListener('click', () => {
            button.style.transform = 'scale(0.95)';
            setTimeout(() => {
                button.style.transform = 'scale(1)';
            }, 150);
        });
    });
});