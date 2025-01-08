$("#searchButton").on('click', function () {
    $("#searchForm").submit();
    console.log("觸發搜尋成功")
});

// 獲取 URL 查詢參數
function getQueryParams() {
    let params = {};
    window.location.search.substring(1).split("&").forEach(param => {
        let [key, value] = param.split("=");
        if (!params[key]) {
            params[key] = [];
        }
        params[key].push(decodeURIComponent(value));
    });
    return params;
}

// 將查詢參數應用到表單中
$(document).ready(function () {
    const params = getQueryParams();
    if (params.keyword) {
        $("#keyword").val(params.keyword[0]);
        $("#searchResults").text(`搜索關鍵字：${params.keyword[0]}`);
    }
    if (params.options) {
        params.options[0].split(',').forEach(option => {
            $(`input[name='options'][value='${option}']`).prop('checked', true);
        });
    }
    // 確認搜索按鈕能夠正常提交表單 
    $("#searchButton").on('click', function () {
        $("#searchForm").submit();
        console.log("觸發搜尋成功");
    });
});
// ------------以下可傳遞checkBox-----------
$("#searchButton").on('click', function () {
$("#searchForm").submit();
console.log("觸發搜尋成功")
});

// 獲取 URL 查詢參數
function getQueryParams() {
let params = {};
window.location.search.substring(1).split("&").forEach(param => {
let [key, value] = param.split("=");
if (!params[key]) {
    params[key] = [];
}
params[key].push(decodeURIComponent(value));
});
return params;
}

// 將查詢參數應用到表單中
function applyQueryParams(params) {
if (params.keyword) {
$("#keyword").val(params.keyword[0]);
}
if (params.options) {
params.options.forEach(option => {
    let checkbox = $(`input[name="options"][value="${option}"]`);
    if (checkbox.length) {
        checkbox.prop("checked", true);
    }
});
}
}
// 當文檔加載完成後應用查詢參數
$(document).ready(function () {
let params = getQueryParams();
applyQueryParams(params);
});