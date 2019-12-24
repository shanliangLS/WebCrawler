$(function () {
    $("#passwordError").hide();
    $("#nicknameError").hide();
});

/**
 * @return {boolean}
 */
function IsHtmlMe() {
    return $('#htmlMe').length > 0;
}

// sidebar:修改个人简介
function updateIntroduction() {
    var ok = $('#updateIntroductionForm').parsley().isValid({force: true});
    if (!ok) {
        return;
    }

    var intro = $("#introduction").val();
    $.ajax({
        async: false,
        url: '/api/setting/doUpdateIntroduction',
        data: {'introduction': intro},
        type: 'POST',
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        },
        success: function (data) {
            if (data.code == '000000') {
                $("#updateIntroductionBtn").attr("aria-hidden", "true");
                $("#updateIntroductionBtn").attr("data-dismiss", "modal");
                $("#updateIntroductionForm")[0].reset();
                if (IsHtmlMe()) {
                    $("#userIntroduction").html(intro);
                }
                toastr.success('个人简介修改成功！', '操作成功');
            } else {
                toastr.error(data.msg, '操作失败');
            }
        }
    });
}


// sidebar:修改密码
function updatePwd() {
    var ok = $('#updatePwdForm').parsley().isValid({force: true});
    if (!ok) {
        return;
    }
    $.ajax({
        async: false,
        url: '/api/setting/doUpdatePassword',
        data: 'oldPassword=' + $("#oldPassword").val() + '&newPassword=' + $("#newPassword").val(),
        type: 'POST',
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        },
        success: function (data) {
            if (data.code == '000000') {
                $("#passwordError").hide();
                $("#updatePwdBtn").attr("aria-hidden", "true");
                $("#updatePwdBtn").attr("data-dismiss", "modal");
                $("#updatePwdForm")[0].reset();
                toastr.success('密码修改成功！', '操作成功');
            } else {
                $("#passwordError").show();
                $("#passwordError").html(data.msg);
                $("#updatePwdBtn").removeAttr("aria-hidden");
                $("#updatePwdBtn").removeAttr("data-dismiss");
            }
        }
    });
}

// sidebar:修改昵称
function updateNickname() {
    var ok = $('#updateNicknameForm').parsley().isValid({force: true});
    if (!ok) {
        return;
    }
    var newName = $("#newNickname").val();
    $.ajax({
        async: false,
        url: '/api/setting/doUpdateName',
        data: 'name=' + newName,
        type: 'POST',
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        },
        success: function (data) {
            if (data.code == '000000') {
                $("#nicknameError").hide();
                $("#updateNicknameBtn").attr("aria-hidden", "true");
                $("#updateNicknameBtn").attr("data-dismiss", "modal");
                $("#updateNicknameForm")[0].reset();
                if(IsHtmlMe())
                {
                    $("#userUserName").html(newName);
                }
                toastr.success('昵称修改成功！', '操作成功');
            } else {
                $("#nicknameError").show();
                $("#nicknameError").html(data.msg);
                $("#updateNicknameBtn").removeAttr("aria-hidden");
                $("#updateNicknameBtn").removeAttr("data-dismiss");
            }
        }
    });
}