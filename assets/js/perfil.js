document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM cargado completamente');
    loadUserProfile();
    loadUserPreferences();
});

window.addEventListener('load', function() {
    console.log('Ventana cargada completamente');
    loadUserProfile();
});

function loadUserProfile() {
    const userData = localStorage.getItem('currentUser');
    
    if (userData) {
        try {
            const user = JSON.parse(userData);
            const profileNameElement = document.getElementById('profileName');
            
            console.log('Datos del usuario:', user);
            console.log('Elemento profileName:', profileNameElement);
        
        if (user.fullName && user.fullName.trim() !== '') {
            if (profileNameElement) {
            profileNameElement.textContent = user.fullName;
            console.log('Nombre actualizado a:', user.fullName);
            } else {
                    console.error('No se encontró el elemento profileName');
                }
            } else {
                console.log('No se encontró fullName, usando nombre por defecto');
                if (profileNameElement) {
                    profileNameElement.textContent = 'Usuario';
                }
            }
            
        } catch (error) {
            console.error('Error al parsear datos del usuario:', error);
            window.location.href = 'index.html';
        }
    } else {
        console.log('No hay usuario logueado');
        window.location.href = 'index.html';
    }
}

// Función para cargar preferencias del usuario
function loadUserPreferences() {
    const preferences = localStorage.getItem('userPreferences');
    
    if (preferences) {
        const prefs = JSON.parse(preferences);
        
        // Cargar estados de los toggles
        document.getElementById('perfilPrivado').checked = prefs.perfilPrivado || false;
        document.getElementById('notificaciones').checked = prefs.notificaciones !== false; // Por defecto true
        document.getElementById('recibirMensajes').checked = prefs.recibirMensajes || false;
    }
    
    // Agregar listeners para guardar cambios
    document.getElementById('perfilPrivado').addEventListener('change', savePreferences);
    document.getElementById('notificaciones').addEventListener('change', savePreferences);
    document.getElementById('recibirMensajes').addEventListener('change', savePreferences);
}

// Función para guardar preferencias
function savePreferences() {
    const preferences = {
        perfilPrivado: document.getElementById('perfilPrivado').checked,
        notificaciones: document.getElementById('notificaciones').checked,
        recibirMensajes: document.getElementById('recibirMensajes').checked
    };
    
    localStorage.setItem('userPreferences', JSON.stringify(preferences));
    
    // Mostrar feedback visual
    showNotification('Preferencias guardadas');
}

// Función para mostrar notificaciones
function showNotification(message) {
    const notification = document.createElement('div');
    notification.className = 'notification';
    notification.textContent = message;
    notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        background: var(--clr-success);
        color: white;
        padding: 12px 20px;
        border-radius: 8px;
        z-index: 10000;
        box-shadow: 0 4px 12px rgba(0,0,0,0.2);
        animation: slideIn 0.3s ease;
    `;
    
    document.body.appendChild(notification);
    
    // Remover después de 3 segundos
    setTimeout(() => {
        notification.style.animation = 'slideOut 0.3s ease forwards';
        setTimeout(() => {
            document.body.removeChild(notification);
        }, 300);
    }, 3000);
}

// Función para redirigir a otras páginas
function redirectTo(page) {
    // Verificar si el usuario está logueado antes de redirigir
    const userData = localStorage.getItem('currentUser');
    
    if (!userData && page !== 'index.html') {
        showNotification('Debes iniciar sesión para acceder a esta página');
        window.location.href = 'index.html';
        return;
    }
    
    // Redirección directa a páginas existentes
    if (['index.html', 'mensajes.html', 'home.html', 'buscar.html', 'perfil.html'].includes(page)) {
        window.location.href = page;
    } else {
        // Para páginas futuras
        showNotification(`Redirigiendo a ${page}...`);
        setTimeout(() => {
            console.log(`Redirección a ${page} - Página aún no creada`);
        }, 1000);
    }
}

// Función para volver atrás
function goBack() {
    window.history.back();
}

// Función para cerrar sesión
function showLogoutModal() {
  document.getElementById('logoutModal').style.display = 'flex';
}

function closeLogoutModal() {
  document.getElementById('logoutModal').style.display = 'none';
}

function confirmLogout() {
  // Limpia datos del usuario
  localStorage.removeItem('nombreUsuario');
  // Aquí puedes redirigir al login o index.html
  window.location.href = 'index.html';
}

// Verificar si el usuario está logueado al cargar la página
window.addEventListener('load', function() {
    const userData = localStorage.getItem('currentUser');
    
    if (!userData) {
        // Si no hay usuario logueado, mostrar mensaje y redirigir
        showNotification('Debes iniciar sesión para acceder a tu perfil');
        setTimeout(() => {
            window.location.href = 'index.html';
        }, 2000);
    }
});

// Agregar estilos para las animaciones de notificación
const style = document.createElement('style');
style.textContent = `
    @keyframes slideIn {
        from {
            transform: translateX(100%);
            opacity: 0;
        }
        to {
            transform: translateX(0);
            opacity: 1;
        }
    }
    
    @keyframes slideOut {
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
document.head.appendChild(style);