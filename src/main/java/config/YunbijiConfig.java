package config;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal.BeetlRenderFactory;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

import controller.Crud;


public class YunbijiConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants arg0) {
		arg0.setDevMode(true);
		arg0.setViewType(ViewType.JSP);
		arg0.setMainRenderFactory(new BeetlRenderFactory());
	        GroupTemplate mainGT = BeetlRenderFactory.groupTemplate;
	}

	@Override
	public void configHandler(Handlers arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configInterceptor(Interceptors arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPlugin(Plugins arg0) {
		 PropKit.use("DB.properties");
	        String driverClass=PropKit.get("driverClass");
	        String jdbcUrl=PropKit.get("jdbcUrl");
	        String user=PropKit.get("user");
	        String password=PropKit.get("password");
//		C3p0Plugin c3p0Plugin=new C3p0Plugin("jdbc:mysql://localhost:3306/yunbiji","root","");
	        C3p0Plugin c3p0Plugin=new C3p0Plugin(jdbcUrl,user,password,driverClass);
		arg0.add(c3p0Plugin);
		ActiveRecordPlugin arp=new ActiveRecordPlugin(c3p0Plugin);
		arg0.add(arp);
		
	}

	@Override
	public void configRoute(Routes arg0) {
		// TODO Auto-generated method stub
		arg0.add("/",Crud.class);
		
	}

}
