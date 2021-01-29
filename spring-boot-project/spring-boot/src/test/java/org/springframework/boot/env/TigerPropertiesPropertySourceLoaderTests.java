package org.springframework.boot.env;

import org.junit.jupiter.api.Test;
import org.springframework.boot.tiger.propertysourceloader.TigerPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author ：tigermeng.
 * @date ：2021/1/29 11:07
 * @desc ：
 */
public class TigerPropertiesPropertySourceLoaderTests {

	private PropertySourceLoader loader = new TigerPropertySourceLoader();

	@Test
	void getFileExtensions() {
		assertThat(this.loader.getFileExtensions()).isEqualTo(new String[] { "properties", "xml" ,"tiger"});
	}

	@Test
	void loadProperties() throws Exception {
		List<PropertySource<?>> loaded = this.loader.load("custom.tiger",
				new ClassPathResource("custom.tiger", Thread.currentThread().getContextClassLoader()));
		PropertySource<?> source = loaded.get(0);
		assertThat(source.getProperty("name")).isEqualTo("tiger");
		assertThat(source.getProperty("desc")).isEqualTo("learn");
	}
}
