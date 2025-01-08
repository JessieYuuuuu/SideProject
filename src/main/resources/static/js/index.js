$(document).ready(function () {
    $("#searchButton").on('click', function () {
        const keyword = $("#keyword").val();
        const options = [];
        $("input[name='options']:checked").each(function () {
            options.push($(this).val());
        });
        const queryString = `keyword=${encodeURIComponent(keyword)}&options=${options.join(',')}`;
        // window.location.href = `/search.html?${queryString}`;
        
        $("#searchForm").submit();
    });
});
// ------------以下可傳遞checkBox-----------
$(document).ready(function () {
    console.log("Document ready"); // 調試信息
    $("#searchButton").on('click', function () {
        console.log("觸發搜尋");
        $("#searchForm").submit();
    });
});