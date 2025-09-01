// Cargar información del usuario al iniciar la página
document.addEventListener('DOMContentLoaded', function () {
  console.log('DOM de mensajes cargado');
  loadUserData();
  clearPreviousMessages();
  showInitialMessage();
});

// Función para cargar datos del usuario
function loadUserData() {
    const userData = localStorage.getItem('currentUser');
    
    if (userData) {
        try {
            const user = JSON.parse(userData);
            const messagesTitle = document.getElementById('messagesTitle');
            
            console.log('Usuario en mensajes:', user);
            
            // Actualizar el título con el nombre del usuario
            if (user.fullName && user.fullName.trim() !== '') {
                if (messagesTitle) {
                    messagesTitle.textContent = user.fullName;
                    console.log('Título actualizado a:', user.fullName);
                }
            }
            
            // Actualizar mensajes personalizados
            updatePersonalizedMessages(user.fullName);
            
        } catch (error) {
            console.error('Error al cargar datos del usuario:', error);
            window.location.href = 'index.html';
        }
    } else {
        console.log('No hay usuario logueado');
        window.location.href = 'index.html';
    }
}

//Funcion para saludo inicial
function showInitialMessage() {
  const userData = JSON.parse(localStorage.getItem('currentUser'));
  const name = userData?.fullName?.split(' ')[0] || 'Usuario';

  const message = `Hola ${name} 👋 ¿Cómo te sientes hoy?.`;
  addMessage(message, 'system');
}

// Funcion para limpiar mensajes pasados
function clearPreviousMessages() {
  const chat = document.querySelector('.chat-container');
  if (chat) chat.innerHTML = '';
}

// Función para personalizar mensajes
function updatePersonalizedMessages(userName) {
    const firstName = userName ? userName.split(' ')[0] : 'Usuario';
    
    // Buscar mensajes del sistema y personalizarlos
    const systemMessages = document.querySelectorAll('.system-message .message-content p');
    
    if (systemMessages.length > 0) {
        systemMessages[0].textContent = `Hola ${firstName} 👋 ¿Cómo te sientes hoy? Puedes registrar tu emoción diaria aquí.`;
    }
}

// Función para enviar mensaje
function sendMessage() {
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value.trim();
    
    if (message === '') return;
    
    // Agregar mensaje del usuario
    addMessage(message, 'user');
    
    // Limpiar input
    messageInput.value = '';
    
    // Simular respuesta automática después de un breve delay
    setTimeout(() => {
        addTypingIndicator();
        setTimeout(() => {
            removeTypingIndicator();
            addMessage(generateResponse(message), 'system');
        }, 1500);
    }, 500);
}

// Función para agregar mensaje al chat
function addMessage(text, type) {
    const chatContainer = document.querySelector('.chat-container');
    const messageGroup = document.createElement('div');
    messageGroup.className = `message-group ${type === 'user' ? 'user-group' : ''}`;
    
    const message = document.createElement('div');
    message.className = `message ${type}-message`;
    
    const messageContent = document.createElement('div');
    messageContent.className = 'message-content';
    messageContent.innerHTML = `<p>${text}</p>`;
    
    const messageAvatar = document.createElement('div');
    messageAvatar.className = 'message-avatar';
    
    const avatarImg = document.createElement('img');
    avatarImg.src = type === 'user' ? 'assets/img/avatar.png' : 'assets/img/logo de neurobrigdge.jpg';
    avatarImg.alt = type === 'user' ? 'Avatar de usuario' : 'NeuroBridge';
    messageAvatar.appendChild(avatarImg);
    
    if (type === 'user') {
        message.appendChild(messageContent);
        message.appendChild(messageAvatar);
    } else {
        message.appendChild(messageAvatar);
        message.appendChild(messageContent);
    }
    
    messageGroup.appendChild(message);
    chatContainer.appendChild(messageGroup);
    
    // Scroll hacia abajo
    scrollToBottom();
}

// Función para generar respuesta automática
function generateResponse(userMessage) {
  const respuestas = [
    "Gracias por compartir cómo te sientes. ¿Te gustaría reflexionar sobre qué lo ha causado?",
    "Entiendo. ¿Te gustaría registrar esta emoción para seguir tu progreso?",
    "Lo importante es reconocer cómo te sientes. ¿Te ha pasado esto en otros días?",
    "Puedes tomar un momento para respirar profundamente. ¿Quieres que te recomiende un ejercicio?",
    "Recuerda que expresar tus emociones es el primer paso hacia el bienestar.",
    "Gracias por confiar en este espacio. ¿Quieres hablar un poco más sobre eso?",
    "Me parece excelente, recuerda que vales mucho y siempre tienes que ver lo positivo de las cosas",
    "Recuerda que puedo ayudarte, pero también es bueno hablarlo con un profesional"
  ];

  const lowerMsg = userMessage.toLowerCase();
  if (lowerMsg.includes("bien") || lowerMsg.includes("positivo") || lowerMsg.includes("excelente")) {
    return "¡Me alegra mucho saber eso! ¿Qué crees que ha contribuido a este estado?";
  }
  if (lowerMsg.includes("mal") || lowerMsg.includes("ansioso") || lowerMsg.includes("estresado")) {
    return "Lamento que te sientas así. ¿Quieres hacer una pausa guiada o registrar esta emoción?";
  }

  return respuestas[Math.floor(Math.random() * respuestas.length)];
}

// Función para agregar indicador de escritura
function addTypingIndicator() {
    const chatContainer = document.querySelector('.chat-container');
    const typingIndicator = document.createElement('div');
    typingIndicator.className = 'typing-indicator';
    typingIndicator.id = 'typing-indicator';
    
    for (let i = 0; i < 3; i++) {
        const dot = document.createElement('div');
        dot.className = 'typing-dot';
        typingIndicator.appendChild(dot);
    }
    
    chatContainer.appendChild(typingIndicator);
    scrollToBottom();
}

// Función para remover indicador de escritura
function removeTypingIndicator() {
    const typingIndicator = document.getElementById('typing-indicator');
    if (typingIndicator) {
        typingIndicator.remove();
    }
}

// Función para scroll hacia abajo
function scrollToBottom() {
    const chatContainer = document.querySelector('.chat-container');
    setTimeout(() => {
        chatContainer.scrollTop = chatContainer.scrollHeight;
    }, 100);
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

// Permitir enviar mensaje con Enter
document.addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        const messageInput = document.getElementById('messageInput');
        if (document.activeElement === messageInput) {
            sendMessage();
        }
    }
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
`;
document.head.appendChild(style);