function goBack() {
  window.history.back();
}

function redirectTo(page) {
  window.location.href = page;
}

document.addEventListener("DOMContentLoaded", function () {
  const volverBtn = document.getElementById("volverBtn");
  const preguntasEstado = document.querySelectorAll(".pregunta-estado");
  const preguntasOrigen = document.querySelectorAll(".pregunta-origen");

  volverBtn.addEventListener("click", function () {
    window.history.back();
  });

  preguntasEstado.forEach((btn) => {
    btn.addEventListener("click", function () {
      alert("Has seleccionado una pregunta sobre el estado emocional actual: " + btn.innerText);
    });
  });

  preguntasOrigen.forEach((btn) => {
    btn.addEventListener("click", function () {
      alert("Has seleccionado una pregunta sobre el origen de la emoción: " + btn.innerText);
    });
  });
});