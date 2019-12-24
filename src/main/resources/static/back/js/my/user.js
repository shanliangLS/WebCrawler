/**
 * @return {boolean}
 */
function IsHtmlMe() {
    return $('#htmlMe').length > 0;
}

function onNoteCollect(id) {
    $('#modal-remove').modal('show');
    $("#noteId").val(id);
}

function delNoteCollect() {
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data: "",
        url: '/api/note/doDeleteById/' + $("#noteId").val(),
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
            toastr.error("删除日志失败", "操作失败");
        },
        success: function (response) {
            if (response.code == '000000') {
                $('#modal-remove').modal('hide');
                toastr.success("日志删除成功!", "操作成功");
                if (IsHtmlMe()) {
                    locationUrl("/me", "");
                }
            } else {
                toastr.error("删除日志失败", "操作失败");
            }
        }
    });
}

function getNoteCollect(id) {
    $("#modal-updateNote").modal('show');
    $("#noteId").val(id);
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data: "",
        url: '/api/note/doGetNoteById/' + $("#noteId").val(),
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
            toastr.error("得到日志失败", "操作失败");
        },
        success: function (response) {
            if (response.code == '000000') {
                $("#noteRecord").val(response.data.record);
                $("#updateRecord").text(response.data.record);
            } else {
                toastr.error("请求日志失败", "操作失败");
            }
        }
    });
}


function updateNoteCollect() {
    var ok = $('#updateNoteForm').parsley().isValid({force: true});
    if (!ok) {
        return;
    }

    var noteId = $("#noteId").val();
    var noteRecord = $("#updateRecord").val();
    $.ajax({
        async: false,
        type: 'POST',
        dataType: 'json',
        data: {
            'record': noteRecord,
            'id': noteId
        },
        url: '/api/note/doUpdateNoteRecordById',
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
            toastr.error("修改日志失败", "操作失败");
        },
        success: function (response) {
            if (response.code == '000000') {
                $("#updateNoteBtn").attr("aria-hidden", "true");
                $("#updateNoteBtn").attr("data-dismiss", "modal");
                $("#updateNoteForm")[0].reset();
                $('#modal-updateNote').modal('hide');
                toastr.success("日志修改成功!", "操作成功");
                if ($('#noteId').length > 0) {
                    locationUrl("/me", "");
                }
            } else {
                toastr.error("修改日志失败", "操作失败");
            }
        }
    });
}