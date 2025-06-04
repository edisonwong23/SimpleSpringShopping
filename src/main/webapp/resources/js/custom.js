// This script updates the current time every second
function updateTime() {
    const now = new Date();
    const timeString = now.toLocaleTimeString();
    $('#currentTime').text(timeString);
}

$(document).ready(function () {
    updateTime();
    setInterval(updateTime, 1000);

        // Prevent form submission if price is invalid
            $("#productForm").submit(function (e) {
                const priceField = $(".price-input");
                let val = priceField.val().replace("RM", "").trim();

                if (!/^\d+(\.\d{1,2})?$/.test(val)) {
                    alert("Please enter a valid price (e.g., 123.45)");
                    priceField.focus();
                    e.preventDefault();
                }
            });
});