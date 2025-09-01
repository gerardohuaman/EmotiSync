let timer = 60;
let intervalId;
const timerDisplay = document.getElementById("timer");

function startTimer() {
  clearInterval(intervalId);
  timer = 60;
  updateTimerDisplay();

  intervalId = setInterval(() => {
    timer--;
    updateTimerDisplay();
    if (timer <= 0) {
      clearInterval(intervalId);
    }
  }, 1000);
}

function updateTimerDisplay() {
  const minutes = Math.floor(timer / 60);
  const seconds = timer % 60;
  timerDisplay.textContent = `${minutes < 10 ? "0" : ""}${minutes}:${seconds < 10 ? "0" : ""}${seconds}`;
}

document.getElementById("reiniciar").addEventListener("click", () => {
  startTimer();
});

document.getElementById("finalizar").addEventListener("click", () => {
  document.getElementById("modal").style.display = "flex";
  clearInterval(intervalId);
});

document.getElementById("cerrar-modal").addEventListener("click", () => {
  document.getElementById("modal").style.display = "none";
});

document.getElementById("musica").addEventListener("click", () => {
  const audio = document.getElementById("audio");
  audio.volume = 0.3;

  if (audio.paused) {
    audio.play();
  } else {
    audio.pause();
  }
});


// iniciar al cargar
startTimer();
