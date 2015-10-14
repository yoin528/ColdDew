package com.ldz.code.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.regex.Pattern;

import com.ldz.code.annotation.Controller;

/**
 * Action扫描器，用于注解扫描控制器
 * @author LDZ   
 * @date 2015年10月14日 上午11:28:32
 */
public class ActionScan {
	private Map<String, Class<?>> ctrs = new HashMap<String, Class<?>>();
	private Map<String, String> viewPaths = new HashMap<String, String>();
	private String pkgs;
	private boolean isScan = false;
	private Pattern STANDARD_SPLIT = Pattern.compile("[\\s,]+");
	private ClassLoader classloader = null;
	
	public ActionScan(String pkgs) {
		this.pkgs = pkgs;
	}
	public void autoScan() {
		String[] packages = STANDARD_SPLIT.split(pkgs);
		for(String pkg : packages) {
			scanPkg(pkg);
		}
		isScan = true;
	}
	/**
	 * 扫描包
	 * @author LDZ   
	 * @date 2015年10月14日 上午11:28:38 
	 * @param pkg
	 */
	public void scanPkg(String pkg) {
		pkg = pkg.replace('.', '/');
		ClassLoader loader = getClassLoader();
		Enumeration<URL> urls = null;
		try {
			urls = loader.getResources(pkg);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		while (urls.hasMoreElements()) {
			String urlPath = urls.nextElement().getFile();
			if (urlPath.startsWith("file:")) {
				urlPath = urlPath.substring(5);
			}
			if (urlPath.indexOf('!') > 0) {
				urlPath = urlPath.substring(0, urlPath.indexOf('!'));
			}
			File file = new File(urlPath);
			if (file.isDirectory()) {
				loadImplementationsInDirectory(pkg, file);
			} else {
				loadImplementationsInJar(pkg, file);
			}
		}
	}
	
	private void loadImplementationsInDirectory(String parent, File location) {
		File[] files = location.listFiles();
		StringBuilder builder = null;
		if (files == null) {
			return;
		}
		for (File file : files) {
			builder = new StringBuilder(100);
			builder.append(parent).append("/").append(file.getName());
			String packageOrClass = (parent == null ? file.getName() : builder
					.toString());
			if (file.isDirectory()) {
				loadImplementationsInDirectory(packageOrClass, file);
			} else if (file.getName().endsWith(".class")) {
				addIfMatching(packageOrClass);
			}
		}
	}

	private void loadImplementationsInJar(String parent, File jarfile) {
		try {
			JarEntry entry;
			@SuppressWarnings("resource")
			JarInputStream jarStream = new JarInputStream(new FileInputStream(
					jarfile));
			while ((entry = jarStream.getNextJarEntry()) != null) {
				String name = entry.getName();
				if (!entry.isDirectory() && name.startsWith(parent)
						&& name.endsWith(".class")) {
					addIfMatching(name);
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void addIfMatching(String fqn) {
		try {
			String externalName = fqn.substring(0, fqn.indexOf('.')).replace(
					'/', '.');
			ClassLoader loader = getClassLoader();
			Class<?> clazz = loader.loadClass(externalName);
			Controller ctr = (Controller) clazz.getAnnotation(Controller.class);
			if (ctr != null) {
				String path = ctr.path();
				Class<?> clazs = ctrs.get(path);
				if (clazs != null) {
					throw new RuntimeException("类名：" + clazz.getName()
							+ "与" + clazs.getName() + "出现重复，请检查配置路径名：" + path);
				}
				ctrs.put(path, clazz);
				String viewPath = ctr.viewPath();
				viewPaths.put(path, viewPath);//模板路径
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private ClassLoader getClassLoader() {
		return classloader == null ? Thread.currentThread()
				.getContextClassLoader() : classloader;
	}
	public boolean isScan() {
		return isScan;
	}
	public Map<String, Class<?>> getCtrs() {
		return ctrs;
	}
	public Map<String, String> getViewPaths() {
		return viewPaths;
	}
	
}
