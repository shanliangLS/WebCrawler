package com.get.web;

import com.get.comm.aop.LoggerManage;
import com.get.comm.aop.LoginRequired;
import com.get.domain.Note;
import com.get.domain.res.AjaxResult;
import com.get.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/note")
public class NoteController extends BaseController {


    @Autowired
    private NoteRepository noteRepository;

    /**
     * 上传日志
     *
     * @param note
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "doUploadNote", method = RequestMethod.POST)
    @LoggerManage(description = "上传日志")
    public AjaxResult doUploadNote(Note note) {
        try {
            note.setUserId(getUserId());
            note.setCreateTime(getCurrentTime());
            noteRepository.save(note);
            return successAjax();
        } catch (Exception e) {
            logger.error("上传日志失败", e);
            return errorAjax();
        }
    }

    /**
     * 按日志ID删除
     *
     * @param id
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "doDeleteById/{id}", method = RequestMethod.POST)
    @LoggerManage(description = "按日志ID删除")
    public AjaxResult doDeleteById(@PathVariable("id") Long id) {
        try {
            noteRepository.deleteNoteByIdAndUserId(id, getUserId());
            return successAjax();
        } catch (Exception e) {
            logger.error("按ID删除日志失败", e);
            return errorAjax();
        }
    }

    /**
     * 按ID得到日志
     *
     * @param id
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "doGetNoteById/{id}", method = RequestMethod.POST)
    @LoggerManage(description = "按ID得到日志")
    public AjaxResult doGetNoteById(@PathVariable("id") Long id) {
        try {
            Note note = noteRepository.findNoteByIdAndUserId(id, getUserId());
            return successAjax(note);
        } catch (Exception e) {
            logger.error("按ID得到日志失败:", e);
            return errorAjax();
        }
    }

    /**
     * 修改日志内容
     *
     * @param id
     * @param record
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "doUpdateNoteRecordById", method = RequestMethod.POST)
    @LoggerManage(description = "修改日志内容")
    public AjaxResult updateNoteRecordById(Long id, String record) {
        try {
            noteRepository.setRecord(record, id, getUserId());
            return successAjax();
        } catch (Exception e) {
            logger.error("修改日志内容", e);
            return errorAjax();
        }
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
