package controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class Crud extends Controller {

	public void view() {
		setSessionAttr("id", 1);
		// 获取用户名
		String id = getSessionAttr("id").toString();
		List<Record> list_zu = Db.find("select * from zu where userid=?", id);
		if (list_zu != null & list_zu.size() > 0) {
			setAttr("list1", list_zu);
			List<Record> list_file = Db.find("select * from file where suozaizu=?", list_zu.get(0).get("id"));
			if (list_file != null & list_file.size() > 0) {
				setAttr("list2", list_file);
			}
		}

		render("/WEB-INF/ui/view.html");

	}

	public void selectfilename() {
		// 获取组的id
		String id = getPara("id").toString();
		List<Record> list_file = Db.find("select * from file where suozaizu=?", id);
		renderJson(list_file);

	}

	public void selectfile() {
		String id = getPara("id").toString();
		Record c = Db.findById("file", id);
		renderJson(c);
	}

	public void save() {

	}

	public void selectneirong() {
		String id = getPara("id").toString();
		Record c = Db.findById("file", id);
		renderText(c.get("neirong").toString());
	}

	public void selectwjm() {
		String id = getPara("id").toString();
		List<Record> list_file = Db.find("select * from file where suozaizu=?", id);
		String aa = null;
		if (list_file != null & list_file.size() > 0) {
			for (Record e : list_file) {
				aa += "<p><span onclick=\"file('" + e.get(id).toString() + "')\">" + e.getStr("name")
						+ "</span><a href=\"\" style=\"color: red\">X</a></p><br>";
			}
		}
		renderText(aa);
	}
}
