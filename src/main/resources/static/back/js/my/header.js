// header:写日志
function uploadNote() {
    var ok = $('#uploadNoteForm').parsley().isValid({force: true});
    if (!ok) {
        return;
    }

    $.ajax({
        async: false,
        url: '/api/note/doUploadNote',
        data: {'record': $("#record").val()},
        type: 'POST',
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        },
        success: function (data) {
            if (data.code == '000000') {
                $("#uploadNoteBtn").attr("aria-hidden", "true");
                $("#uploadNoteBtn").attr("data-dismiss", "modal");
                $("#uploadNoteForm")[0].reset();
                toastr.success('日志提交成功！', '操作成功');
                if ($("#noteId").length > 0) {
                    locationUrl("/me", "");
                }
            } else {
                toastr.error(data.msg, '操作失败');
            }
        }
    });
}