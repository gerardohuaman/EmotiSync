// Cargar información del usuario al iniciar la página
document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM de home cargado');
    loadUserData();
    initializeWellnessCards();
});

// Función para cargar datos del usuario
function loadUserData() {
    const userData = localStorage.getItem('currentUser');
    
    if (userData) {
        try {
            const user = JSON.parse(userData);
            const userName = document.getElementById('userName');
            
            console.log('Usuario en home:', user);
            
            // Actualizar el nombre del usuario
            if (user.fullName && user.fullName.trim() !== '') {
                if (userName) {
                    userName.textContent = user.fullName;
                    console.log('Nombre en home actualizado a:', user.fullName);
                }
            }
            
        } catch (error) {
            console.error('Error al cargar datos del usuario:', error);
            window.location.href = 'index.html';
        }
    } else {
        console.log('No hay usuario logueado');
        window.location.href = 'index.html';
    }
}

// Función para inicializar las tarjetas de bienestar
function initializeWellnessCards() {
    const wellnessCards = document.querySelectorAll('.wellness-card');
    
    wellnessCards.forEach((card, index) => {
        card.addEventListener('click', function() {
            handleWellnessCardClick(index);
        });
    });
}

// Función para manejar clics en tarjetas de bienestar
function handleWellnessCardClick(cardIndex) {
    const cardActions = [
        {
            title: 'Estado Mental',
            action: () => {
                showNotification('Redirigiendo al registro de estado mental...', 'info');
                setTimeout(() => {
                    window.location.href = 'mensajes.html';
                }, 1000);
            }
        },
        {
            title: 'Progreso',
            action: () => {
                showNotification('Visualizando tu progreso emocional...', 'info');
                setTimeout(() => {
                    showProgressModal();
                }, 1000);
            }
        },
        {
            title: 'Recursos',
            action: () => {
                showNotification('Accediendo a recursos de apoyo...', 'info');
                setTimeout(() => {
                    window.location.href = 'buscar.html';
                }, 1000);
            }
        }
    ];
    
    if (cardActions[cardIndex]) {
        cardActions[cardIndex].action();
    }
}

// Función para mostrar modal de progreso
function showProgressModal() {
  const semana = getRandomSemana();
  const tendencia = getRandomTendencia();

  const modal = document.createElement('div');
  modal.className = 'progress-modal';
  modal.style.cssText = `
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 10000;
    backdrop-filter: blur(5px);
  `;

  const modalContent = document.createElement('div');
  modalContent.style.cssText = `
    background: white;
    padding: 30px;
    border-radius: 15px;
    max-width: 350px;
    width: 90%;
    text-align: center;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
    animation: modalFadeIn 0.3s ease;
  `;

  modalContent.innerHTML = `
    <h2 style="color: #1565C0; margin-bottom: 20px;">Tu Progreso Emocional</h2>
    <div style="margin-bottom: 20px;">
      <div style="background: #E3F2FD; padding: 15px; border-radius: 10px; margin-bottom: 10px;">
        <h3 style="color: #1565C0; margin-bottom: 5px;">Esta Semana</h3>
        <p style="color: #546E7A;">${semana}</p>
      </div>
      <div style="background: #E8F5E8; padding: 15px; border-radius: 10px; margin-bottom: 10px;">
        <h3 style="color: #4CAF50; margin-bottom: 5px;">Tendencia</h3>
        <p style="color: #546E7A;">${tendencia}</p>
      </div>
    </div>
    <button onclick="closeProgressModal()" style="
      background: #1565C0;
      color: white;
      border: none;
      padding: 12px 24px;
      border-radius: 25px;
      cursor: pointer;
      font-weight: 600;
    ">Cerrar</button>
  `;

  modal.appendChild(modalContent);
  document.body.appendChild(modal);

  modal.addEventListener('click', function (e) {
    if (e.target === modal) closeProgressModal();
  });
}

function getRandomSemana() {
  const opciones = [
    '4 días positivos, 3 días neutros',
    '5 días positivos, 2 días neutros',
    '6 días positivos, 1 día negativo',
    '3 días positivos, 4 días neutros',
    '2 días positivos, 5 días neutros',
    '4 días positivos, 2 días negativos, 1 día neutro'
  ];
  return opciones[Math.floor(Math.random() * opciones.length)];
}

function getRandomTendencia() {
  const mejoras = [
    'Mejora del 10% respecto a la semana anterior',
    'Mejora del 15% respecto a la semana anterior',
    'Estabilidad respecto a la semana pasada',
    'Ligera disminución del 5%, ¡aún puedes mejorar!',
    'Mejora del 20% respecto a la semana anterior',
    'Progreso notable en comparación con el mes pasado'
  ];
  return mejoras[Math.floor(Math.random() * mejoras.length)];
}


// Función para cerrar modal de progreso
function closeProgressModal() {
    const modal = document.querySelector('.progress-modal');
    if (modal) {
        modal.style.animation = 'modalFadeOut 0.3s ease forwards';
        setTimeout(() => {
            document.body.removeChild(modal);
        }, 300);
    }
}

// Función para manejar clics en imágenes
function handleImageClick(imageIndex) {
    const imageTexts = [
        'Paisaje relajante para la meditación y el bienestar mental',
        'Naturaleza que inspira tranquilidad y paz interior'
    ];
    
    showNotification(imageTexts[imageIndex], 'info');
}

// Función para redirigir a otras páginas
function redirectTo(page) {
    const userData = localStorage.getItem('currentUser');
    
    if (!userData && page !== 'index.html') {
        showNotification('Debes iniciar sesión para acceder a esta página');
        window.location.href = 'index.html';
        return;
    }
    
    window.location.href = page;
}

// Función para volver atrás
function goBack() {
    window.history.back();
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
    }, 3000);
}

// Agregar listeners para las imágenes
document.addEventListener('DOMContentLoaded', function() {
    const galleryImages = document.querySelectorAll('.gallery-image');
    
    galleryImages.forEach((image, index) => {
        image.addEventListener('click', function() {
            handleImageClick(index);
        });
        
        // Agregar cursor pointer
        image.style.cursor = 'pointer';
    });
});

// Agregar estilos para las animaciones
const style = document.createElement('style');
style.textContent = `
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
    
    @keyframes modalFadeIn {
        from {
            opacity: 0;
            transform: translateY(-20px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
    
    @keyframes modalFadeOut {
        from {
            opacity: 1;
            transform: translateY(0);
        }
        to {
            opacity: 0;
            transform: translateY(-20px);
        }
    }
`;
document.head.appendChild(style);