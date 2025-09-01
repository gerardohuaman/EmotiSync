document.querySelector('.save').addEventListener('click', function() {
  document.getElementById('successMessage').style.display = 'block';
});

document.querySelector('.omit').addEventListener('click', function() {
  document.querySelector('textarea').value = '';
  document.getElementById('successMessage').style.display = 'none';
});
// Mostrar nombre del usuario
document.addEventListener('DOMContentLoaded', () => {
  const userData = JSON.parse(localStorage.getItem('currentUser'));
  if (userData && userData.fullName) {
    document.getElementById('userName').textContent = userData.fullName.split(' ')[0];
  }
});

// Funciones base
function redirectTo(page) {
  window.location.href = page;
}

function goBack() {
  window.history.back();
}

function saveEmotion() {
  const textarea = document.getElementById('emotionText');
  const success = document.getElementById('successMessage');
  if (textarea.value.trim() !== '') {
    success.style.display = 'block';
    setTimeout(() => {
      success.style.display = 'none';
    }, 3000);
  }
}

function omitEmotion() {
  document.getElementById('emotionText').value = '';
  document.getElementById('successMessage').style.display = 'none';
}

function triggerCrisisMode() {
  const modal = document.getElementById('crisisModal');
  modal.classList.add('active');
}

function closeCrisisModal() {
  const modal = document.getElementById('crisisModal');
  modal.classList.remove('active');
}
