const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;

        function success() {
            alert("삭제 완료되었습니다.");
            location.replace('/articles');
        }

        function fail() {
            alert("삭제 실패했습니다.");
            location.replace('/articles');
        }

        httpRequest("DELETE", "/api/articles/"+id, null, success, fail);
    });
}

const logoutButton = document.getElementById('logout-btn');

if (logoutButton) {
    logoutButton.addEventListener('click', event => {
        function success() {    // 웹에 저장된 토큰 삭제
            localStorage.removeItem('access_token')
            deleteCookie('refresh_token')
            location.replace('/login');
        }

        function fail() {
            alert("로그아웃 실패했습니다.");
        }

        httpRequest("DELETE", "/api/refresh-token", null, success, fail);   // 서버에 저장된 (리프레시) 토큰 삭제
    });
}

const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        body = JSON.stringify({
           title: document.getElementById('title').value,
           content: document.getElementById('content').value
        });

        function success() {
            alert("수정 완료되었습니다.");
            location.replace("/articles/" + id);
        }

        function fail() {
            alert("수정 실패했습니다.");
            location.replace("/articles/" + id);
        }

        httpRequest("PUT", "/api/articles/"+id, body, success, fail);
    });
}

const createButton = document.getElementById('create-btn');

if (createButton) {
    createButton.addEventListener('click', event => {
        body = JSON.stringify({
           title: document.getElementById('title').value,
           content: document.getElementById('content').value
        });

        function success() {
            alert("등록 완료되었습니다.");
            location.replace("/articles");
        }

        function fail() {
            alert("등록 실패했습니다.");
            location.replace("/articles");
        }

        httpRequest("POST", "/api/articles", body, success, fail);

    });
}

function httpRequest(method, url, body, success, fail) {
    fetch(url, {
        method: method,
        headers: {
            Authorization: "Bearer " + localStorage.getItem("access_token"),
            "Content-Type": "application/json",
        },
        body: body,
    }).then((response) => {
        if(response.status === 200 || response.status === 201) {
            return success();
        }
        const refresh_token = getCookie("refresh_token");
        if (response.status === 401 && refresh_token) {
            fetch("/api/token", {
                method: "POST",
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("access_token"),
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    refreshToken: getCookie("refresh_token"),
                }),
            }).then((res) => {
                if(res.ok) {
                    return res.json();
                }
            }).then((result) => {
                localStorage.setItem("access_token", result.accessToken);
                httpRequest(method, url, body, success, fail);
            }).catch((error) => fail());
        } else {
            return fail();
        }
    })
}

function getCookie(key) {
    var result = null;
    var cookie = document.cookie.split(";");
    cookie.some(function (item) {
        item = item.replace(" ", "");
        var dic = item.split("=");

        if (key == dic[0]) {
            result = dic[1];
            return true;
        }
    });

    return result;
}

function deleteCookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}