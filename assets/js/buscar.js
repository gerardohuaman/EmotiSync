// Cargar información del usuario al iniciar la página
document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM de buscar cargado');
    checkUserAuthentication();
    initializeSearchFunctionality();
});

// Función para verificar autenticación del usuario
function checkUserAuthentication() {
    const userData = localStorage.getItem('currentUser');
    
    if (!userData) {
        console.log('No hay usuario logueado');
        window.location.href = 'index.html';
    } else {
        console.log('Usuario autenticado en buscar');
    }
}

// Función para inicializar la funcionalidad de búsqueda
function initializeSearchFunctionality() {
    const searchInput = document.getElementById('searchInput');
    
    // Agregar listener para buscar con Enter
    searchInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            performSearch();
        }
    });
    
    // Autocompletado y sugerencias
    searchInput.addEventListener('input', function() {
        handleSearchInput(this.value);
    });
}

// Función para manejar entrada de búsqueda
function handleSearchInput(query) {
    if (query.length < 2) return;
    
    const suggestions = getSuggestions(query);
    showSearchSuggestions(suggestions);
}

// Función para obtener sugerencias basadas en la consulta
function getSuggestions(query) {
    const allSuggestions = [
        'Registrar estado emocional diario',
        'Registro de emociones',
        'Seguimiento emocional',
        'Bienestar mental',
        'Técnicas de relajación',
        'Mindfulness',
        'Meditación',
        'Ansiedad',
        'Depresión',
        'Autoestima',
        'Gestión del estrés',
        'Patrones emocionales',
        'Diario de gratitud',
        'Ejercicios de respiración',
        'Terapia cognitiva'
    ];
    
    return allSuggestions.filter(suggestion => 
        suggestion.toLowerCase().includes(query.toLowerCase())
    );
}

// Función para mostrar sugerencias de búsqueda
function showSearchSuggestions(suggestions) {
    // Por ahora, solo mostrar en consola
    console.log('Sugerencias:', suggestions);
}

// Función para realizar búsqueda
function performSearch() {
    const searchInput = document.getElementById('searchInput');
    const query = searchInput.value.trim();
    
    if (query === '') {
        showNotification('Por favor ingresa un término de búsqueda', 'warning');
        return;
    }
    
    showNotification('Buscando...', 'info');
    showLoadingIndicator();
    
    // Simular búsqueda
    setTimeout(() => {
        hideLoadingIndicator();
        const results = searchResources(query);
        displaySearchResults(results, query);
    }, 1500);
}

// Función para buscar recursos
function searchResources(query) {
    const resources = [
        {
            title: 'Registro Diario de Emociones',
            description: 'Herramienta para registrar y monitorear tus emociones diarias. Incluye seguimiento de patrones y análisis de tendencias.',
            category: 'Herramientas',
            relevance: 95
        },
        {
            title: 'Técnicas de Mindfulness',
            description: 'Ejercicios de atención plena para reducir el estrés y mejorar el bienestar emocional.',
            category: 'Técnicas',
            relevance: 85
        },
        {
            title: 'Guía de Manejo de Ansiedad',
            description: 'Estrategias y técnicas para identificar y manejar la ansiedad en la vida diaria.',
            category: 'Recursos',
            relevance: 90
        },
        {
            title: 'Ejercicios de Respiración',
            description: 'Técnicas de respiración para calmar la mente y reducir el estrés.',
            category: 'Ejercicios',
            relevance: 80
        },
        {
            title: 'Diario de Gratitud',
            description: 'Práctica diaria para cultivar una mentalidad positiva y agradecimiento.',
            category: 'Herramientas',
            relevance: 75
        }
    ];
    
    // Filtrar recursos basados en la consulta
    const filteredResources = resources.filter(resource => 
        resource.title.toLowerCase().includes(query.toLowerCase()) ||
        resource.description.toLowerCase().includes(query.toLowerCase())
    );
    
    // Ordenar por relevancia
    return filteredResources.sort((a, b) => b.relevance - a.relevance);
}

