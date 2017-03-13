package com.wuxian.charge.data.entities;

/**
 * Created by jianglei on 2017/2/24.
 */

public class Ad {

//    {
//        "status": 1,
//            "msg": "获取成功",
//            "data": [
//        {
//            "id": "1",//序号
//                "uniacid": "181",//我方模块ID
//                "title": "",//幻灯片标题
//                "link": "",//链接
//                "thumb": "images/36/2017/02/TyP7XhphX7dX1Pgh5lpkT1Zl17sxr7.jpg",//图片，我会转成远程图片穿回来
//                "status": "1",//该幻灯片是否显示
//                "displayorder": "1",//幻灯片排序
//                "createtime": "0"//幻灯片创建时间
//        }
//        ]
//    }
    private String id;
    private String uniacid;
    private String title;
    private String link;
    private String thumb;
    private String status;
    private String displayorder;
    private String createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUniacid() {
        return uniacid;
    }

    public void setUniacid(String uniacid) {
        this.uniacid = uniacid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(String displayorder) {
        this.displayorder = displayorder;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
