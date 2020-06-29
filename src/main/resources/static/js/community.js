function post() {
    var questionId = $("#question_id").val();
    var text = $(".comment").val();
    $.ajax({
            type: "post",
            url: "/comment",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                "parentId": questionId,
                "content": text,
                "type": 1
            }),
            success: function (data) {
                if (data.code == 200) {
                    $("#comment_section").hide(2000);
                } else {
                    if (data.code == 2003) {
                       var isAccepted=confirm(data.message)
                       if(isAccepted){
                           window.open("https://github.com/login/oauth/authorize?client_id=f46aabb20f309d96c1e7&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                           window.localStorage.setItem("closable","true");
                       }
                    } else {
                        alert(data.code+"-"+data.message);
                    }
                }
            },
        dataType:"json"});
}