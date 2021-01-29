package org.springframework.boot.tiger.propertysourceloader;

import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：tigermeng.
 * @date ：2021/1/29 10:59
 * @desc ：
 */
public class TigerPropertySourceLoader implements PropertySourceLoader {

	@Override
	public String[] getFileExtensions() {
		return new String[]{"tiger"};
	}


	@Override
	public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
		Map<String, Object> map = this.loadProperties(resource);
		return Collections.singletonList(new MapPropertySource(name, map));
	}


	@SuppressWarnings({"unchecked", "rawtypes"})
	private Map<String, Object> loadProperties(Resource resource) throws IOException {
		String filename = resource.getFilename();
		if (Strings.isNotBlank(filename) && filename.endsWith(".tiger")) {
			return (Map) PropertiesLoaderUtils.loadProperties(resource);
		}
		return new HashMap<>();
	}
}
