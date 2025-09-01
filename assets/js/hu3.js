const allLabels = ['ENERO', 'FEBRERO', 'MARZO', 'ABRIL', 'MAYO', 'JUNIO', 'JULIO'];
const allData = [3, 2, 4, 4, 5, 4, 6];
const nombreUsuario = 'Usuario';
localStorage.setItem('nombreUsuario', nombreUsuario);
let emotionChart; // para mantener instancia

function showDateRangeModal() {
  document.getElementById('rangeModal').style.display = 'flex';
  const startSelect = document.getElementById('startMonth');
  const endSelect = document.getElementById('endMonth');

  startSelect.addEventListener('change', () => {
    const startVal = parseInt(startSelect.value);
    Array.from(endSelect.options).forEach(option => {
      option.disabled = parseInt(option.value) < startVal;
    });
    if (parseInt(endSelect.value) < startVal) {
      endSelect.value = startVal;
    }
  });

  startSelect.dispatchEvent(new Event('change'));
}


function closeModal() {
  document.getElementById('rangeModal').style.display = 'none';
}

function filterChart() {
  const start = parseInt(document.getElementById('startMonth').value);
  const end = parseInt(document.getElementById('endMonth').value);
  if (start > end) {
    alert("Selecciona un rango válido.");
    return;
  }

  const newLabels = allLabels.slice(start, end + 1);
  const newData = allData.slice(start, end + 1);

  emotionChart.data.labels = newLabels;
  emotionChart.data.datasets[0].data = newData;
  emotionChart.update();

  closeModal();
}

function goBack() {
  window.history.back();
}

function redirectTo(page) {
  window.location.href = page;
}

function exportToPDF() {
  const element = document.querySelector('.hu3-container');
  const opt = {
    margin:       0,
    filename:     'evolucion-emocional.pdf',
    image:        { type: 'jpeg', quality: 0.98 },
    html2canvas:  { scale: 2 },
    jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait' }
  };
  html2pdf().set(opt).from(element).save();
}

document.addEventListener('DOMContentLoaded', () => {
  const ctx = document.getElementById('emotionChart').getContext('2d');

  emotionChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: allLabels,
      datasets: [{
        label: 'Nivel emocional',
        data: allData,
        borderColor: '#8e24aa',
        backgroundColor: '#ce93d8',
        tension: 0.4,
        pointRadius: 5,
        pointBackgroundColor: '#8e24aa'
      }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: { display: false }
      },
      scales: {
        y: {
          ticks: {
            stepSize: 1,
            callback: function (value) {
              return ['😢', '😐', '😊', '😄', '😁', '🤩'][value - 1] || value;
            }
          },
          min: 1,
          max: 6
        }
      }
    }
  });
});

function exportImageWithText() {
  const labels = emotionChart.data.labels;
  const data = emotionChart.data.datasets[0].data;

  // Obtener nombre del usuario desde localStorage
  const username = localStorage.getItem('nombreUsuario') || 'Usuario';
  const today = new Date().toLocaleDateString('es-PE', {
    day: 'numeric', month: 'long', year: 'numeric'
  });

  // Obtener imagen del gráfico
  const canvas = document.getElementById('emotionChart');
  const imageData = canvas.toDataURL('image/jpeg', 1.0);

  // Crear contenido HTML del PDF
  const pdfContent = document.createElement('div');
  pdfContent.style.padding = '30px';
  pdfContent.style.fontFamily = 'Poppins';
  pdfContent.style.fontSize = '14px';
  pdfContent.style.lineHeight = '1.6';

  // Título
  const header = document.createElement('h2');
  header.textContent = `Reporte de evolución emocional`;
  header.style.textAlign = 'center';
  header.style.color = '#1E88E5';
  pdfContent.appendChild(header);

  // Info usuario y fecha
  const userInfo = document.createElement('p');
  userInfo.innerHTML = `<strong>Fecha:</strong> ${today}`;
  pdfContent.appendChild(userInfo);

  // Gráfico como imagen
  const img = document.createElement('img');
  img.src = imageData;
  img.style.width = '100%';
  img.style.margin = '20px 0';
  pdfContent.appendChild(img);

  // Interpretación emocional por mes
  const emotionMap = ['😢 Muy bajo', '😐 Bajo', '😊 Medio', '😄 Bueno', '😁 Muy bueno', '🤩 Excelente'];
  const list = document.createElement('ul');
  list.style.paddingLeft = '20px';

  let suma = 0;
  labels.forEach((month, i) => {
    const li = document.createElement('li');
    const level = data[i];
    li.textContent = `${month}: ${emotionMap[level - 1] || 'Sin dato'}`;
    list.appendChild(li);
    suma += level;
  });

  pdfContent.appendChild(list);

  // Recomendación automática
  const promedio = suma / data.length;
  let consejo = '';

  if (promedio <= 2) {
    consejo = 'Parece que has tenido días desafiantes. Considera hablar con alguien de confianza o buscar apoyo profesional.';
  } else if (promedio <= 4) {
    consejo = 'Tu estado emocional es moderado. Sigue prestando atención a tus emociones y tómate tiempo para ti.';
  } else {
    consejo = '¡Excelente progreso! Sigue cultivando hábitos que te hagan sentir bien contigo mismo.';
  }

  const message = document.createElement('p');
  message.style.marginTop = '25px';
  message.style.padding = '15px';
  message.style.background = '#E3F2FD';
  message.style.borderRadius = '12px';
  message.style.color = '#0D47A1';
  message.style.textAlign = 'center';
  message.innerHTML = `Recuerda que tus emociones son válidas 💙<br><br>${consejo}`;
  pdfContent.appendChild(message);

  // Generar PDF
  html2pdf()
    .set({
      margin: 0.5,
      filename: `reporte-emocional-${username}.pdf`,
      image: { type: 'jpeg', quality: 0.98 },
      html2canvas: { scale: 2 },
      jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' }
    })
    .from(pdfContent)
    .save();
}


