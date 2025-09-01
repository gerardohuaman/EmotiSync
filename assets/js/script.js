// Toggle del blog
document.addEventListener('DOMContentLoaded', function() {
    const toggleBlogButton = document.getElementById('toggle-blog-button');
    const blogSection = document.getElementById('blog-section');
    const authModal = document.getElementById('auth-modal');
    const closeModal = document.querySelector('.close-modal');
    const tabButtons = document.querySelectorAll('.tab-btn');
    const authForms = document.querySelectorAll('.auth-form');
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');
    const iniciarButton = document.querySelector('.btn-secondary');
    const unirseButton = document.querySelector('.btn-primary');

    // Toggle del blog
    if (toggleBlogButton && blogSection) {
        toggleBlogButton.addEventListener('click', function(e) {
            e.preventDefault();
            blogSection.classList.toggle('is-visible');
        });
    }

    // Abrir modal de autenticación
    if (iniciarButton) {
        iniciarButton.addEventListener('click', function() {
            authModal.style.display = 'block';
            // Mostrar formulario de login por defecto
            showLoginForm();
        });
    }

    if (unirseButton) {
        unirseButton.addEventListener('click', function() {
            authModal.style.display = 'block';
            // Mostrar formulario de registro
            showRegisterForm();
        });
    }

    // Cerrar modal
    if (closeModal) {
        closeModal.addEventListener('click', function() {
            authModal.style.display = 'none';
        });
    }

    // Cerrar modal al hacer clic fuera
    window.addEventListener('click', function(event) {
        if (event.target === authModal) {
            authModal.style.display = 'none';
        }
    });

    // Cambiar entre tabs
    tabButtons.forEach(button => {
        button.addEventListener('click', function() {
            const targetTab = this.getAttribute('data-tab');
            
            // Remover active de todos los tabs
            tabButtons.forEach(btn => btn.classList.remove('active'));
            authForms.forEach(form => form.classList.remove('active'));
            
            // Agregar active al tab seleccionado
            this.classList.add('active');
            
            if (targetTab === 'login') {
                showLoginForm();
            } else {
                showRegisterForm();
            }
        });
    });


    // Función para mostrar formulario de login
    function showLoginForm() {
        authForms.forEach(form => form.classList.remove('active'));
        loginForm.classList.add('active');
        tabButtons.forEach(btn => btn.classList.remove('active'));
        document.querySelector('[data-tab="login"]').classList.add('active');
    }

    // Función para mostrar formulario de registro
    function showRegisterForm() {
        authForms.forEach(form => form.classList.remove('active'));
        registerForm.classList.add('active');
        tabButtons.forEach(btn => btn.classList.remove('active'));
        document.querySelector('[data-tab="register"]').classList.add('active');
    }

    // Manejar envío del formulario de login
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            const email = document.getElementById('login-email').value;
            const password = document.getElementById('login-password').value;
            const userType = document.querySelector('input[name="user-type"]:checked').value;
            
            // Verificar si el usuario existe en localStorage
            const registeredUsers = JSON.parse(localStorage.getItem('registeredUsers') || '[]');
            const user = registeredUsers.find(u => u.email === email && u.password === password);
            
            if (user) {
                // Login exitoso
                localStorage.setItem('currentUser', JSON.stringify(user));
                showNotification('¡Inicio de sesión exitoso!', 'success');
                
                // Cerrar modal
                authModal.style.display = 'none';
                
                // Redirigir al perfil después de un breve delay
                setTimeout(() => {
                    window.location.href = 'perfil.html';
                }, 1500);
            } else {
                showNotification('Credenciales incorrectas. Por favor, verifica tu email y contraseña.', 'error');
            }
        });
    }

    // Manejar envío del formulario de registro
    if (registerForm) {
        registerForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            const name = document.getElementById('register-name').value;
            const email = document.getElementById('register-email').value;
            const password = document.getElementById('register-password').value;
            const confirmPassword = document.getElementById('register-confirm-password').value;
            const userType = document.querySelector('input[name="user-type-register"]:checked').value;
            
            // Validaciones
            if (password !== confirmPassword) {
                showNotification('Las contraseñas no coinciden', 'error');
                return;
            }
            
            if (password.length < 6) {
                showNotification('La contraseña debe tener al menos 6 caracteres', 'error');
                return;
            }
            
            // Verificar si el email ya existe
            const registeredUsers = JSON.parse(localStorage.getItem('registeredUsers') || '[]');
            if (registeredUsers.find(u => u.email === email)) {
                showNotification('Este email ya está registrado', 'error');
                return;
            }
            
            // Crear nuevo usuario
            const newUser = {
                fullName: name.trim(),
                email: email.trim(),
                password: password,
                userType: userType,
                registeredAt: new Date().toISOString()
            };
            
            // Guardar usuario
            registeredUsers.push(newUser);
            localStorage.setItem('registeredUsers', JSON.stringify(registeredUsers));
            localStorage.setItem('currentUser', JSON.stringify(newUser));
            console.log('Usuario guardado en localStorage:', JSON.parse(localStorage.getItem('currentUser')));
            
            // Mostrar mensaje de éxito
            showNotification('¡Registro exitoso! Redirigiendo al perfil...', 'success');
            
            // Cerrar modal
            authModal.style.display = 'none';
            
            // Redirigir al perfil después de un breve delay
            setTimeout(() => {
                window.location.href = 'perfil.html';
            }, 1500);
        });
    }

    // Manejar envío del formulario de consulta
    const consultaForm = document.querySelector('.form-consulta');
    if (consultaForm) {
        consultaForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            const nombre = document.getElementById('nombre').value;
            const correo = document.getElementById('correo').value;
            const mensaje = document.getElementById('mensaje').value;
            
            // Simular envío exitoso
            showNotification('¡Consulta enviada exitosamente! Te contactaremos pronto.', 'success');
            
            // Limpiar formulario
            consultaForm.reset();
        });
    }

    // Función para mostrar notificaciones
    function showNotification(message, type = 'info') {
        const notification = document.createElement('div');
        notification.className = `notification ${type}`;
        notification.textContent = message;
        
        const backgroundColor = type === 'success' ? '#4CAF50' : type === 'error' ? '#f44336' : '#2196F3';
        
        notification.style.cssText = `
            position: fixed;
            top: 20px;
            right: 20px;
            background: ${backgroundColor};
            color: white;
            padding: 15px 20px;
            border-radius: 8px;
            z-index: 10000;
            box-shadow: 0 4px 12px rgba(0,0,0,0.2);
            animation: slideInNotification 0.3s ease;
            max-width: 300px;
            word-wrap: break-word;
        `;
        
        document.body.appendChild(notification);
        
        setTimeout(() => {
            notification.style.animation = 'slideOutNotification 0.3s ease forwards';
            setTimeout(() => {
                if (document.body.contains(notification)) {
                    document.body.removeChild(notification);
                }
            }, 300);
        }, 4000);
    }

    // Animaciones para scroll
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('visible');
            }
        });
    }, observerOptions);

    // Observar elementos con animación
    document.querySelectorAll('[data-animate]').forEach(el => {
        observer.observe(el);
    });
});

// Agregar estilos para las animaciones de notificación
const notificationStyle = document.createElement('style');
notificationStyle.textContent = `
    @keyframes slideInNotification {
        from {
            transform: translateX(100%);
            opacity: 0;
        }
        to {
            transform: translateX(0);
            opacity: 1;
        }
    }
    
    @keyframes slideOutNotification {
        from {
            transform: translateX(0);
            opacity: 1;
        }
        to {
            transform: translateX(100%);
            opacity: 0;
        }
    }
`;
document.head.appendChild(notificationStyle);