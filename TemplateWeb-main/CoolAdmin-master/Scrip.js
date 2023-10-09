// Lấy các elemments
var modal = document.getElementById("myModal");
var modalButton = document.getElementById("modalButton");
var externalButton = document.getElementById("externalButton");
var closeButton = document.getElementsByClassName("close")[0];

// Sự kiện khi nhấp vào nút "Mở Modal"
modalButton.onclick = function() {
  modal.style.display = "block";
}

// Sự kiện khi nhấp vào nút "Đóng Modal"
closeButton.onclick = function() {
  modal.style.display = "none";
}

// Sự kiện khi nhấp vào nút "Nút Button Bên Ngoài"
externalButton.onclick = function() {
  // Thực hiện hành động cho nút button bên ngoài modal ở đây
  console.log("Đã nhấp vào nút Button Bên Ngoài");
}
