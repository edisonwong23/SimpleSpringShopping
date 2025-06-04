// This script updates the current time every second
function updateTime() {
    const now = new Date();
    const timeString = now.toLocaleTimeString();
    $('#currentTime').text(timeString);
}

$(document).ready(function () {
    updateTime();
    setInterval(updateTime, 1000);
});