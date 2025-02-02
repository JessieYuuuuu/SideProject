$("#memberSources").click(function () {
    fetch("memberSources")
    .then(response => response.text())
    .then(data => { 
        $("#centerText").html(data); 
    })
    .catch(error => console.error('Error loading content:', error));
});
$("#memberWork").click(function () {
    fetch("memberWork")
    .then(response => response.text())
    .then(data => { 
        $("#centerText").html(data); 
    })
    .catch(error => console.error('Error loading content:', error));
});
$("#memberPush").click(function () {
    fetch("memberPush")
    .then(response => response.text())
    .then(data => { 
        $("#centerText").html(data); 
    })
    .catch(error => console.error('Error loading content:', error));
});
$("#memberPull").click(function () {
    fetch("memberPull")
    .then(response => response.text())
    .then(data => { 
        $("#centerText").html(data); 
    })
    .catch(error => console.error('Error loading content:', error));
});
$("#memberLike").click(function () {
    fetch("memberLike")
    .then(response => response.text())
    .then(data => { 
        $("#centerText").html(data); 
    })
    .catch(error => console.error('Error loading content:', error));
});
$("#memberMail").click(function () {
    event.preventDefault();
    fetch("memberMail")
    .then(response => response.text())
    .then(data => { 
        $("#centerText").html(data); 
    })
    .catch(error => console.error('Error loading content:', error));
});