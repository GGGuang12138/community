function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();

    if(!content){
        alert("回复内容不能为空呢～")
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "content":content,
            "type":1,
            "parentId":questionId
        }),
        success: function (response) {
            if(response.code == 200){
                window.location.reload();
                $("#comment_section").hide();
            }else{
                if(response.code == 1001){
                    var isAccepted = confirm(response.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=f69d16668d64167ff6c4&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                }else{
                    alert(response.message);
                }
            }
            console.log(response)
        },
        dataType: "json",
    });
}