package com.ldz.code.render;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * Freemarker渲染器
 * @author LDZ
 * @date 2013-12-14 下午06:06:23
 */
public class FreemarkerRender extends Render {
	private static Configuration cfg;
	private String view;
	
	
	public FreemarkerRender(String view) {
		this.view = view;
	}
	public static void initFrk(ServletContext context) {
		cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(
        		context, "/");
        cfg.setTemplateUpdateDelay(0);
        cfg.setTemplateExceptionHandler(
                TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        cfg.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
        cfg.setClassicCompatible(true);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setOutputEncoding("UTF-8");
        cfg.setLocale(Locale.CHINA);

	}
	@SuppressWarnings("unchecked")
	@Override
	public void rander() {
		Writer out = null;
        try {
        	 Template t = cfg.getTemplate(view);
    		 response.setContentType("text/html; charset=" + cfg.getOutputEncoding());
          	 response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, "
                    + "post-check=0, pre-check=0");
    		 response.setHeader("Pragma", "no-cache");
    		 response.setHeader("Expires", "Thu, 01 Dec 1994 00:00:00 GMT");
    		 out = response.getWriter();
    		 Map data = new HashMap();
    		 Enumeration<String> attrs = request.getAttributeNames();
    		 while (attrs.hasMoreElements()) {
				String attrName = attrs.nextElement();
				data.put(attrName, request.getAttribute(attrName));
    		 }
    		 
             t.process(data, out);
             response.flushBuffer();
        } catch(FileNotFoundException fe){
        	throw new RuntimeException("����ҳ�治���ڣ�");
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
        	try {
			  if(out != null) out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

}
