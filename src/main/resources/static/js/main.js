// 1. Tính năng Live Search (Tìm kiếm thời gian thực không cần load trang)
document.addEventListener("DOMContentLoaded", function () {
  const searchInput = document.getElementById("liveSearchInput");
  const tableBody = document.getElementById("studentTableBody");

  if (searchInput && tableBody) {
    const rows = tableBody.querySelectorAll(".student-row");
    const noResultRow = document.getElementById("noResultRow");

    searchInput.addEventListener("input", function () {
      const keyword = this.value.toLowerCase().trim();
      let hasVisibleRow = false;

      rows.forEach((row) => {
        const rowText = row.textContent.toLowerCase();
        if (rowText.includes(keyword)) {
          row.style.display = "";
          hasVisibleRow = true;
        } else {
          row.style.display = "none";
        }
      });

      if (!hasVisibleRow && keyword !== "") {
        noResultRow.style.display = "";
      } else {
        noResultRow.style.display = "none";
      }
    });
  }
});

// 2. Cảnh báo xác nhận xóa (An toàn, chống lỗi Thymeleaf)
function confirmDelete(id) {
  if (
    confirm(
      "Bạn có chắc chắn muốn xóa hồ sơ này?\nHành động này không thể hoàn tác.",
    )
  ) {
    document.getElementById("deleteForm-" + id).submit();
  }
}

// 3. Tự động ẩn thông báo Flash Messages sau 3.5s
document.addEventListener("DOMContentLoaded", function () {
  setTimeout(function () {
    let alerts = document.querySelectorAll(".alert");
    alerts.forEach(function (alert) {
      alert.classList.remove("show");
      setTimeout(() => alert.remove(), 200);
    });
  }, 3500);
});