// Función para mostrar resultados de búsqueda
function displaySearchResults(results, query) {
    const searchResults = document.getElementById('searchResults');
    const resultsList = document.getElementById('resultsList');
    const suggestionsSection = document.querySelector('.suggestions-section');
    
    // Limpiar resultados anteriores
    resultsList.innerHTML = '';
    
    if (results.length === 0) {
        resultsList.innerHTML = `
            <div class="no-results">
                <div class="no-results-icon">🔍</div>
                <h3>No se encontraron resultados</h3>
                <p>Intenta con diferentes términos de búsqueda</p>
            </div>
        `;
    } else {
        results.forEach(result => {
            const resultItem = document.createElement('div');
            resultItem.className = 'result-item';
            resultItem.innerHTML = `
                <h3>${result.title}</h3>
                <p>${result.description}</p>
                <small style="color: #1565C0; font-weight: 500;">${result.category}</small>
            `;
            
            resultItem.addEventListener('click', function() {
                openResource(result);
            });
            
            resultsList.appendChild(resultItem);
        });
    }
    
    // Mostrar sección de resultados y ocultar sugerencias
    suggestionsSection.style.display = 'none';
    searchResults.style.display = 'block';
    
    showNotification(`Se encontraron ${results.length} resultado(s) para "${query}"`, 'success');
}

// Función para abrir recurso
function openResource(resource) {
    showNotification(`Abriendo: ${resource.title}`, 'info');
    
    // Simular apertura del recurso
    setTimeout(() => {
        showResourceModal(resource);
    }, 500);
}

// Función para mostrar modal de recurso
function showResourceModal(resource) {
    const modal = document.createElement('div');
    modal.className = 'resource-modal';
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
        max-width: 400px;
        width: 90%;
        text-align: center;
        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
        animation: modalFadeIn 0.3s ease;
    `;
    
    modalContent.innerHTML = `
        <h2 style="color: #1565C0; margin-bottom: 15px;">${resource.title}</h2>
        <p style="color: #546E7A; margin-bottom: 20px; line-height: 1.6;">${resource.description}</p>
        <div style="background: #E3F2FD; padding: 15px; border-radius: 10px; margin-bottom: 20px;">
            <h4 style="color: #1565C0; margin-bottom: 10px;">Próximamente disponible</h4>
            <p style="color: #546E7A; font-size: 14px;">Este recurso estará disponible en futuras actualizaciones de la aplicación.</p>
        </div>
        <button onclick="closeResourceModal()" style="
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
    
    // Cerrar modal al hacer clic fuera
    modal.addEventListener('click', function(e) {
        if (e.target === modal) {
            closeResourceModal();
        }
    });
}

// Función para cerrar modal de recurso
function closeResourceModal() {
    const modal = document.querySelector('.resource-modal');
    if (modal) {
        modal.style.animation = 'modalFadeOut 0.3s ease forwards';
        setTimeout(() => {
            document.body.removeChild(modal);
        }, 300);
    }
}

// Función para abrir sugerencia
function openSuggestion(suggestionId) {
    const suggestions = {
        'registro-emociones': {
            title: 'Mi Registro Diario de Emociones',
            description: 'Herramienta completa para registrar y monitorear tus emociones diarias. Incluye análisis de patrones y tendencias emocionales.',
            category: 'Herramientas Principales'
        },
        'semana-emociones': {
            title: 'Mi Semana de Emociones',
            description: 'Visualiza tu progreso emocional semanal con gráficos y estadísticas detalladas.',
            category: 'Análisis y Seguimiento'
        },
        'seguimiento-emocional': {
            title: 'Seguimiento Emocional Detallado',
            description: 'Análisis completo de tu bienestar emocional con recomendaciones personalizadas.',
            category: 'Análisis Avanzado'
        }
    };
    
    const suggestion = suggestions[suggestionId];
    if (suggestion) {
        showResourceModal(suggestion);
    }
}

// Función para mostrar indicador de carga
function showLoadingIndicator() {
    const searchResults = document.getElementById('searchResults');
    const resultsList = document.getElementById('resultsList');
    
    resultsList.innerHTML = `
        <div class="loading">
            <div class="loading-spinner"></div>
            <p>Buscando recursos...</p>
        </div>
    `;
    
    searchResults.style.display = 'block';
}

// Función para ocultar indicador de carga
function hideLoadingIndicator() {
    // El indicador se oculta automáticamente cuando se muestran los resultados
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
    
    const backgroundColor = type === 'success' ? '#4CAF50' : 
                           type === 'error' ? '#f44336' : 
                           type === 'warning' ? '#FF9800' : '#2196F3';
    
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